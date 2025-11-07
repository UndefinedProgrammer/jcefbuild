package org.cef.handler;

import java.awt.Rectangle;
import org.cef.browser.CefBrowser;

public interface CefWindowHandler {
   Rectangle getRect(CefBrowser var1);

   void onMouseEvent(CefBrowser var1, int var2, int var3, int var4, int var5, int var6);
}
