package org.cef.handler;

import java.awt.Point;
import java.awt.Rectangle;
import java.nio.ByteBuffer;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefDragData;

public abstract class CefRenderHandlerAdapter implements CefRenderHandler {
   @Override
   public Rectangle getViewRect(CefBrowser var1) {
      return new Rectangle(0, 0, 0, 0);
   }

   @Override
   public boolean getScreenInfo(CefBrowser var1, CefScreenInfo var2) {
      return false;
   }

   @Override
   public Point getScreenPoint(CefBrowser var1, Point var2) {
      return new Point(0, 0);
   }

   @Override
   public void onPopupShow(CefBrowser var1, boolean var2) {
   }

   @Override
   public void onPopupSize(CefBrowser var1, Rectangle var2) {
   }

   @Override
   public void onPaint(CefBrowser var1, boolean var2, Rectangle[] var3, ByteBuffer var4, int var5, int var6) {
   }

   @Override
   public boolean onCursorChange(CefBrowser var1, int var2) {
      return false;
   }

   @Override
   public boolean startDragging(CefBrowser var1, CefDragData var2, int var3, int var4, int var5) {
      return false;
   }

   @Override
   public void updateDragCursor(CefBrowser var1, int var2) {
   }
}
