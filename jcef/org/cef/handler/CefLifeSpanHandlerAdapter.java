package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;

public abstract class CefLifeSpanHandlerAdapter implements CefLifeSpanHandler {
   @Override
   public boolean onBeforePopup(CefBrowser var1, CefFrame var2, String var3, String var4) {
      return false;
   }

   @Override
   public void onAfterCreated(CefBrowser var1) {
   }

   @Override
   public void onAfterParentChanged(CefBrowser var1) {
   }

   @Override
   public boolean doClose(CefBrowser var1) {
      return false;
   }

   @Override
   public void onBeforeClose(CefBrowser var1) {
   }
}
