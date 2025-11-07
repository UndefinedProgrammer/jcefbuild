package org.cef.handler;

import java.util.HashMap;
import java.util.Vector;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;
import org.cef.callback.CefNative;

public abstract class CefClientHandler implements CefNative {
   private HashMap<String, Long> N_CefHandle = new HashMap<>();
   private Vector<CefMessageRouter> msgRouters = new Vector<>();

   @Override
   public void setNativeRef(String var1, long var2) {
      synchronized (this.N_CefHandle) {
         this.N_CefHandle.put(var1, var2);
      }
   }

   @Override
   public long getNativeRef(String var1) {
      synchronized (this.N_CefHandle) {
         return this.N_CefHandle.containsKey(var1) ? this.N_CefHandle.get(var1) : 0L;
      }
   }

   public CefClientHandler() {
      try {
         this.N_CefClientHandler_CTOR();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   protected void dispose() {
      try {
         for (int var1 = 0; var1 < this.msgRouters.size(); var1++) {
            this.msgRouters.get(var1).dispose();
         }

         this.msgRouters.clear();
         this.N_CefClientHandler_DTOR();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   protected abstract CefBrowser getBrowser(int var1);

   protected abstract Object[] getAllBrowser();

   protected abstract CefContextMenuHandler getContextMenuHandler();

   protected abstract CefDialogHandler getDialogHandler();

   protected abstract CefDisplayHandler getDisplayHandler();

   protected abstract CefDownloadHandler getDownloadHandler();

   protected abstract CefDragHandler getDragHandler();

   protected abstract CefFocusHandler getFocusHandler();

   protected abstract CefJSDialogHandler getJSDialogHandler();

   protected abstract CefKeyboardHandler getKeyboardHandler();

   protected abstract CefLifeSpanHandler getLifeSpanHandler();

   protected abstract CefLoadHandler getLoadHandler();

   protected abstract CefPrintHandler getPrintHandler();

   protected abstract CefRenderHandler getRenderHandler();

   protected abstract CefRequestHandler getRequestHandler();

   protected abstract CefWindowHandler getWindowHandler();

   protected synchronized void addMessageRouter(CefMessageRouter var1) {
      try {
         this.msgRouters.add(var1);
         this.N_addMessageRouter(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeContextMenuHandler(CefContextMenuHandler var1) {
      try {
         this.N_removeContextMenuHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeDialogHandler(CefDialogHandler var1) {
      try {
         this.N_removeDialogHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeDisplayHandler(CefDisplayHandler var1) {
      try {
         this.N_removeDisplayHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeDownloadHandler(CefDisplayHandler var1) {
      try {
         this.N_removeDownloadHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeDragHandler(CefDragHandler var1) {
      try {
         this.N_removeDragHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeFocusHandler(CefFocusHandler var1) {
      try {
         this.N_removeFocusHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeJSDialogHandler(CefJSDialogHandler var1) {
      try {
         this.N_removeJSDialogHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeKeyboardHandler(CefKeyboardHandler var1) {
      try {
         this.N_removeKeyboardHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeLifeSpanHandler(CefLifeSpanHandler var1) {
      try {
         this.N_removeLifeSpanHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeLoadHandler(CefLoadHandler var1) {
      try {
         this.N_removeLoadHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removePrintHandler(CefPrintHandler var1) {
      try {
         this.N_removePrintHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected synchronized void removeMessageRouter(CefMessageRouter var1) {
      try {
         this.msgRouters.remove(var1);
         this.N_removeMessageRouter(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeRenderHandler(CefRenderHandler var1) {
      try {
         this.N_removeRenderHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeRequestHandler(CefRequestHandler var1) {
      try {
         this.N_removeRequestHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   protected void removeWindowHandler(CefWindowHandler var1) {
      try {
         this.N_removeWindowHandler(var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   private final native void N_CefClientHandler_CTOR();

   private final native void N_addMessageRouter(CefMessageRouter var1);

   private final native void N_removeContextMenuHandler(CefContextMenuHandler var1);

   private final native void N_removeDialogHandler(CefDialogHandler var1);

   private final native void N_removeDisplayHandler(CefDisplayHandler var1);

   private final native void N_removeDownloadHandler(CefDisplayHandler var1);

   private final native void N_removeDragHandler(CefDragHandler var1);

   private final native void N_removeFocusHandler(CefFocusHandler var1);

   private final native void N_removeJSDialogHandler(CefJSDialogHandler var1);

   private final native void N_removeKeyboardHandler(CefKeyboardHandler var1);

   private final native void N_removeLifeSpanHandler(CefLifeSpanHandler var1);

   private final native void N_removeLoadHandler(CefLoadHandler var1);

   private final native void N_removePrintHandler(CefPrintHandler var1);

   private final native void N_removeMessageRouter(CefMessageRouter var1);

   private final native void N_removeRenderHandler(CefRenderHandler var1);

   private final native void N_removeRequestHandler(CefRequestHandler var1);

   private final native void N_removeWindowHandler(CefWindowHandler var1);

   private final native void N_CefClientHandler_DTOR();
}
