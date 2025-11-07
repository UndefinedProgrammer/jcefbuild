package org.cef.browser;

import java.awt.Rectangle;
import java.nio.ByteBuffer;

public class CefPaintEvent {
   private final CefBrowser browser;
   private final boolean popup;
   private final Rectangle[] dirtyRects;
   private final ByteBuffer renderedFrame;
   private final int width;
   private final int height;

   public CefPaintEvent(CefBrowser var1, boolean var2, Rectangle[] var3, ByteBuffer var4, int var5, int var6) {
      this.browser = var1;
      this.popup = var2;
      this.dirtyRects = var3;
      this.renderedFrame = var4;
      this.width = var5;
      this.height = var6;
   }

   public CefBrowser getBrowser() {
      return this.browser;
   }

   public boolean getPopup() {
      return this.popup;
   }

   public Rectangle[] getDirtyRects() {
      return this.dirtyRects;
   }

   public ByteBuffer getRenderedFrame() {
      return this.renderedFrame;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }
}
