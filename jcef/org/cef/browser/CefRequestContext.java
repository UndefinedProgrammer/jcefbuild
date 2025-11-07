package org.cef.browser;

import org.cef.handler.CefRequestContextHandler;

public abstract class CefRequestContext {
   CefRequestContext() {
   }

   public static final CefRequestContext getGlobalContext() {
      return CefRequestContext_N.getGlobalContextNative();
   }

   public static final CefRequestContext createContext(CefRequestContextHandler var0) {
      return CefRequestContext_N.createNative(var0);
   }

   public abstract void dispose();

   public abstract boolean isGlobal();

   public abstract CefRequestContextHandler getHandler();
}
