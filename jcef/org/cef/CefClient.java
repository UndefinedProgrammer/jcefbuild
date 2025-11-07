package org.cef;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Vector;
import java.util.function.Consumer;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefBrowserFactory;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.browser.CefPaintEvent;
import org.cef.browser.CefRequestContext;
import org.cef.callback.CefAuthCallback;
import org.cef.callback.CefBeforeDownloadCallback;
import org.cef.callback.CefCallback;
import org.cef.callback.CefContextMenuParams;
import org.cef.callback.CefDownloadItem;
import org.cef.callback.CefDownloadItemCallback;
import org.cef.callback.CefDragData;
import org.cef.callback.CefFileDialogCallback;
import org.cef.callback.CefJSDialogCallback;
import org.cef.callback.CefMenuModel;
import org.cef.callback.CefPrintDialogCallback;
import org.cef.callback.CefPrintJobCallback;
import org.cef.handler.CefClientHandler;
import org.cef.handler.CefContextMenuHandler;
import org.cef.handler.CefDialogHandler;
import org.cef.handler.CefDisplayHandler;
import org.cef.handler.CefDownloadHandler;
import org.cef.handler.CefDragHandler;
import org.cef.handler.CefFocusHandler;
import org.cef.handler.CefJSDialogHandler;
import org.cef.handler.CefKeyboardHandler;
import org.cef.handler.CefLifeSpanHandler;
import org.cef.handler.CefLoadHandler;
import org.cef.handler.CefPrintHandler;
import org.cef.handler.CefRenderHandler;
import org.cef.handler.CefRequestHandler;
import org.cef.handler.CefResourceRequestHandler;
import org.cef.handler.CefScreenInfo;
import org.cef.handler.CefWindowHandler;
import org.cef.misc.BoolRef;
import org.cef.misc.CefPrintSettings;
import org.cef.network.CefRequest;

