package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.network.CefRequest;

public abstract class CefLoadHandlerAdapter implements CefLoadHandler {
   @Override
   public void onLoadingStateChange(CefBrowser var1, boolean var2, boolean var3, boolean var4) {
   }

   @Override
   public void onLoadStart(CefBrowser var1, CefFrame var2, CefRequest.TransitionType var3) {
   }

   @Override
   public void onLoadEnd(CefBrowser var1, CefFrame var2, int var3) {
   }

   @Override
   public void onLoadError(CefBrowser var1, CefFrame var2, CefLoadHandler.ErrorCode var3, String var4, String var5) {
   }
}
