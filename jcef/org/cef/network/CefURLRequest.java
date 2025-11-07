package org.cef.network;

import org.cef.callback.CefURLRequestClient;
import org.cef.handler.CefLoadHandler;

public abstract class CefURLRequest {
   CefURLRequest() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefURLRequest create(CefRequest var0, CefURLRequestClient var1) {
      return CefURLRequest_N.createNative(var0, var1);
   }

   public abstract void dispose();

   public abstract CefRequest getRequest();

   public abstract CefURLRequestClient getClient();

   public abstract CefURLRequest.Status getRequestStatus();

   public abstract CefLoadHandler.ErrorCode getRequestError();

   public abstract CefResponse getResponse();

   public abstract void cancel();

   public static enum Status {
      UR_UNKNOWN,
      UR_SUCCESS,
      UR_IO_PENDING,
      UR_CANCELED,
      UR_FAILED;
   }
}
