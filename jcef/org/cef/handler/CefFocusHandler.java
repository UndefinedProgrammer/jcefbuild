package org.cef.handler;

import org.cef.browser.CefBrowser;

public interface CefFocusHandler {
   void onTakeFocus(CefBrowser var1, boolean var2);

   boolean onSetFocus(CefBrowser var1, CefFocusHandler.FocusSource var2);

   void onGotFocus(CefBrowser var1);

   public static enum FocusSource {
      FOCUS_SOURCE_NAVIGATION,
      FOCUS_SOURCE_SYSTEM;
   }
}
