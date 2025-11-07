package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;

public interface CefLifeSpanHandler {
   boolean onBeforePopup(CefBrowser var1, CefFrame var2, String var3, String var4);

   void onAfterCreated(CefBrowser var1);

   void onAfterParentChanged(CefBrowser var1);

   boolean doClose(CefBrowser var1);

   void onBeforeClose(CefBrowser var1);
}
