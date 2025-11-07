package org.cef.browser;

import com.jogamp.nativewindow.NativeSurface;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.GLBuffers;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DropTarget;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import org.cef.CefBrowserSettings;
import org.cef.CefClient;
import org.cef.OS;
import org.cef.callback.CefDragData;
import org.cef.handler.CefRenderHandler;
import org.cef.handler.CefScreenInfo;

class CefBrowserOsr extends CefBrowser_N implements CefRenderHandler {
   private CefRenderer renderer_;
   private GLCanvas canvas_;
   private long window_handle_ = 0L;
   private boolean justCreated_ = false;
   private Rectangle browser_rect_ = new Rectangle(0, 0, 1, 1);
   private Point screenPoint_ = new Point(0, 0);
   private double scaleFactor_ = 1.0;
   private int depth = 32;
   private int depth_per_component = 8;
   private boolean isTransparent_;
   private CopyOnWriteArrayList<Consumer<CefPaintEvent>> onPaintListeners = new CopyOnWriteArrayList<>();

   CefBrowserOsr(CefClient var1, String var2, boolean var3, CefRequestContext var4, CefBrowserSettings var5) {
      this(var1, var2, var3, var4, null, null, var5);
   }

   private CefBrowserOsr(CefClient var1, String var2, boolean var3, CefRequestContext var4, CefBrowserOsr var5, Point var6, CefBrowserSettings var7) {
      super(var1, var2, var4, var5, var6, var7);
      this.isTransparent_ = var3;
      this.renderer_ = new CefRenderer(var3);
      this.createGLCanvas();
   }

   @Override
   public void createImmediately() {
      this.justCreated_ = true;
      this.createBrowserIfRequired(false);
   }

   @Override
   public Component getUIComponent() {
      return this.canvas_;
   }

   @Override
   public CefRenderHandler getRenderHandler() {
      return this;
   }

   @Override
   protected CefBrowser_N createDevToolsBrowser(CefClient var1, String var2, CefRequestContext var3, CefBrowser_N var4, Point var5) {
      return new CefBrowserOsr(var1, var2, this.isTransparent_, var3, this, var5, null);
   }

   private synchronized long getWindowHandle() {
      if (this.window_handle_ == 0L) {
         NativeSurface var1 = this.canvas_.getNativeSurface();
         if (var1 != null) {
            var1.lockSurface();
            this.window_handle_ = this.getWindowHandle(var1.getSurfaceHandle());
            var1.unlockSurface();

            assert this.window_handle_ != 0L;
         }
      }

      return this.window_handle_;
   }

