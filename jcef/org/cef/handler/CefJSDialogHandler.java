package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.callback.CefJSDialogCallback;
import org.cef.misc.BoolRef;

public interface CefJSDialogHandler {
   boolean onJSDialog(CefBrowser var1, String var2, CefJSDialogHandler.JSDialogType var3, String var4, String var5, CefJSDialogCallback var6, BoolRef var7);

   boolean onBeforeUnloadDialog(CefBrowser var1, String var2, boolean var3, CefJSDialogCallback var4);

   void onResetDialogState(CefBrowser var1);

   void onDialogClosed(CefBrowser var1);

   public static enum JSDialogType {
      JSDIALOGTYPE_ALERT,
      JSDIALOGTYPE_CONFIRM,
      JSDIALOGTYPE_PROMPT;
   }
}
