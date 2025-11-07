package org.cef.handler;

import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;

public interface CefDisplayHandler {
   void onAddressChange(CefBrowser var1, CefFrame var2, String var3);

   void onTitleChange(CefBrowser var1, String var2);

   void OnFullscreenModeChange(CefBrowser var1, boolean var2);

   boolean onTooltip(CefBrowser var1, String var2);

   void onStatusMessage(CefBrowser var1, String var2);

   boolean onConsoleMessage(CefBrowser var1, CefSettings.LogSeverity var2, String var3, String var4, int var5);

   boolean onCursorChange(CefBrowser var1, int var2);
}
