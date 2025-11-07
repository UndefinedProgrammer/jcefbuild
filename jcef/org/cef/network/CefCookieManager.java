package org.cef.network;

import org.cef.callback.CefCompletionCallback;
import org.cef.callback.CefCookieVisitor;

public abstract class CefCookieManager {
   CefCookieManager() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefCookieManager getGlobalManager() {
      return CefCookieManager_N.getGlobalManagerNative();
   }

   public abstract void dispose();

   public abstract boolean visitAllCookies(CefCookieVisitor var1);

   public abstract boolean visitUrlCookies(String var1, boolean var2, CefCookieVisitor var3);

   public abstract boolean setCookie(String var1, CefCookie var2);

   public abstract boolean deleteCookies(String var1, String var2);

   public abstract boolean flushStore(CefCompletionCallback var1);
}
