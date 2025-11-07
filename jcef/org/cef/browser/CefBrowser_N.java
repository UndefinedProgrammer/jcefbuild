package org.cef.browser;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import javax.swing.SwingUtilities;
import org.cef.CefBrowserSettings;
import org.cef.CefClient;
import org.cef.callback.CefDragData;
import org.cef.callback.CefNativeAdapter;
import org.cef.callback.CefPdfPrintCallback;
import org.cef.callback.CefRunFileDialogCallback;
import org.cef.callback.CefStringVisitor;
import org.cef.handler.CefClientHandler;
import org.cef.handler.CefDialogHandler;
import org.cef.handler.CefRenderHandler;
import org.cef.handler.CefWindowHandler;
import org.cef.misc.CefPdfPrintSettings;
import org.cef.network.CefRequest;

abstract class CefBrowser_N extends CefNativeAdapter implements CefBrowser {
   private volatile boolean isPending_ = false;
   private final CefClient client_;
   private final String url_;
   private final CefRequestContext request_context_;
   private volatile CefBrowser_N parent_ = null;
   private volatile Point inspectAt_ = null;
   private volatile CefBrowser_N devTools_ = null;
   private volatile CefDevToolsClient devToolsClient_ = null;
   private boolean closeAllowed_ = false;
   private volatile boolean isClosed_ = false;
   private volatile boolean isClosing_ = false;
   private final CefBrowserSettings settings_;

   protected CefBrowser_N(CefClient var1, String var2, CefRequestContext var3, CefBrowser_N var4, Point var5, CefBrowserSettings var6) {
      this.client_ = var1;
      this.url_ = var2;
      this.request_context_ = var3;
      this.parent_ = var4;
      this.inspectAt_ = var5;
      if (var6 != null) {
         this.settings_ = var6.clone();
      } else {
         this.settings_ = new CefBrowserSettings();
      }
   }

   protected String getUrl() {
      return this.url_;
   }

   protected CefRequestContext getRequestContext() {
      return this.request_context_;
   }

   protected CefBrowser_N getParentBrowser() {
      return this.parent_;
   }

   protected Point getInspectAt() {
      return this.inspectAt_;
   }

   protected boolean isClosed() {
      return this.isClosed_;
   }

   @Override
   public CefClient getClient() {
      return this.client_;
   }

   @Override
   public CefRenderHandler getRenderHandler() {
      return null;
   }

   @Override
   public CefWindowHandler getWindowHandler() {
      return null;
   }

   @Override
   public synchronized void setCloseAllowed() {
      this.closeAllowed_ = true;
   }

