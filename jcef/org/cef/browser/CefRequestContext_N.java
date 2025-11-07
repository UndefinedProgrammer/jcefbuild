package org.cef.browser;

import org.cef.callback.CefNative;
import org.cef.handler.CefRequestContextHandler;

class CefRequestContext_N extends CefRequestContext implements CefNative {
   private long N_CefHandle = 0L;
   private static CefRequestContext_N globalInstance = null;
   private CefRequestContextHandler handler = null;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   static final CefRequestContext_N getGlobalContextNative() {
      CefRequestContext_N var0 = null;

      try {
         var0 = N_GetGlobalContext();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }

      if (globalInstance == null) {
         globalInstance = var0;
      } else if (globalInstance.N_CefHandle == var0.N_CefHandle) {
         var0.N_CefRequestContext_DTOR();
      }

      return globalInstance;
   }

   static final CefRequestContext_N createNative(CefRequestContextHandler var0) {
      CefRequestContext_N var1 = null;

      try {
         var1 = N_CreateContext(var0);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }

      if (var1 != null) {
         var1.handler = var0;
      }

      return var1;
   }

   @Override
   public void dispose() {
      try {
         this.N_CefRequestContext_DTOR();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public boolean isGlobal() {
      try {
         return this.N_IsGlobal();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public CefRequestContextHandler getHandler() {
      return this.handler;
   }

   private static final native CefRequestContext_N N_GetGlobalContext();

   private static final native CefRequestContext_N N_CreateContext(CefRequestContextHandler var0);

   private final native boolean N_IsGlobal();

   private final native void N_CefRequestContext_DTOR();
}
