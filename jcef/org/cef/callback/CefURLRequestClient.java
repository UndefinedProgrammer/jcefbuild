package org.cef.callback;

import org.cef.network.CefURLRequest;

public interface CefURLRequestClient extends CefNative {
   void onRequestComplete(CefURLRequest var1);

   void onUploadProgress(CefURLRequest var1, int var2, int var3);

   void onDownloadProgress(CefURLRequest var1, int var2, int var3);

   void onDownloadData(CefURLRequest var1, byte[] var2, int var3);

   boolean getAuthCredentials(boolean var1, String var2, int var3, String var4, String var5, CefAuthCallback var6);
}
