package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.callback.CefJSDialogCallback;
import org.cef.misc.BoolRef;

public abstract class CefJSDialogHandlerAdapter implements CefJSDialogHandler {
   @Override
   public boolean onJSDialog(
      CefBrowser var1, String var2, CefJSDialogHandler.JSDialogType var3, String var4, String var5, CefJSDialogCallback var6, BoolRef var7
   ) {
      return false;
   }

   @Override
   public boolean onBeforeUnloadDialog(CefBrowser var1, String var2, boolean var3, CefJSDialogCallback var4) {
      return false;
   }

   @Override
   public void onResetDialogState(CefBrowser var1) {
   }

   @Override
   public void onDialogClosed(CefBrowser var1) {
   }
}
