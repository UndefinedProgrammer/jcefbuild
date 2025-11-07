package org.cef.handler;

import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;

public abstract class CefDisplayHandlerAdapter implements CefDisplayHandler {
   @Override
   public void onAddressChange(CefBrowser var1, CefFrame var2, String var3) {
   }

   @Override
   public void onTitleChange(CefBrowser var1, String var2) {
   }

   @Override
   public void OnFullscreenModeChange(CefBrowser var1, boolean var2) {
   }

   @Override
   public boolean onTooltip(CefBrowser var1, String var2) {
      return false;
   }

   @Override
   public void onStatusMessage(CefBrowser var1, String var2) {
   }

   @Override
   public boolean onConsoleMessage(CefBrowser var1, CefSettings.LogSeverity var2, String var3, String var4, int var5) {
      return false;
   }

   @Override
   public boolean onCursorChange(CefBrowser var1, int var2) {
      return false;
   }
}
