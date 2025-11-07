package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.network.CefCookie;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;

public interface CefCookieAccessFilter {
   boolean canSendCookie(CefBrowser var1, CefFrame var2, CefRequest var3, CefCookie var4);

   boolean canSaveCookie(CefBrowser var1, CefFrame var2, CefRequest var3, CefResponse var4, CefCookie var5);
}
