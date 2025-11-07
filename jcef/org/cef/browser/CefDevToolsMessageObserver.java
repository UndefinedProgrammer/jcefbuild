package org.cef.browser;

interface CefDevToolsMessageObserver {
   void onDevToolsMethodResult(CefBrowser var1, int var2, boolean var3, String var4);

   void onDevToolsEvent(CefBrowser var1, String var2, String var3);
}
