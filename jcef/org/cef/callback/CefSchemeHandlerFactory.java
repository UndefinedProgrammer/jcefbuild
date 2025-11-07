package org.cef.callback;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefResourceHandler;
import org.cef.network.CefRequest;

public interface CefSchemeHandlerFactory {
   CefResourceHandler create(CefBrowser var1, CefFrame var2, String var3, CefRequest var4);
}
