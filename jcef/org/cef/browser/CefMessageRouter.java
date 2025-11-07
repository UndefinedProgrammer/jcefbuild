package org.cef.browser;

import org.cef.handler.CefMessageRouterHandler;

public abstract class CefMessageRouter {
   private CefMessageRouter.CefMessageRouterConfig routerConfig_ = null;

   CefMessageRouter() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefMessageRouter create() {
      return create(null, null);
   }

   public static final CefMessageRouter create(CefMessageRouter.CefMessageRouterConfig var0) {
      return create(var0, null);
   }

   public static final CefMessageRouter create(CefMessageRouterHandler var0) {
      return create(null, var0);
   }

   public static final CefMessageRouter create(CefMessageRouter.CefMessageRouterConfig var0, CefMessageRouterHandler var1) {
      CefMessageRouter var2 = CefMessageRouter_N.createNative(var0);
      if (var2 != null && var1 != null) {
         var2.addHandler(var1, true);
      }

      return var2;
   }

   public abstract void dispose();

   void setMessageRouterConfig(CefMessageRouter.CefMessageRouterConfig var1) {
      this.routerConfig_ = var1;
   }

   public final CefMessageRouter.CefMessageRouterConfig getMessageRouterConfig() {
      return this.routerConfig_;
   }

   public abstract boolean addHandler(CefMessageRouterHandler var1, boolean var2);

   public abstract boolean removeHandler(CefMessageRouterHandler var1);

   public abstract void cancelPending(CefBrowser var1, CefMessageRouterHandler var2);

   public static class CefMessageRouterConfig {
      public String jsQueryFunction;
      public String jsCancelFunction;

      public CefMessageRouterConfig() {
         this("cefQuery", "cefQueryCancel");
      }

      public CefMessageRouterConfig(String var1, String var2) {
         this.jsQueryFunction = var1;
         this.jsCancelFunction = var2;
      }
   }
}
