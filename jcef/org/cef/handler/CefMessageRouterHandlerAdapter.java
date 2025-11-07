package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefNativeAdapter;
import org.cef.callback.CefQueryCallback;

public abstract class CefMessageRouterHandlerAdapter extends CefNativeAdapter implements CefMessageRouterHandler {
   @Override
   public boolean onQuery(CefBrowser var1, CefFrame var2, long var3, String var5, boolean var6, CefQueryCallback var7) {
      return false;
   }

   @Override
   public void onQueryCanceled(CefBrowser var1, CefFrame var2, long var3) {
   }
}
