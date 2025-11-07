package org.cef.handler;

import org.cef.callback.CefCallback;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;

public interface CefResourceHandler {
   boolean processRequest(CefRequest var1, CefCallback var2);

   void getResponseHeaders(CefResponse var1, IntRef var2, StringRef var3);

   boolean readResponse(byte[] var1, int var2, IntRef var3, CefCallback var4);

   void cancel();
}
