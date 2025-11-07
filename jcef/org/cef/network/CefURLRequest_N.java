package org.cef.network;

import org.cef.callback.CefNative;
import org.cef.callback.CefURLRequestClient;
import org.cef.handler.CefLoadHandler;

class CefURLRequest_N extends CefURLRequest implements CefNative {
   private long N_CefHandle = 0L;
   private final CefRequest request_;
   private final CefURLRequestClient client_;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   CefURLRequest_N(CefRequest var1, CefURLRequestClient var2) {
      this.request_ = var1;
      this.client_ = var2;
   }

   public static final CefURLRequest createNative(CefRequest var0, CefURLRequestClient var1) {
      CefURLRequest_N var2 = new CefURLRequest_N(var0, var1);

      try {
         var2.N_Create(var0, var1);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }

      return var2.N_CefHandle == 0L ? null : var2;
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
   public CefRequest getRequest() {
      return this.request_;
   }

   @Override
   public CefURLRequestClient getClient() {
      return this.client_;
   }

   @Override
   public CefURLRequest.Status getRequestStatus() {
      try {
         return this.N_GetRequestStatus(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public CefLoadHandler.ErrorCode getRequestError() {
      try {
         return this.N_GetRequestError(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public CefResponse getResponse() {
      try {
         return this.N_GetResponse(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void cancel() {
      try {
         this.N_Cancel(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private final native void N_Create(CefRequest var1, CefURLRequestClient var2);

   private final native void N_Dispose(long var1);

   private final native CefURLRequest.Status N_GetRequestStatus(long var1);

   private final native CefLoadHandler.ErrorCode N_GetRequestError(long var1);

   private final native CefResponse N_GetResponse(long var1);

   private final native void N_Cancel(long var1);
}
