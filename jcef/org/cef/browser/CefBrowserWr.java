package org.cef.browser;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import org.cef.CefBrowserSettings;
import org.cef.CefClient;
import org.cef.OS;
import org.cef.handler.CefWindowHandler;
import org.cef.handler.CefWindowHandlerAdapter;

class CefBrowserWr extends CefBrowser_N {
   private Canvas canvas_ = null;
   private Component component_ = null;
   private Rectangle content_rect_ = new Rectangle(0, 0, 0, 0);
   private long window_handle_ = 0L;
   private boolean justCreated_ = false;
   private double scaleFactor_ = 1.0;
   private Timer delayedUpdate_ = new Timer(100, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent var1) {
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               if (!CefBrowserWr.this.isClosed()) {
                  boolean var1x = CefBrowserWr.this.createBrowserIfRequired(true);
                  if (var1x) {
                     CefBrowserWr.this.delayedUpdate_.restart();
                  } else if (OS.isMacintosh() || OS.isLinux()) {
                     CefBrowserWr.this.doUpdate();
                  }
               }
            }
         });
      }
   });
   private CefWindowHandlerAdapter win_handler_ = new CefWindowHandlerAdapter() {
      private Point lastPos = new Point(-1, -1);
      private long[] nextClick = new long[MouseInfo.getNumberOfButtons()];
      private int[] clickCnt = new int[MouseInfo.getNumberOfButtons()];

      @Override
      public Rectangle getRect(CefBrowser var1) {
         synchronized (CefBrowserWr.this.content_rect_) {
            return CefBrowserWr.this.content_rect_;
         }
      }

      @Override
      public void onMouseEvent(CefBrowser var1, final int var2, final int var3, final int var4, final int var5, final int var6) {
         final Point var7 = new Point(var3, var4);
         if (var2 == 503) {
            if (var7.equals(this.lastPos)) {
               return;
            }

            this.lastPos = var7;
            if ((var5 & 1024) != 0) {
               var2 = 506;
            }
         }

         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               Component var1x = SwingUtilities.getRoot(CefBrowserWr.this.component_);
               if (var1x != null) {
                  SwingUtilities.convertPointFromScreen(var7, var1x);
                  int var2x = 0;
                  long var3x = new Date().getTime();
                  if (var2 == 507) {
                     byte var5x = 0;
                     int var6x = var6 > 0 ? 1 : -1;
                     CefBrowserWr.this.component_.dispatchEvent(new MouseWheelEvent(var1x, var2, var3x, var5, var7.x, var7.y, 0, false, var5x, 3, var6x));
                  } else {
                     var2x = getClickCount(var2, var6);
                     CefBrowserWr.this.component_.dispatchEvent(new MouseEvent(var1x, var2, var3x, var5, var7.x, var7.y, var3, var4, var2x, false, var6));
                  }

                  if (var2 == 502) {
                     CefBrowserWr.this.component_.dispatchEvent(new MouseEvent(var1x, 500, var3x, var5, var7.x, var7.y, var3, var4, var2x, false, var6));
                  }
               }
            }
         });
      }

      public int getClickCount(int var1, int var2) {
         int var3 = var2 % this.nextClick.length;
         switch (var1) {
            case 501:
               long var4 = new Date().getTime();
               if (var4 > this.nextClick[var3]) {
                  this.nextClick[var3] = var4 + ((Integer)Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval")).intValue();
                  this.clickCnt[var3] = 1;
               } else {
                  this.clickCnt[var3]++;
               }
            case 502:
               return this.clickCnt[var3];
            default:
               return 0;
         }
      }
   };

   CefBrowserWr(CefClient var1, String var2, CefRequestContext var3, CefBrowserSettings var4) {
      this(var1, var2, var3, null, null, var4);
   }

   private CefBrowserWr(CefClient var1, String var2, CefRequestContext var3, CefBrowserWr var4, Point var5, CefBrowserSettings var6) {
      super(var1, var2, var3, var4, var5, var6);
      this.delayedUpdate_.setRepeats(false);
      JPopupMenu.setDefaultLightWeightPopupEnabled(false);
      ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
      this.component_ = new JPanel(new BorderLayout()) {
         private boolean removed_ = true;

         @Override
         public void setBounds(int var1, int var2x, int var3x, int var4x) {
            super.setBounds(var1, var2x, var3x, var4x);
            CefBrowserWr.this.wasResized((int)(var3x * CefBrowserWr.this.scaleFactor_), (int)(var4x * CefBrowserWr.this.scaleFactor_));
         }

         @Override
         public void setBounds(Rectangle var1) {
            this.setBounds(var1.x, var1.y, var1.width, var1.height);
         }

         @Override
         public void setSize(int var1, int var2x) {
            super.setSize(var1, var2x);
            CefBrowserWr.this.wasResized((int)(var1 * CefBrowserWr.this.scaleFactor_), (int)(var2x * CefBrowserWr.this.scaleFactor_));
         }

         @Override
         public void setSize(Dimension var1) {
            this.setSize(var1.width, var1.height);
         }

         @Override
         public void paint(Graphics var1) {
            if (var1 instanceof Graphics2D) {
               CefBrowserWr.this.scaleFactor_ = ((Graphics2D)var1).getTransform().getScaleX();
            }

            CefBrowserWr.this.doUpdate();
            CefBrowserWr.this.delayedUpdate_.restart();
         }

         @Override
         public void addNotify() {
            super.addNotify();
            if (this.removed_) {
               CefBrowserWr.this.setParent(CefBrowserWr.getWindowHandle(this), CefBrowserWr.this.canvas_);
               this.removed_ = false;
            }
         }

         @Override
         public void removeNotify() {
            if (!this.removed_) {
               if (!CefBrowserWr.this.isClosed()) {
                  CefBrowserWr.this.setParent(0L, null);
               }

               this.removed_ = true;
            }

            super.removeNotify();
         }
      };
      if (OS.isWindows() || OS.isLinux()) {
         this.canvas_ = new Canvas();
         ((JPanel)this.component_).add(this.canvas_, "Center");
      }

      this.component_.setMinimumSize(new Dimension(0, 0));
      this.component_.setFocusable(true);
      this.component_.addFocusListener(new FocusListener() {
         @Override
         public void focusLost(FocusEvent var1) {
            CefBrowserWr.this.setFocus(false);
         }

         @Override
         public void focusGained(FocusEvent var1) {
            MenuSelectionManager.defaultManager().clearSelectedPath();
            CefBrowserWr.this.setFocus(true);
         }
      });
      this.component_.addHierarchyBoundsListener(new HierarchyBoundsListener() {
         @Override
         public void ancestorResized(HierarchyEvent var1) {
            CefBrowserWr.this.doUpdate();
         }

         @Override
         public void ancestorMoved(HierarchyEvent var1) {
            CefBrowserWr.this.doUpdate();
            CefBrowserWr.this.notifyMoveOrResizeStarted();
         }
      });
      this.component_.addHierarchyListener(new HierarchyListener() {
         @Override
         public void hierarchyChanged(HierarchyEvent var1) {
            if ((var1.getChangeFlags() & 4L) != 0L) {
               CefBrowserWr.this.setWindowVisibility(var1.getChanged().isVisible());
            }
         }
      });
   }

   @Override
   public void createImmediately() {
      this.justCreated_ = true;
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            CefBrowserWr.this.createBrowserIfRequired(false);
         }
      });
   }

   @Override
   public Component getUIComponent() {
      return this.component_;
   }

   @Override
   public CefWindowHandler getWindowHandler() {
      return this.win_handler_;
   }

   @Override
   protected CefBrowser_N createDevToolsBrowser(CefClient var1, String var2, CefRequestContext var3, CefBrowser_N var4, Point var5) {
      return new CefBrowserWr(var1, var2, var3, this, var5, null);
   }

   private synchronized long getWindowHandle() {
      if (this.window_handle_ == 0L && OS.isMacintosh()) {
         this.window_handle_ = getWindowHandle(this.component_);
      }

      return this.window_handle_;
   }

   private static long getWindowHandle(Component var0) {
      if (OS.isMacintosh()) {
         try {
            Class var1 = Class.forName("org.cef.browser.mac.CefBrowserWindowMac");
            CefBrowserWindow var2 = (CefBrowserWindow)var1.newInstance();
            if (var2 != null) {
               return var2.getWindowHandle(var0);
            }
         } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
         } catch (InstantiationException var4) {
            var4.printStackTrace();
         } catch (IllegalAccessException var5) {
            var5.printStackTrace();
         }
      }

      return 0L;
   }

   private void doUpdate() {
      if (!this.isClosed()) {
         Rectangle var1 = ((JPanel)this.component_).getVisibleRect();
         Rectangle var2 = new Rectangle(
            (int)(var1.getX() * this.scaleFactor_),
            (int)(var1.getY() * this.scaleFactor_),
            (int)(var1.getWidth() * this.scaleFactor_),
            (int)(var1.getHeight() * this.scaleFactor_)
         );
         if (OS.isMacintosh()) {
            Container var3 = this.component_.getParent();
            Point var4 = this.component_.getLocation();

            while (var3 != null) {
               Container var5 = var3.getParent();
               if (var5 != null && var5 instanceof Window) {
                  break;
               }

               Point var6 = var3.getLocation();
               var4.translate(var6.x, var6.y);
               var3 = var5;
            }

            var4.translate(var2.x, var2.y);
            Point var14 = var2.getLocation();
            var14.x *= -1;
            var14.y *= -1;
            synchronized (this.content_rect_) {
               this.content_rect_ = new Rectangle(var4, var2.getSize());
               Rectangle var7 = new Rectangle(var14, this.component_.getSize());
               this.updateUI(this.content_rect_, var7);
            }
         } else {
            synchronized (this.content_rect_) {
               Rectangle var13 = null != this.canvas_ ? this.canvas_.getBounds() : this.component_.getBounds();
               this.content_rect_ = new Rectangle(
                  (int)(var13.getX() * this.scaleFactor_),
                  (int)(var13.getY() * this.scaleFactor_),
                  (int)(var13.getWidth() * this.scaleFactor_),
                  (int)(var13.getHeight() * this.scaleFactor_)
               );
               this.updateUI(var2, this.content_rect_);
            }
         }
      }
   }

   private boolean createBrowserIfRequired(boolean var1) {
      if (this.isClosed()) {
         return false;
      } else {
         long var2 = 0L;
         Object var4 = null;
         if (var1) {
            var2 = this.getWindowHandle();
            var4 = !OS.isWindows() && !OS.isLinux() ? this.component_ : this.canvas_;
         }

         if (this.getNativeRef("CefBrowser") == 0L) {
            if (this.getParentBrowser() != null) {
               this.createDevTools(this.getParentBrowser(), this.getClient(), var2, false, false, (Component)var4, this.getInspectAt());
               return true;
            } else {
               this.createBrowser(this.getClient(), var2, this.getUrl(), false, false, (Component)var4, this.getRequestContext());
               return true;
            }
         } else {
            if (var1 && this.justCreated_) {
               this.setParent(var2, (Component)var4);
               this.setFocus(true);
               this.justCreated_ = false;
            }

            return false;
         }
      }
   }

   @Override
   public CompletableFuture<BufferedImage> createScreenshot(boolean var1) {
      throw new UnsupportedOperationException("Unsupported for windowed rendering");
   }

   @Override
   public void setWindowlessFrameRate(int var1) {
      throw new UnsupportedOperationException("You can only set windowless framerate on OSR browser");
   }

   @Override
   public CompletableFuture<Integer> getWindowlessFrameRate() {
      throw new UnsupportedOperationException("You can only get windowless framerate on OSR browser");
   }
}
