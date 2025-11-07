package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;

public interface CefRequestContextHandler {
   CefResourceRequestHandler getResourceRequestHandler(CefBrowser var1, CefFrame var2, CefRequest var3, boolean var4, boolean var5, String var6, BoolRef var7);
}
