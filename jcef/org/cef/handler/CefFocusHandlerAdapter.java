package org.cef.handler;

import org.cef.browser.CefBrowser;

public abstract class CefFocusHandlerAdapter implements CefFocusHandler {
   @Override
   public void onTakeFocus(CefBrowser var1, boolean var2) {
   }

   @Override
   public boolean onSetFocus(CefBrowser var1, CefFocusHandler.FocusSource var2) {
      return false;
   }

   @Override
   public void onGotFocus(CefBrowser var1) {
   }
}
