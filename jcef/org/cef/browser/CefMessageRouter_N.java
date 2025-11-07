package org.cef.browser;

import org.cef.callback.CefNative;
import org.cef.handler.CefMessageRouterHandler;

class CefMessageRouter_N extends CefMessageRouter implements CefNative {
   private long N_CefHandle = 0L;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   private CefMessageRouter_N() {
   }

   public static final CefMessageRouter createNative(CefMessageRouter.CefMessageRouterConfig var0) {
      try {
         return N_Create(var0);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void dispose() {
      try {
         this.N_Dispose(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public boolean addHandler(CefMessageRouterHandler var1, boolean var2) {
      try {
         return this.N_AddHandler(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean removeHandler(CefMessageRouterHandler var1) {
      try {
         return this.N_RemoveHandler(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public void cancelPending(CefBrowser var1, CefMessageRouterHandler var2) {
      try {
         this.N_CancelPending(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   private static final native CefMessageRouter_N N_Create(CefMessageRouter.CefMessageRouterConfig var0);

   private final native void N_Dispose(long var1);

   private final native boolean N_AddHandler(long var1, CefMessageRouterHandler var3, boolean var4);

   private final native boolean N_RemoveHandler(long var1, CefMessageRouterHandler var3);

   private final native void N_CancelPending(long var1, CefBrowser var3, CefMessageRouterHandler var4);
}