   @Override
   public synchronized boolean doClose() {
      if (this.closeAllowed_) {
         return false;
      } else {
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               Component var1 = SwingUtilities.getRoot(CefBrowser_N.this.getUIComponent());
               if (var1 != null) {
                  var1.dispatchEvent(new WindowEvent((Window)var1, 201));
               }
            }
         });
         return true;
      }
   }

   @Override
   public synchronized void onBeforeClose() {
      this.isClosed_ = true;
      if (this.request_context_ != null) {
         this.request_context_.dispose();
      }

      if (this.parent_ != null) {
         this.parent_.closeDevTools();
         this.parent_.devTools_ = null;
         this.parent_ = null;
      }

      if (this.devToolsClient_ != null) {
         this.devToolsClient_.close();
      }
   }

   @Override
   public CefBrowser getDevTools() {
      return this.getDevTools(null);
   }

   @Override
   public synchronized CefBrowser getDevTools(Point var1) {
      if (this.devTools_ == null) {
         this.devTools_ = this.createDevToolsBrowser(this.client_, this.url_, this.request_context_, this, var1);
      }

      return this.devTools_;
   }

   @Override
   public synchronized CefDevToolsClient getDevToolsClient() {
      if (this.isPending_ && !this.isClosing_ && !this.isClosed_) {
         if (this.devToolsClient_ == null || this.devToolsClient_.isClosed()) {
            this.devToolsClient_ = new CefDevToolsClient(this);
         }

         return this.devToolsClient_;
      } else {
         return null;
      }
   }

   CompletableFuture<Integer> executeDevToolsMethod(final String var1, String var2) {
      final CompletableFuture var3 = new CompletableFuture();
      this.N_ExecuteDevToolsMethod(var1, var2, new CefBrowser_N.IntCallback() {
         @Override
         public void onComplete(int var1x) {
            if (var1x <= 0) {
               var3.completeExceptionally(new CefDevToolsClient.DevToolsException(String.format("Failed to execute DevTools method %s", var1)));
            } else {
               var3.complete(var1x);
            }
         }
      });
      return var3;
   }

   CefRegistration addDevToolsMessageObserver(CefDevToolsMessageObserver var1) {
      return this.N_AddDevToolsMessageObserver(var1);
   }

   protected abstract CefBrowser_N createDevToolsBrowser(CefClient var1, String var2, CefRequestContext var3, CefBrowser_N var4, Point var5);

   protected void createBrowser(CefClientHandler var1, long var2, String var4, boolean var5, boolean var6, Component var7, CefRequestContext var8) {
      if (this.getNativeRef("CefBrowser") == 0L && !this.isPending_) {
         try {
            this.N_CreateBrowser(var1, var2, var4, var5, var6, var7, var8, this.settings_);
         } catch (UnsatisfiedLinkError var10) {
            var10.printStackTrace();
         }
      }
   }

   private void notifyBrowserCreated() {
      this.isPending_ = true;
   }

   protected final void createDevTools(CefBrowser_N var1, CefClientHandler var2, long var3, boolean var5, boolean var6, Component var7, Point var8) {
      if (this.getNativeRef("CefBrowser") == 0L && !this.isPending_) {
         try {
            this.isPending_ = this.N_CreateDevTools(var1, var2, var3, var5, var6, var7, var8);
         } catch (UnsatisfiedLinkError var10) {
            var10.printStackTrace();
         }
      }
   }

   protected final long getWindowHandle(long var1) {
      try {
         return this.N_GetWindowHandle(var1);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return 0L;
      }
   }

   @Override
   protected void finalize() throws Throwable {
      this.close(true);
      super.finalize();
   }

   @Override
   public boolean canGoBack() {
      try {
         return this.N_CanGoBack();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void goBack() {
      try {
         this.N_GoBack();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public boolean canGoForward() {
      try {
         return this.N_CanGoForward();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void goForward() {
      try {
         this.N_GoForward();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public boolean isLoading() {
      try {
         return this.N_IsLoading();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void reload() {
      try {
         this.N_Reload();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void reloadIgnoreCache() {
      try {
         this.N_ReloadIgnoreCache();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void stopLoad() {
      try {
         this.N_StopLoad();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public int getIdentifier() {
      try {
         return this.N_GetIdentifier();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return -1;
      }
   }

   @Override
   public CefFrame getMainFrame() {
      try {
         return this.N_GetMainFrame();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public CefFrame getFocusedFrame() {
      try {
         return this.N_GetFocusedFrame();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public CefFrame getFrame(long var1) {
      try {
         return this.N_GetFrame(var1);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return null;
      }
   }

   @Override
   public CefFrame getFrame(String var1) {
      try {
         return this.N_GetFrame2(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public Vector<Long> getFrameIdentifiers() {
      try {
         return this.N_GetFrameIdentifiers();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public Vector<String> getFrameNames() {
      try {
         return this.N_GetFrameNames();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public int getFrameCount() {
      try {
         return this.N_GetFrameCount();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return -1;
      }
   }

   @Override
   public boolean isPopup() {
      try {
         return this.N_IsPopup();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean hasDocument() {
      try {
         return this.N_HasDocument();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void viewSource() {
      try {
         this.N_ViewSource();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void getSource(CefStringVisitor var1) {
      try {
         this.N_GetSource(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void getText(CefStringVisitor var1) {
      try {
         this.N_GetText(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void loadRequest(CefRequest var1) {
      try {
         this.N_LoadRequest(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void loadURL(String var1) {
      try {
         this.N_LoadURL(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void executeJavaScript(String var1, String var2, int var3) {
      try {
         this.N_ExecuteJavaScript(var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
      }
   }

   @Override
   public String getURL() {
      try {
         return this.N_GetURL();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return "";
      }
   }

   @Override
   public void close(boolean var1) {
      if (!this.isClosing_ && !this.isClosed_) {
         if (var1) {
            this.isClosing_ = true;
         }

         try {
            this.N_Close(var1);
         } catch (UnsatisfiedLinkError var3) {
            var3.printStackTrace();
         }
      }
   }

   @Override
   public void setFocus(boolean var1) {
      try {
         this.N_SetFocus(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setWindowVisibility(boolean var1) {
      try {
         this.N_SetWindowVisibility(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public double getZoomLevel() {
      try {
         return this.N_GetZoomLevel();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0.0;
      }
   }

   @Override
   public void setZoomLevel(double var1) {
      try {
         this.N_SetZoomLevel(var1);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   @Override
   public void runFileDialog(CefDialogHandler.FileDialogMode var1, String var2, String var3, Vector<String> var4, int var5, CefRunFileDialogCallback var6) {
      try {
         this.N_RunFileDialog(var1, var2, var3, var4, var5, var6);
      } catch (UnsatisfiedLinkError var8) {
         var8.printStackTrace();
      }
   }

   @Override
   public void startDownload(String var1) {
      try {
         this.N_StartDownload(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void print() {
      try {
         this.N_Print();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void printToPDF(String var1, CefPdfPrintSettings var2, CefPdfPrintCallback var3) {
      if (var1 != null && !var1.isEmpty()) {
         try {
            this.N_PrintToPDF(var1, var2, var3);
         } catch (UnsatisfiedLinkError var5) {
            var5.printStackTrace();
         }
      } else {
         throw new IllegalArgumentException("path was null or empty");
      }
   }

   @Override
   public void find(String var1, boolean var2, boolean var3, boolean var4) {
      try {
         this.N_Find(var1, var2, var3, var4);
      } catch (UnsatisfiedLinkError var6) {
         var6.printStackTrace();
      }
   }

   @Override
   public void stopFinding(boolean var1) {
      try {
         this.N_StopFinding(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected final void closeDevTools() {
      try {
         this.N_CloseDevTools();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void replaceMisspelling(String var1) {
      try {
         this.N_ReplaceMisspelling(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected final void wasResized(int var1, int var2) {
      try {
         this.N_WasResized(var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   protected final void invalidate() {
      try {
         this.N_Invalidate();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   protected final void sendKeyEvent(KeyEvent var1) {
      try {
         this.N_SendKeyEvent(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected final void sendMouseEvent(MouseEvent var1) {
      try {
         this.N_SendMouseEvent(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected final void sendMouseWheelEvent(MouseWheelEvent var1) {
      try {
         this.N_SendMouseWheelEvent(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected final void dragTargetDragEnter(CefDragData var1, Point var2, int var3, int var4) {
      try {
         this.N_DragTargetDragEnter(var1, var2, var3, var4);
      } catch (UnsatisfiedLinkError var6) {
         var6.printStackTrace();
      }
   }

   protected final void dragTargetDragOver(Point var1, int var2, int var3) {
      try {
         this.N_DragTargetDragOver(var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
      }
   }

   protected final void dragTargetDragLeave() {
      try {
         this.N_DragTargetDragLeave();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   protected final void dragTargetDrop(Point var1, int var2) {
      try {
         this.N_DragTargetDrop(var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   protected final void dragSourceEndedAt(Point var1, int var2) {
      try {
         this.N_DragSourceEndedAt(var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   protected final void dragSourceSystemDragEnded() {
      try {
         this.N_DragSourceSystemDragEnded();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   protected final void updateUI(Rectangle var1, Rectangle var2) {
      try {
         this.N_UpdateUI(var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   protected final void setParent(long var1, Component var3) {
      if (!this.isClosing_ && !this.isClosed_) {
         try {
            this.N_SetParent(var1, var3);
         } catch (UnsatisfiedLinkError var5) {
            var5.printStackTrace();
         }
      }
   }

   protected final void notifyMoveOrResizeStarted() {
      try {
         this.N_NotifyMoveOrResizeStarted();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void setWindowlessFrameRate(int var1) {
      try {
         this.N_SetWindowlessFrameRate(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public CompletableFuture<Integer> getWindowlessFrameRate() {
      CompletableFuture var1 = new CompletableFuture();

      try {
         this.N_GetWindowlessFrameRate(var1::complete);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         var1.complete(0);
      }

      return var1;
   }

   private final native boolean N_CreateBrowser(
      CefClientHandler var1, long var2, String var4, boolean var5, boolean var6, Component var7, CefRequestContext var8, CefBrowserSettings var9
   );

   private final native boolean N_CreateDevTools(CefBrowser var1, CefClientHandler var2, long var3, boolean var5, boolean var6, Component var7, Point var8);

   private final native void N_ExecuteDevToolsMethod(String var1, String var2, CefBrowser_N.IntCallback var3);

   private final native CefRegistration N_AddDevToolsMessageObserver(CefDevToolsMessageObserver var1);

   private final native long N_GetWindowHandle(long var1);

   private final native boolean N_CanGoBack();

   private final native void N_GoBack();

   private final native boolean N_CanGoForward();

   private final native void N_GoForward();

   private final native boolean N_IsLoading();

   private final native void N_Reload();

   private final native void N_ReloadIgnoreCache();

   private final native void N_StopLoad();

   private final native int N_GetIdentifier();

   private final native CefFrame N_GetMainFrame();

   private final native CefFrame N_GetFocusedFrame();

   private final native CefFrame N_GetFrame(long var1);

   private final native CefFrame N_GetFrame2(String var1);

   private final native Vector<Long> N_GetFrameIdentifiers();

   private final native Vector<String> N_GetFrameNames();

   private final native int N_GetFrameCount();

   private final native boolean N_IsPopup();

   private final native boolean N_HasDocument();

   private final native void N_ViewSource();

   private final native void N_GetSource(CefStringVisitor var1);

   private final native void N_GetText(CefStringVisitor var1);

   private final native void N_LoadRequest(CefRequest var1);

   private final native void N_LoadURL(String var1);

   private final native void N_ExecuteJavaScript(String var1, String var2, int var3);

   private final native String N_GetURL();

   private final native void N_Close(boolean var1);

   private final native void N_SetFocus(boolean var1);

   private final native void N_SetWindowVisibility(boolean var1);

   private final native double N_GetZoomLevel();

   private final native void N_SetZoomLevel(double var1);

   private final native void N_RunFileDialog(
      CefDialogHandler.FileDialogMode var1, String var2, String var3, Vector<String> var4, int var5, CefRunFileDialogCallback var6
   );

   private final native void N_StartDownload(String var1);

   private final native void N_Print();

   private final native void N_PrintToPDF(String var1, CefPdfPrintSettings var2, CefPdfPrintCallback var3);

   private final native void N_Find(String var1, boolean var2, boolean var3, boolean var4);

   private final native void N_StopFinding(boolean var1);

   private final native void N_CloseDevTools();

   private final native void N_ReplaceMisspelling(String var1);

   private final native void N_WasResized(int var1, int var2);

   private final native void N_Invalidate();

   private final native void N_SendKeyEvent(KeyEvent var1);

   private final native void N_SendMouseEvent(MouseEvent var1);

   private final native void N_SendMouseWheelEvent(MouseWheelEvent var1);

   private final native void N_DragTargetDragEnter(CefDragData var1, Point var2, int var3, int var4);

   private final native void N_DragTargetDragOver(Point var1, int var2, int var3);

   private final native void N_DragTargetDragLeave();

   private final native void N_DragTargetDrop(Point var1, int var2);

   private final native void N_DragSourceEndedAt(Point var1, int var2);

   private final native void N_DragSourceSystemDragEnded();

   private final native void N_UpdateUI(Rectangle var1, Rectangle var2);

   private final native void N_SetParent(long var1, Component var3);

   private final native void N_NotifyMoveOrResizeStarted();

   private final native void N_SetWindowlessFrameRate(int var1);

   private final native void N_GetWindowlessFrameRate(CefBrowser_N.IntCallback var1);

   private interface IntCallback {
      void onComplete(int var1);
   }
}
