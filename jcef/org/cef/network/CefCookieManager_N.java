package org.cef.network;

import org.cef.callback.CefCompletionCallback;
import org.cef.callback.CefCookieVisitor;
import org.cef.callback.CefNative;

class CefCookieManager_N extends CefCookieManager implements CefNative {
   private long N_CefHandle = 0L;
   private static CefCookieManager_N globalInstance = null;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   static final synchronized CefCookieManager_N getGlobalManagerNative() {
      if (globalInstance != null && globalInstance.N_CefHandle != 0L) {
         return globalInstance;
      } else {
         CefCookieManager_N var0 = null;

         try {
            var0 = N_GetGlobalManager();
         } catch (UnsatisfiedLinkError var2) {
            var2.printStackTrace();
         }

         globalInstance = var0;
         return globalInstance;
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
   public boolean visitAllCookies(CefCookieVisitor var1) {
      try {
         return this.N_VisitAllCookies(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean visitUrlCookies(String var1, boolean var2, CefCookieVisitor var3) {
      try {
         return this.N_VisitUrlCookies(this.N_CefHandle, var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setCookie(String var1, CefCookie var2) {
      try {
         return this.N_SetCookie(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean deleteCookies(String var1, String var2) {
      try {
         return this.N_DeleteCookies(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean flushStore(CefCompletionCallback var1) {
      try {
         return this.N_FlushStore(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   private static final native CefCookieManager_N N_GetGlobalManager();

   private final native void N_Dispose(long var1);

   private final native boolean N_VisitAllCookies(long var1, CefCookieVisitor var3);

   private final native boolean N_VisitUrlCookies(long var1, String var3, boolean var4, CefCookieVisitor var5);

   private final native boolean N_SetCookie(long var1, String var3, CefCookie var4);

   private final native boolean N_DeleteCookies(long var1, String var3, String var4);

   private final native boolean N_FlushStore(long var1, CefCompletionCallback var3);
}
