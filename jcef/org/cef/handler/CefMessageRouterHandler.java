package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefNative;
import org.cef.callback.CefQueryCallback;

public interface CefMessageRouterHandler extends CefNative {
   boolean onQuery(CefBrowser var1, CefFrame var2, long var3, String var5, boolean var6, CefQueryCallback var7);

   void onQueryCanceled(CefBrowser var1, CefFrame var2, long var3);
}
