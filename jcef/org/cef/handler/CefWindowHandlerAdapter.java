package org.cef.handler;

import java.awt.Rectangle;
import org.cef.browser.CefBrowser;

public abstract class CefWindowHandlerAdapter implements CefWindowHandler {
   @Override
   public Rectangle getRect(CefBrowser var1) {
      return new Rectangle(0, 0, 0, 0);
   }

   @Override
   public void onMouseEvent(CefBrowser var1, int var2, int var3, int var4, int var5, int var6) {
   }
}
