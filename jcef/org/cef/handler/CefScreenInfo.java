package org.cef.handler;

import java.awt.Rectangle;

public class CefScreenInfo {
   public double device_scale_factor;
   public int depth;
   public int depth_per_component;
   public boolean is_monochrome;
   public int x;
   public int y;
   public int width;
   public int height;
   public int available_x;
   public int available_y;
   public int available_width;
   public int available_height;

   public void Set(double var1, int var3, int var4, boolean var5, Rectangle var6, Rectangle var7) {
      this.device_scale_factor = var1;
      this.depth = var3;
      this.depth_per_component = var4;
      this.is_monochrome = var5;
      this.x = var6.x;
      this.y = var6.y;
      this.width = var6.width;
      this.height = var6.height;
      this.available_x = var7.x;
      this.available_y = var7.y;
      this.available_width = var7.width;
      this.available_height = var7.height;
   }
}
