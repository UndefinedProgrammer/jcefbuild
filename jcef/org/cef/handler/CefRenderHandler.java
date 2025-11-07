package org.cef.handler;

import java.awt.Point;
import java.awt.Rectangle;
import java.nio.ByteBuffer;
import java.util.function.Consumer;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefPaintEvent;
import org.cef.callback.CefDragData;

public interface CefRenderHandler {
   Rectangle getViewRect(CefBrowser var1);

   boolean getScreenInfo(CefBrowser var1, CefScreenInfo var2);

   Point getScreenPoint(CefBrowser var1, Point var2);

   void onPopupShow(CefBrowser var1, boolean var2);

   void onPopupSize(CefBrowser var1, Rectangle var2);

   void onPaint(CefBrowser var1, boolean var2, Rectangle[] var3, ByteBuffer var4, int var5, int var6);

   void addOnPaintListener(Consumer<CefPaintEvent> var1);

   void setOnPaintListener(Consumer<CefPaintEvent> var1);

   void removeOnPaintListener(Consumer<CefPaintEvent> var1);

   boolean onCursorChange(CefBrowser var1, int var2);

   boolean startDragging(CefBrowser var1, CefDragData var2, int var3, int var4, int var5);

   void updateDragCursor(CefBrowser var1, int var2);
}
