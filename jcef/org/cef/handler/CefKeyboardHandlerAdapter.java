package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.misc.BoolRef;

public abstract class CefKeyboardHandlerAdapter implements CefKeyboardHandler {
   @Override
   public boolean onPreKeyEvent(CefBrowser var1, CefKeyboardHandler.CefKeyEvent var2, BoolRef var3) {
      return false;
   }

   @Override
   public boolean onKeyEvent(CefBrowser var1, CefKeyboardHandler.CefKeyEvent var2) {
      return false;
   }
}