public class CefClient
   extends CefClientHandler
   implements CefContextMenuHandler,
   CefDialogHandler,
   CefDisplayHandler,
   CefDownloadHandler,
   CefDragHandler,
   CefFocusHandler,
   CefJSDialogHandler,
   CefKeyboardHandler,
   CefLifeSpanHandler,
   CefLoadHandler,
   CefPrintHandler,
   CefRenderHandler,
   CefRequestHandler,
   CefWindowHandler {
   private HashMap<Integer, CefBrowser> browser_ = new HashMap<>();
   private CefContextMenuHandler contextMenuHandler_ = null;
   private CefDialogHandler dialogHandler_ = null;
   private CefDisplayHandler displayHandler_ = null;
   private CefDownloadHandler downloadHandler_ = null;
   private CefDragHandler dragHandler_ = null;
   private CefFocusHandler focusHandler_ = null;
   private CefJSDialogHandler jsDialogHandler_ = null;
   private CefKeyboardHandler keyboardHandler_ = null;
   private CefLifeSpanHandler lifeSpanHandler_ = null;
   private CefLoadHandler loadHandler_ = null;
   private CefPrintHandler printHandler_ = null;
   private CefRequestHandler requestHandler_ = null;
   private boolean isDisposed_ = false;
   private volatile CefBrowser focusedBrowser_ = null;
   private final PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent var1) {
         if (CefClient.this.focusedBrowser_ != null) {
            Component var2 = CefClient.this.focusedBrowser_.getUIComponent();
            Object var3 = var1.getOldValue();
            if (CefClient.this.isPartOf(var3, var2)) {
               CefClient.this.focusedBrowser_.setFocus(false);
               CefClient.this.focusedBrowser_ = null;
            }
         }
      }
   };

   CefClient() throws UnsatisfiedLinkError {
      KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(this.propertyChangeListener);
   }

   private boolean isPartOf(Object var1, Component var2) {
      if (var1 == var2) {
         return true;
      } else {
         if (var1 instanceof Container) {
            Component[] var3 = ((Container)var1).getComponents();
            int var5 = var3.length;
            byte var6 = 0;
            if (var6 < var5) {
               Component var7 = var3[var6];
               return this.isPartOf(var7, var2);
            }
         }

         return false;
      }
   }

   @Override
   public void dispose() {
      this.isDisposed_ = true;
      this.cleanupBrowser(-1);
   }

   public CefBrowser createBrowser(String var1, boolean var2, boolean var3) {
      return this.createBrowser(var1, var2, var3, null);
   }

   public CefBrowser createBrowser(String var1, boolean var2, boolean var3, CefRequestContext var4) {
      if (this.isDisposed_) {
         throw new IllegalStateException("Can't create browser. CefClient is disposed");
      } else {
         return CefBrowserFactory.create(this, var1, var2, var3, var4, null);
      }
   }

   public CefBrowser createBrowser(String var1, boolean var2, boolean var3, CefRequestContext var4, CefBrowserSettings var5) {
      if (this.isDisposed_) {
         throw new IllegalStateException("Can't create browser. CefClient is disposed");
      } else {
         return CefBrowserFactory.create(this, var1, var2, var3, var4, var5);
      }
   }

   @Override
   protected CefBrowser getBrowser(int var1) {
      synchronized (this.browser_) {
         return this.browser_.get(new Integer(var1));
      }
   }

   @Override
   protected Object[] getAllBrowser() {
      synchronized (this.browser_) {
         return this.browser_.values().toArray();
      }
   }

   @Override
   protected CefContextMenuHandler getContextMenuHandler() {
      return this;
   }

   @Override
   protected CefDialogHandler getDialogHandler() {
      return this;
   }

   @Override
   protected CefDisplayHandler getDisplayHandler() {
      return this;
   }

   @Override
   protected CefDownloadHandler getDownloadHandler() {
      return this;
   }

   @Override
   protected CefDragHandler getDragHandler() {
      return this;
   }

   @Override
   protected CefFocusHandler getFocusHandler() {
      return this;
   }

   @Override
   protected CefJSDialogHandler getJSDialogHandler() {
      return this;
   }

   @Override
   protected CefKeyboardHandler getKeyboardHandler() {
      return this;
   }

   @Override
   protected CefLifeSpanHandler getLifeSpanHandler() {
      return this;
   }

   @Override
   protected CefLoadHandler getLoadHandler() {
      return this;
   }

   @Override
   protected CefPrintHandler getPrintHandler() {
      return this;
   }

   @Override
   protected CefRenderHandler getRenderHandler() {
      return this;
   }

   @Override
   protected CefRequestHandler getRequestHandler() {
      return this;
   }

   @Override
   protected CefWindowHandler getWindowHandler() {
      return this;
   }

   public CefClient addContextMenuHandler(CefContextMenuHandler var1) {
      if (this.contextMenuHandler_ == null) {
         this.contextMenuHandler_ = var1;
      }

      return this;
   }

   public void removeContextMenuHandler() {
      this.contextMenuHandler_ = null;
   }

   @Override
   public void onBeforeContextMenu(CefBrowser var1, CefFrame var2, CefContextMenuParams var3, CefMenuModel var4) {
      if (this.contextMenuHandler_ != null && var1 != null) {
         this.contextMenuHandler_.onBeforeContextMenu(var1, var2, var3, var4);
      }
   }

   @Override
   public boolean onContextMenuCommand(CefBrowser var1, CefFrame var2, CefContextMenuParams var3, int var4, int var5) {
      return this.contextMenuHandler_ != null && var1 != null ? this.contextMenuHandler_.onContextMenuCommand(var1, var2, var3, var4, var5) : false;
   }

   @Override
   public void onContextMenuDismissed(CefBrowser var1, CefFrame var2) {
      if (this.contextMenuHandler_ != null && var1 != null) {
         this.contextMenuHandler_.onContextMenuDismissed(var1, var2);
      }
   }

   public CefClient addDialogHandler(CefDialogHandler var1) {
      if (this.dialogHandler_ == null) {
         this.dialogHandler_ = var1;
      }

      return this;
   }

   public void removeDialogHandler() {
      this.dialogHandler_ = null;
   }

   @Override
   public boolean onFileDialog(CefBrowser var1, CefDialogHandler.FileDialogMode var2, String var3, String var4, Vector<String> var5, CefFileDialogCallback var6) {
      return this.dialogHandler_ != null && var1 != null ? this.dialogHandler_.onFileDialog(var1, var2, var3, var4, var5, var6) : false;
   }

   public CefClient addDisplayHandler(CefDisplayHandler var1) {
      if (this.displayHandler_ == null) {
         this.displayHandler_ = var1;
      }

      return this;
   }

   public void removeDisplayHandler() {
      this.displayHandler_ = null;
   }

   @Override
   public void onAddressChange(CefBrowser var1, CefFrame var2, String var3) {
      if (this.displayHandler_ != null && var1 != null) {
         this.displayHandler_.onAddressChange(var1, var2, var3);
      }
   }

   @Override
   public void onTitleChange(CefBrowser var1, String var2) {
      if (this.displayHandler_ != null && var1 != null) {
         this.displayHandler_.onTitleChange(var1, var2);
      }
   }

   @Override
   public void OnFullscreenModeChange(CefBrowser var1, boolean var2) {
      if (this.displayHandler_ != null && var1 != null) {
         this.displayHandler_.OnFullscreenModeChange(var1, var2);
      }
   }

   @Override
   public boolean onTooltip(CefBrowser var1, String var2) {
      return this.displayHandler_ != null && var1 != null ? this.displayHandler_.onTooltip(var1, var2) : false;
   }

   @Override
   public void onStatusMessage(CefBrowser var1, String var2) {
      if (this.displayHandler_ != null && var1 != null) {
         this.displayHandler_.onStatusMessage(var1, var2);
      }
   }

   @Override
   public boolean onConsoleMessage(CefBrowser var1, CefSettings.LogSeverity var2, String var3, String var4, int var5) {
      return this.displayHandler_ != null && var1 != null ? this.displayHandler_.onConsoleMessage(var1, var2, var3, var4, var5) : false;
   }

   @Override
   public boolean onCursorChange(CefBrowser var1, int var2) {
      if (var1 == null) {
         return false;
      } else if (this.displayHandler_ != null && this.displayHandler_.onCursorChange(var1, var2)) {
         return true;
      } else {
         CefRenderHandler var3 = var1.getRenderHandler();
         return var3 != null ? var3.onCursorChange(var1, var2) : false;
      }
   }

   public CefClient addDownloadHandler(CefDownloadHandler var1) {
      if (this.downloadHandler_ == null) {
         this.downloadHandler_ = var1;
      }

      return this;
   }

   public void removeDownloadHandler() {
      this.downloadHandler_ = null;
   }

   @Override
   public void onBeforeDownload(CefBrowser var1, CefDownloadItem var2, String var3, CefBeforeDownloadCallback var4) {
      if (this.downloadHandler_ != null && var1 != null) {
         this.downloadHandler_.onBeforeDownload(var1, var2, var3, var4);
      }
   }

   @Override
   public void onDownloadUpdated(CefBrowser var1, CefDownloadItem var2, CefDownloadItemCallback var3) {
      if (this.downloadHandler_ != null && var1 != null) {
         this.downloadHandler_.onDownloadUpdated(var1, var2, var3);
      }
   }

   public CefClient addDragHandler(CefDragHandler var1) {
      if (this.dragHandler_ == null) {
         this.dragHandler_ = var1;
      }

      return this;
   }

   public void removeDragHandler() {
      this.dragHandler_ = null;
   }

   @Override
   public boolean onDragEnter(CefBrowser var1, CefDragData var2, int var3) {
      return this.dragHandler_ != null && var1 != null ? this.dragHandler_.onDragEnter(var1, var2, var3) : false;
   }

   public CefClient addFocusHandler(CefFocusHandler var1) {
      if (this.focusHandler_ == null) {
         this.focusHandler_ = var1;
      }

      return this;
   }

   public void removeFocusHandler() {
      this.focusHandler_ = null;
   }

   @Override
   public void onTakeFocus(CefBrowser var1, boolean var2) {
      if (var1 != null) {
         var1.setFocus(false);
         Container var3 = var1.getUIComponent().getParent();
         if (var3 != null) {
            FocusTraversalPolicy var4;
            for (var4 = null; var3 != null; var3 = var3.getParent()) {
               var4 = var3.getFocusTraversalPolicy();
               if (var4 != null) {
                  break;
               }
            }

            if (var4 != null) {
               Component var5 = var2 ? var4.getComponentAfter(var3, var1.getUIComponent()) : var4.getComponentBefore(var3, var1.getUIComponent());
               if (var5 == null) {
                  var4.getDefaultComponent(var3).requestFocus();
               } else {
                  var5.requestFocus();
               }
            }
         }

         this.focusedBrowser_ = null;
         if (this.focusHandler_ != null) {
            this.focusHandler_.onTakeFocus(var1, var2);
         }
      }
   }

   @Override
   public boolean onSetFocus(CefBrowser var1, CefFocusHandler.FocusSource var2) {
      if (var1 == null) {
         return false;
      } else {
         boolean var3 = false;
         if (this.focusHandler_ != null) {
            var3 = this.focusHandler_.onSetFocus(var1, var2);
         }

         return var3;
      }
   }

   @Override
   public void onGotFocus(CefBrowser var1) {
      if (var1 != null) {
         this.focusedBrowser_ = var1;
         var1.setFocus(true);
         if (this.focusHandler_ != null) {
            this.focusHandler_.onGotFocus(var1);
         }
      }
   }

   public CefClient addJSDialogHandler(CefJSDialogHandler var1) {
      if (this.jsDialogHandler_ == null) {
         this.jsDialogHandler_ = var1;
      }

      return this;
   }

   public void removeJSDialogHandler() {
      this.jsDialogHandler_ = null;
   }

   @Override
   public boolean onJSDialog(
      CefBrowser var1, String var2, CefJSDialogHandler.JSDialogType var3, String var4, String var5, CefJSDialogCallback var6, BoolRef var7
   ) {
      return this.jsDialogHandler_ != null && var1 != null ? this.jsDialogHandler_.onJSDialog(var1, var2, var3, var4, var5, var6, var7) : false;
   }

   @Override
   public boolean onBeforeUnloadDialog(CefBrowser var1, String var2, boolean var3, CefJSDialogCallback var4) {
      return this.jsDialogHandler_ != null && var1 != null ? this.jsDialogHandler_.onBeforeUnloadDialog(var1, var2, var3, var4) : false;
   }

   @Override
   public void onResetDialogState(CefBrowser var1) {
      if (this.jsDialogHandler_ != null && var1 != null) {
         this.jsDialogHandler_.onResetDialogState(var1);
      }
   }

   @Override
   public void onDialogClosed(CefBrowser var1) {
      if (this.jsDialogHandler_ != null && var1 != null) {
         this.jsDialogHandler_.onDialogClosed(var1);
      }
   }

   public CefClient addKeyboardHandler(CefKeyboardHandler var1) {
      if (this.keyboardHandler_ == null) {
         this.keyboardHandler_ = var1;
      }

      return this;
   }

   public void removeKeyboardHandler() {
      this.keyboardHandler_ = null;
   }

   @Override
   public boolean onPreKeyEvent(CefBrowser var1, CefKeyboardHandler.CefKeyEvent var2, BoolRef var3) {
      return this.keyboardHandler_ != null && var1 != null ? this.keyboardHandler_.onPreKeyEvent(var1, var2, var3) : false;
   }

   @Override
   public boolean onKeyEvent(CefBrowser var1, CefKeyboardHandler.CefKeyEvent var2) {
      return this.keyboardHandler_ != null && var1 != null ? this.keyboardHandler_.onKeyEvent(var1, var2) : false;
   }

   public CefClient addLifeSpanHandler(CefLifeSpanHandler var1) {
      if (this.lifeSpanHandler_ == null) {
         this.lifeSpanHandler_ = var1;
      }

      return this;
   }

   public void removeLifeSpanHandler() {
      this.lifeSpanHandler_ = null;
   }

   @Override
   public boolean onBeforePopup(CefBrowser var1, CefFrame var2, String var3, String var4) {
      if (this.isDisposed_) {
         return true;
      } else {
         return this.lifeSpanHandler_ != null && var1 != null ? this.lifeSpanHandler_.onBeforePopup(var1, var2, var3, var4) : false;
      }
   }

   @Override
   public void onAfterCreated(CefBrowser var1) {
      if (var1 != null) {
         Integer var2 = var1.getIdentifier();
         synchronized (this.browser_) {
            this.browser_.put(var2, var1);
         }

         if (this.lifeSpanHandler_ != null) {
            this.lifeSpanHandler_.onAfterCreated(var1);
         }
      }
   }

   @Override
   public void onAfterParentChanged(CefBrowser var1) {
      if (var1 != null) {
         if (this.lifeSpanHandler_ != null) {
            this.lifeSpanHandler_.onAfterParentChanged(var1);
         }
      }
   }

   @Override
   public boolean doClose(CefBrowser var1) {
      if (var1 == null) {
         return false;
      } else {
         return this.lifeSpanHandler_ != null ? this.lifeSpanHandler_.doClose(var1) : var1.doClose();
      }
   }

   @Override
   public void onBeforeClose(CefBrowser var1) {
      if (var1 != null) {
         if (this.lifeSpanHandler_ != null) {
            this.lifeSpanHandler_.onBeforeClose(var1);
         }

         var1.onBeforeClose();
         this.cleanupBrowser(var1.getIdentifier());
      }
   }

   private void cleanupBrowser(int var1) {
      synchronized (this.browser_) {
         if (var1 >= 0) {
            this.browser_.remove(var1);
         } else if (!this.browser_.isEmpty()) {
            for (CefBrowser var5 : this.browser_.values()) {
               var5.close(true);
            }

            return;
         }

         if (this.browser_.isEmpty() && this.isDisposed_) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removePropertyChangeListener(this.propertyChangeListener);
            this.removeContextMenuHandler(this);
            this.removeDialogHandler(this);
            this.removeDisplayHandler(this);
            this.removeDownloadHandler(this);
            this.removeDragHandler(this);
            this.removeFocusHandler(this);
            this.removeJSDialogHandler(this);
            this.removeKeyboardHandler(this);
            this.removeLifeSpanHandler(this);
            this.removeLoadHandler(this);
            this.removePrintHandler(this);
            this.removeRenderHandler(this);
            this.removeRequestHandler(this);
            this.removeWindowHandler(this);
            super.dispose();
            CefApp.getInstance().clientWasDisposed(this);
         }
      }
   }

   public CefClient addLoadHandler(CefLoadHandler var1) {
      if (this.loadHandler_ == null) {
         this.loadHandler_ = var1;
      }

      return this;
   }

   public void removeLoadHandler() {
      this.loadHandler_ = null;
   }

   @Override
   public void onLoadingStateChange(CefBrowser var1, boolean var2, boolean var3, boolean var4) {
      if (this.loadHandler_ != null && var1 != null) {
         this.loadHandler_.onLoadingStateChange(var1, var2, var3, var4);
      }
   }

   @Override
   public void onLoadStart(CefBrowser var1, CefFrame var2, CefRequest.TransitionType var3) {
      if (this.loadHandler_ != null && var1 != null) {
         this.loadHandler_.onLoadStart(var1, var2, var3);
      }
   }

   @Override
   public void onLoadEnd(CefBrowser var1, CefFrame var2, int var3) {
      if (this.loadHandler_ != null && var1 != null) {
         this.loadHandler_.onLoadEnd(var1, var2, var3);
      }
   }

   @Override
   public void onLoadError(CefBrowser var1, CefFrame var2, CefLoadHandler.ErrorCode var3, String var4, String var5) {
      if (this.loadHandler_ != null && var1 != null) {
         this.loadHandler_.onLoadError(var1, var2, var3, var4, var5);
      }
   }

   public CefClient addPrintHandler(CefPrintHandler var1) {
      if (this.printHandler_ == null) {
         this.printHandler_ = var1;
      }

      return this;
   }

   public void removePrintHandler() {
      this.printHandler_ = null;
   }

   @Override
   public void onPrintStart(CefBrowser var1) {
      if (this.printHandler_ != null && var1 != null) {
         this.printHandler_.onPrintStart(var1);
      }
   }

   @Override
   public void onPrintSettings(CefBrowser var1, CefPrintSettings var2, boolean var3) {
      if (this.printHandler_ != null && var1 != null) {
         this.printHandler_.onPrintSettings(var1, var2, var3);
      }
   }

   @Override
   public boolean onPrintDialog(CefBrowser var1, boolean var2, CefPrintDialogCallback var3) {
      return this.printHandler_ != null && var1 != null ? this.printHandler_.onPrintDialog(var1, var2, var3) : false;
   }

   @Override
   public boolean onPrintJob(CefBrowser var1, String var2, String var3, CefPrintJobCallback var4) {
      return this.printHandler_ != null && var1 != null ? this.printHandler_.onPrintJob(var1, var2, var3, var4) : false;
   }

   @Override
   public void onPrintReset(CefBrowser var1) {
      if (this.printHandler_ != null && var1 != null) {
         this.printHandler_.onPrintReset(var1);
      }
   }

   @Override
   public Dimension getPdfPaperSize(CefBrowser var1, int var2) {
      return this.printHandler_ != null && var1 != null ? this.printHandler_.getPdfPaperSize(var1, var2) : null;
   }

   @Override
   public synchronized void addMessageRouter(CefMessageRouter var1) {
      super.addMessageRouter(var1);
   }

   @Override
   public synchronized void removeMessageRouter(CefMessageRouter var1) {
      super.removeMessageRouter(var1);
   }

   @Override
   public Rectangle getViewRect(CefBrowser var1) {
      if (var1 == null) {
         return new Rectangle(0, 0, 0, 0);
      } else {
         CefRenderHandler var2 = var1.getRenderHandler();
         return var2 != null ? var2.getViewRect(var1) : new Rectangle(0, 0, 0, 0);
      }
   }

   @Override
   public Point getScreenPoint(CefBrowser var1, Point var2) {
      if (var1 == null) {
         return new Point(0, 0);
      } else {
         CefRenderHandler var3 = var1.getRenderHandler();
         return var3 != null ? var3.getScreenPoint(var1, var2) : new Point(0, 0);
      }
   }

   @Override
   public void onPopupShow(CefBrowser var1, boolean var2) {
      if (var1 != null) {
         CefRenderHandler var3 = var1.getRenderHandler();
         if (var3 != null) {
            var3.onPopupShow(var1, var2);
         }
      }
   }

   @Override
   public void onPopupSize(CefBrowser var1, Rectangle var2) {
      if (var1 != null) {
         CefRenderHandler var3 = var1.getRenderHandler();
         if (var3 != null) {
            var3.onPopupSize(var1, var2);
         }
      }
   }

   @Override
   public void onPaint(CefBrowser var1, boolean var2, Rectangle[] var3, ByteBuffer var4, int var5, int var6) {
      if (var1 != null) {
         CefRenderHandler var7 = var1.getRenderHandler();
         if (var7 != null) {
            var7.onPaint(var1, var2, var3, var4, var5, var6);
         }
      }
   }

   @Override
   public void addOnPaintListener(Consumer<CefPaintEvent> var1) {
   }

   @Override
   public void setOnPaintListener(Consumer<CefPaintEvent> var1) {
   }

   @Override
   public void removeOnPaintListener(Consumer<CefPaintEvent> var1) {
   }

   @Override
   public boolean startDragging(CefBrowser var1, CefDragData var2, int var3, int var4, int var5) {
      if (var1 == null) {
         return false;
      } else {
         CefRenderHandler var6 = var1.getRenderHandler();
         return var6 != null ? var6.startDragging(var1, var2, var3, var4, var5) : false;
      }
   }

   @Override
   public void updateDragCursor(CefBrowser var1, int var2) {
      if (var1 != null) {
         CefRenderHandler var3 = var1.getRenderHandler();
         if (var3 != null) {
            var3.updateDragCursor(var1, var2);
         }
      }
   }

   public CefClient addRequestHandler(CefRequestHandler var1) {
      if (this.requestHandler_ == null) {
         this.requestHandler_ = var1;
      }

      return this;
   }

   public void removeRequestHandler() {
      this.requestHandler_ = null;
   }

   @Override
   public boolean onBeforeBrowse(CefBrowser var1, CefFrame var2, CefRequest var3, boolean var4, boolean var5) {
      return this.requestHandler_ != null && var1 != null ? this.requestHandler_.onBeforeBrowse(var1, var2, var3, var4, var5) : false;
   }

   @Override
   public boolean onOpenURLFromTab(CefBrowser var1, CefFrame var2, String var3, boolean var4) {
      if (this.isDisposed_) {
         return true;
      } else {
         return this.requestHandler_ != null && var1 != null ? this.requestHandler_.onOpenURLFromTab(var1, var2, var3, var4) : false;
      }
   }

   @Override
   public CefResourceRequestHandler getResourceRequestHandler(
      CefBrowser var1, CefFrame var2, CefRequest var3, boolean var4, boolean var5, String var6, BoolRef var7
   ) {
      return this.requestHandler_ != null && var1 != null ? this.requestHandler_.getResourceRequestHandler(var1, var2, var3, var4, var5, var6, var7) : null;
   }

   @Override
   public boolean getAuthCredentials(CefBrowser var1, String var2, boolean var3, String var4, int var5, String var6, String var7, CefAuthCallback var8) {
      return this.requestHandler_ != null && var1 != null ? this.requestHandler_.getAuthCredentials(var1, var2, var3, var4, var5, var6, var7, var8) : false;
   }

   @Override
   public boolean onCertificateError(CefBrowser var1, CefLoadHandler.ErrorCode var2, String var3, CefCallback var4) {
      return this.requestHandler_ != null ? this.requestHandler_.onCertificateError(var1, var2, var3, var4) : false;
   }

   @Override
   public void onRenderProcessTerminated(CefBrowser var1, CefRequestHandler.TerminationStatus var2) {
      if (this.requestHandler_ != null) {
         this.requestHandler_.onRenderProcessTerminated(var1, var2);
      }
   }

   @Override
   public Rectangle getRect(CefBrowser var1) {
      if (var1 == null) {
         return new Rectangle(0, 0, 0, 0);
      } else {
         CefWindowHandler var2 = var1.getWindowHandler();
         return var2 != null ? var2.getRect(var1) : new Rectangle(0, 0, 0, 0);
      }
   }

   @Override
   public void onMouseEvent(CefBrowser var1, int var2, int var3, int var4, int var5, int var6) {
      if (var1 != null) {
         CefWindowHandler var7 = var1.getWindowHandler();
         if (var7 != null) {
            var7.onMouseEvent(var1, var2, var3, var4, var5, var6);
         }
      }
   }

   @Override
   public boolean getScreenInfo(CefBrowser var1, CefScreenInfo var2) {
      return false;
   }
}
