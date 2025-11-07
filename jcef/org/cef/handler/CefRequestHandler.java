package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefAuthCallback;
import org.cef.callback.CefCallback;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;

public interface CefRequestHandler {
   boolean onBeforeBrowse(CefBrowser var1, CefFrame var2, CefRequest var3, boolean var4, boolean var5);

   boolean onOpenURLFromTab(CefBrowser var1, CefFrame var2, String var3, boolean var4);

   CefResourceRequestHandler getResourceRequestHandler(CefBrowser var1, CefFrame var2, CefRequest var3, boolean var4, boolean var5, String var6, BoolRef var7);

   boolean getAuthCredentials(CefBrowser var1, String var2, boolean var3, String var4, int var5, String var6, String var7, CefAuthCallback var8);

   boolean onCertificateError(CefBrowser var1, CefLoadHandler.ErrorCode var2, String var3, CefCallback var4);

   void onRenderProcessTerminated(CefBrowser var1, CefRequestHandler.TerminationStatus var2);

   public static enum TerminationStatus {
      TS_ABNORMAL_TERMINATION,
      TS_PROCESS_WAS_KILLED,
      TS_PROCESS_CRASHED,
      TS_PROCESS_OOM;
   }
}
