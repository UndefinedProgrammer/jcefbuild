package org.cef;

public class CefBrowserSettings {
   public int windowless_frame_rate = 0;

   public CefBrowserSettings clone() {
      CefBrowserSettings var1 = new CefBrowserSettings();
      var1.windowless_frame_rate = this.windowless_frame_rate;
      return var1;
   }
}
