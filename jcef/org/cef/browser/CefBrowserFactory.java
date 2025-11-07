package org.cef.browser;

import org.cef.CefBrowserSettings;
import org.cef.CefClient;

public class CefBrowserFactory {
   public static CefBrowser create(CefClient var0, String var1, boolean var2, boolean var3, CefRequestContext var4, CefBrowserSettings var5) {
      return (CefBrowser)(var2 ? new CefBrowserOsr(var0, var1, var3, var4, var5) : new CefBrowserWr(var0, var1, var4, var5));
   }
}
