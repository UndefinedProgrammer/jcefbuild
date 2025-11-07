package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.callback.CefBeforeDownloadCallback;
import org.cef.callback.CefDownloadItem;
import org.cef.callback.CefDownloadItemCallback;

public interface CefDownloadHandler {
   void onBeforeDownload(CefBrowser var1, CefDownloadItem var2, String var3, CefBeforeDownloadCallback var4);

   void onDownloadUpdated(CefBrowser var1, CefDownloadItem var2, CefDownloadItemCallback var3);
}