   private void createGLCanvas() {
      GLProfile var1 = GLProfile.getMaxFixedFunc(true);
      GLCapabilities var2 = new GLCapabilities(var1);
      this.canvas_ = new GLCanvas(var2) {
         private Method scaleFactorAccessor = null;
         private boolean removed_ = true;

         public void paint(Graphics var1) {
            CefBrowserOsr.this.createBrowserIfRequired(true);
            if (var1 instanceof Graphics2D) {
               GraphicsConfiguration var2x = ((Graphics2D)var1).getDeviceConfiguration();
               CefBrowserOsr.this.depth = var2x.getColorModel().getPixelSize();
               CefBrowserOsr.this.depth_per_component = var2x.getColorModel().getComponentSize()[0];
               if (OS.isMacintosh() && System.getProperty("java.runtime.version").startsWith("1.8")) {
                  try {
                     if (this.scaleFactorAccessor == null) {
                        this.scaleFactorAccessor = this.getClass().getClassLoader().loadClass("sun.awt.CGraphicsDevice").getDeclaredMethod("getScaleFactor");
                     }

                     Object var3 = this.scaleFactorAccessor.invoke(var2x.getDevice());
                     if (var3 instanceof Integer) {
                        CefBrowserOsr.this.scaleFactor_ = ((Integer)var3).doubleValue();
                     } else {
                        CefBrowserOsr.this.scaleFactor_ = 1.0;
                     }
                  } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | ClassNotFoundException | InvocationTargetException var4) {
                     CefBrowserOsr.this.scaleFactor_ = 1.0;
                  }
               } else {
                  CefBrowserOsr.this.scaleFactor_ = ((Graphics2D)var1).getTransform().getScaleX();
               }
            }

            super.paint(var1);
         }

         public void addNotify() {
            super.addNotify();
            if (this.removed_) {
               CefBrowserOsr.this.notifyAfterParentChanged();
               this.removed_ = false;
            }
         }

         public void removeNotify() {
            if (!this.removed_) {
               if (!CefBrowserOsr.this.isClosed()) {
                  CefBrowserOsr.this.notifyAfterParentChanged();
               }

               this.removed_ = true;
            }

            super.removeNotify();
         }
      };
      this.canvas_.addGLEventListener(new GLEventListener() {
         public void reshape(GLAutoDrawable var1, int var2x, int var3, int var4, int var5) {
            int var6 = var4;
            int var7 = var5;
            if (OS.isMacintosh()) {
               var6 = (int)(var4 / CefBrowserOsr.this.scaleFactor_);
               var7 = (int)(var5 / CefBrowserOsr.this.scaleFactor_);
            }

            CefBrowserOsr.this.browser_rect_.setBounds(var2x, var3, var6, var7);
            CefBrowserOsr.this.screenPoint_ = CefBrowserOsr.this.canvas_.getLocationOnScreen();
            CefBrowserOsr.this.wasResized(var6, var7);
         }

         public void init(GLAutoDrawable var1) {
            CefBrowserOsr.this.renderer_.initialize(var1.getGL().getGL2());
         }

         public void dispose(GLAutoDrawable var1) {
            CefBrowserOsr.this.renderer_.cleanup(var1.getGL().getGL2());
         }

         public void display(GLAutoDrawable var1) {
            CefBrowserOsr.this.renderer_.render(var1.getGL().getGL2());
         }
      });
      this.canvas_.addMouseListener(new MouseListener() {
         @Override
         public void mousePressed(MouseEvent var1) {
            CefBrowserOsr.this.sendMouseEvent(var1);
         }

         @Override
         public void mouseReleased(MouseEvent var1) {
            CefBrowserOsr.this.sendMouseEvent(var1);
         }

         @Override
         public void mouseEntered(MouseEvent var1) {
            CefBrowserOsr.this.sendMouseEvent(var1);
         }

         @Override
         public void mouseExited(MouseEvent var1) {
            CefBrowserOsr.this.sendMouseEvent(var1);
         }

         @Override
         public void mouseClicked(MouseEvent var1) {
            CefBrowserOsr.this.sendMouseEvent(var1);
         }
      });
      this.canvas_.addMouseMotionListener(new MouseMotionListener() {
         @Override
         public void mouseMoved(MouseEvent var1) {
            CefBrowserOsr.this.sendMouseEvent(var1);
         }

         @Override
         public void mouseDragged(MouseEvent var1) {
            CefBrowserOsr.this.sendMouseEvent(var1);
         }
      });
      this.canvas_.addMouseWheelListener(new MouseWheelListener() {
         @Override
         public void mouseWheelMoved(MouseWheelEvent var1) {
            CefBrowserOsr.this.sendMouseWheelEvent(var1);
         }
      });
      this.canvas_.addKeyListener(new KeyListener() {
         @Override
         public void keyTyped(KeyEvent var1) {
            CefBrowserOsr.this.sendKeyEvent(var1);
         }

         @Override
         public void keyPressed(KeyEvent var1) {
            CefBrowserOsr.this.sendKeyEvent(var1);
         }

         @Override
         public void keyReleased(KeyEvent var1) {
            CefBrowserOsr.this.sendKeyEvent(var1);
         }
      });
      this.canvas_.setFocusable(true);
      this.canvas_.addFocusListener(new FocusListener() {
         @Override
         public void focusLost(FocusEvent var1) {
            CefBrowserOsr.this.setFocus(false);
         }

         @Override
         public void focusGained(FocusEvent var1) {
            MenuSelectionManager.defaultManager().clearSelectedPath();
            CefBrowserOsr.this.setFocus(true);
         }
      });
      new DropTarget(this.canvas_, new CefDropTargetListener(this));
   }

   @Override
   public Rectangle getViewRect(CefBrowser var1) {
      return this.browser_rect_;
   }

   @Override
   public Point getScreenPoint(CefBrowser var1, Point var2) {
      Point var3 = new Point(this.screenPoint_);
      var3.translate(var2.x, var2.y);
      return var3;
   }

   @Override
   public void onPopupShow(CefBrowser var1, boolean var2) {
      if (!var2) {
         this.renderer_.clearPopupRects();
         this.invalidate();
      }
   }

   @Override
   public void onPopupSize(CefBrowser var1, Rectangle var2) {
      this.renderer_.onPopupSize(var2);
   }

   @Override
   public void addOnPaintListener(Consumer<CefPaintEvent> var1) {
      this.onPaintListeners.add(var1);
   }

   @Override
   public void setOnPaintListener(Consumer<CefPaintEvent> var1) {
      this.onPaintListeners.clear();
      this.onPaintListeners.add(var1);
   }

   @Override
   public void removeOnPaintListener(Consumer<CefPaintEvent> var1) {
      this.onPaintListeners.remove(var1);
   }

   @Override
   public void onPaint(CefBrowser var1, boolean var2, Rectangle[] var3, ByteBuffer var4, int var5, int var6) {
      GLContext var7 = this.canvas_ != null ? this.canvas_.getContext() : null;
      if (var7 != null) {
         if (var7.makeCurrent() != 0) {
            this.renderer_.onPaint(this.canvas_.getGL().getGL2(), var2, var3, var4, var5, var6);
            var7.release();
            SwingUtilities.invokeLater(new Runnable() {
               @Override
               public void run() {
                  CefBrowserOsr.this.canvas_.display();
               }
            });
            if (!this.onPaintListeners.isEmpty()) {
               CefPaintEvent var8 = new CefPaintEvent(var1, var2, var3, var4, var5, var6);

               for (Consumer var10 : this.onPaintListeners) {
                  var10.accept(var8);
               }
            }
         }
      }
   }

   @Override
   public boolean onCursorChange(CefBrowser var1, final int var2) {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            CefBrowserOsr.this.canvas_.setCursor(new Cursor(var2));
         }
      });
      return true;
   }

   private static int getDndAction(int var0) {
      int var1 = 0;
      if ((var0 & 1) == 1) {
         var1 = 1;
      } else if ((var0 & 16) == 16) {
         var1 = 2;
      } else if ((var0 & 2) == 2) {
         var1 = 1073741824;
      }

      return var1;
   }

   @Override
   public boolean startDragging(CefBrowser var1, CefDragData var2, int var3, int var4, int var5) {
      final int var6 = getDndAction(var3);
      MouseEvent var7 = new MouseEvent(this.canvas_, 506, 0L, 0, var4, var5, 0, false);
      DragGestureEvent var8 = new DragGestureEvent(
         new CefBrowserOsr.SyntheticDragGestureRecognizer(this.canvas_, var6, var7), var6, new Point(var4, var5), new ArrayList<>(Arrays.asList(var7))
      );
      DragSource.getDefaultDragSource().startDrag(var8, null, new StringSelection(var2.getFragmentText()), new DragSourceAdapter() {
         @Override
         public void dragDropEnd(DragSourceDropEvent var1) {
            CefBrowserOsr.this.dragSourceEndedAt(var1.getLocation(), var6);
            CefBrowserOsr.this.dragSourceSystemDragEnded();
         }
      });
      return true;
   }

   @Override
   public void updateDragCursor(CefBrowser var1, int var2) {
   }

   private void createBrowserIfRequired(boolean var1) {
      long var2 = 0L;
      if (var1) {
         var2 = this.getWindowHandle();
      }

      if (this.getNativeRef("CefBrowser") == 0L) {
         if (this.getParentBrowser() != null) {
            this.createDevTools(this.getParentBrowser(), this.getClient(), var2, true, this.isTransparent_, null, this.getInspectAt());
         } else {
            this.createBrowser(this.getClient(), var2, this.getUrl(), true, this.isTransparent_, null, this.getRequestContext());
         }
      } else if (var1 && this.justCreated_) {
         this.notifyAfterParentChanged();
         this.setFocus(true);
         this.justCreated_ = false;
      }
   }

   private void notifyAfterParentChanged() {
      this.getClient().onAfterParentChanged(this);
   }

   @Override
   public boolean getScreenInfo(CefBrowser var1, CefScreenInfo var2) {
      var2.Set(this.scaleFactor_, this.depth, this.depth_per_component, false, this.browser_rect_.getBounds(), this.browser_rect_.getBounds());
      return true;
   }

   @Override
   public CompletableFuture<BufferedImage> createScreenshot(final boolean var1) {
      final int var2 = (int)Math.ceil(this.canvas_.getWidth() * this.scaleFactor_);
      final int var3 = (int)Math.ceil(this.canvas_.getHeight() * this.scaleFactor_);
      final GL2 var4 = this.canvas_.getGL().getGL2();
      final int var5 = this.renderer_.getTextureID();
      final boolean var6 = var5 == 0;
      final Callable var7 = new Callable<BufferedImage>() {
         public BufferedImage call() {
            BufferedImage var1x = new BufferedImage(var2, var3, 2);
            ByteBuffer var2x = GLBuffers.newDirectByteBuffer(var2 * var3 * 4);
            var4.getContext().makeCurrent();

            try {
               if (var6) {
                  var4.glReadPixels(0, 0, var2, var3, 6408, 5121, var2x);
               } else {
                  var4.glEnable(3553);
                  var4.glBindTexture(3553, var5);
                  var4.glGetTexImage(3553, 0, 6408, 5121, var2x);
                  var4.glDisable(3553);
               }
            } finally {
               var4.getContext().release();
            }

            for (int var3x = 0; var3x < var3; var3x++) {
               for (int var4x = 0; var4x < var2; var4x++) {
                  int var5x = var2x.get() & 255;
                  int var6x = var2x.get() & 255;
                  int var7x = var2x.get() & 255;
                  int var8 = var2x.get() & 255;
                  int var9 = var8 << 24 | var5x << 16 | var6x << 8 | var7x << 0;
                  var1x.setRGB(var4x, var6 ? var3 - var3x - 1 : var3x, var9);
               }
            }

            if (!var1 && CefBrowserOsr.this.scaleFactor_ != 1.0) {
               BufferedImage var12 = new BufferedImage(
                  (int)(var1x.getWidth() / CefBrowserOsr.this.scaleFactor_), (int)(var1x.getHeight() / CefBrowserOsr.this.scaleFactor_), 2
               );
               AffineTransform var13 = new AffineTransform();
               var13.scale(1.0 / CefBrowserOsr.this.scaleFactor_, 1.0 / CefBrowserOsr.this.scaleFactor_);
               AffineTransformOp var14 = new AffineTransformOp(var13, 2);
               return var14.filter(var1x, var12);
            } else {
               return var1x;
            }
         }
      };
      if (SwingUtilities.isEventDispatchThread()) {
         try {
            BufferedImage var11 = (BufferedImage)var7.call();
            return CompletableFuture.completedFuture(var11);
         } catch (Exception var10) {
            CompletableFuture var9 = new CompletableFuture();
            var9.completeExceptionally(var10);
            return var9;
         }
      } else {
         final CompletableFuture var8 = new CompletableFuture<BufferedImage>() {
            private void safeguardGet() {
               if (SwingUtilities.isEventDispatchThread()) {
                  throw new RuntimeException("Waiting on this Future using the AWT Event Thread is illegal, because it can potentially deadlock the thread.");
               }
            }

            public BufferedImage get() throws InterruptedException, ExecutionException {
               this.safeguardGet();
               return (BufferedImage)super.get();
            }

            public BufferedImage get(long var1, TimeUnit var3x) throws InterruptedException, ExecutionException, TimeoutException {
               this.safeguardGet();
               return (BufferedImage)super.get(var1, var3x);
            }
         };
         this.canvas_.addGLEventListener(new GLEventListener() {
            public void reshape(GLAutoDrawable var1, int var2x, int var3x, int var4x, int var5x) {
            }

            public void init(GLAutoDrawable var1) {
            }

            public void dispose(GLAutoDrawable var1) {
            }

            public void display(GLAutoDrawable var1) {
               CefBrowserOsr.this.canvas_.removeGLEventListener(this);

               try {
                  var8.complete(var7.call());
               } catch (Exception var3x) {
                  var8.completeExceptionally(var3x);
               }
            }
         });
         this.canvas_.repaint();
         return var8;
      }
   }

   private static final class SyntheticDragGestureRecognizer extends DragGestureRecognizer {
      public SyntheticDragGestureRecognizer(Component var1, int var2, MouseEvent var3) {
         super(new DragSource(), var1, var2);
         this.appendEvent(var3);
      }

      @Override
      protected void registerListeners() {
      }

      @Override
      protected void unregisterListeners() {
      }
   }
}
