package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefContextMenuParams;
import org.cef.callback.CefMenuModel;

public abstract class CefContextMenuHandlerAdapter implements CefContextMenuHandler {
   @Override
   public void onBeforeContextMenu(CefBrowser var1, CefFrame var2, CefContextMenuParams var3, CefMenuModel var4) {
   }

   @Override
   public boolean onContextMenuCommand(CefBrowser var1, CefFrame var2, CefContextMenuParams var3, int var4, int var5) {
      return false;
   }

   @Override
   public void onContextMenuDismissed(CefBrowser var1, CefFrame var2) {
   }
}
