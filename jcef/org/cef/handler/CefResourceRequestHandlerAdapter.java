package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.misc.BoolRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;
import org.cef.network.CefURLRequest;

public abstract class CefResourceRequestHandlerAdapter implements CefResourceRequestHandler {
   @Override
   public CefCookieAccessFilter getCookieAccessFilter(CefBrowser var1, CefFrame var2, CefRequest var3) {
      return null;
   }

   @Override
   public boolean onBeforeResourceLoad(CefBrowser var1, CefFrame var2, CefRequest var3) {
      return false;
   }

   @Override
   public CefResourceHandler getResourceHandler(CefBrowser var1, CefFrame var2, CefRequest var3) {
      return null;
   }

   @Override
   public void onResourceRedirect(CefBrowser var1, CefFrame var2, CefRequest var3, CefResponse var4, StringRef var5) {
   }

   @Override
   public boolean onResourceResponse(CefBrowser var1, CefFrame var2, CefRequest var3, CefResponse var4) {
      return false;
   }

   @Override
   public void onResourceLoadComplete(CefBrowser var1, CefFrame var2, CefRequest var3, CefResponse var4, CefURLRequest.Status var5, long var6) {
   }

   @Override
   public void onProtocolExecution(CefBrowser var1, CefFrame var2, CefRequest var3, BoolRef var4) {
   }
}
