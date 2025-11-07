package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.misc.BoolRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.cef.network.CefURLRequest;

public interface CefResourceRequestHandler {
   CefCookieAccessFilter getCookieAccessFilter(CefBrowser var1, CefFrame var2, CefRequest var3);

   boolean onBeforeResourceLoad(CefBrowser var1, CefFrame var2, CefRequest var3);

   CefResourceHandler getResourceHandler(CefBrowser var1, CefFrame var2, CefRequest var3);

   void onResourceRedirect(CefBrowser var1, CefFrame var2, CefRequest var3, CefResponse var4, StringRef var5);

   boolean onResourceResponse(CefBrowser var1, CefFrame var2, CefRequest var3, CefResponse var4);

   void onResourceLoadComplete(CefBrowser var1, CefFrame var2, CefRequest var3, CefResponse var4, CefURLRequest.Status var5, long var6);

   void onProtocolExecution(CefBrowser var1, CefFrame var2, CefRequest var3, BoolRef var4);
}
