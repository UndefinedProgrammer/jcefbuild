package org.cef.misc;

public class CefPdfPrintSettings {
   public boolean landscape;
   public boolean print_background;
   public double scale;
   public double paper_width;
   public double paper_height;
   public boolean prefer_css_page_size;
   public CefPdfPrintSettings.MarginType margin_type;
   public double margin_top;
   public double margin_right;
   public double margin_bottom;
   public double margin_left;
   public String page_ranges;
   public boolean display_header_footer;
   public String header_template;
   public String footer_template;

   public CefPdfPrintSettings clone() {
      CefPdfPrintSettings var1 = new CefPdfPrintSettings();
      var1.landscape = this.landscape;
      var1.print_background = this.print_background;
      var1.scale = this.scale;
      var1.paper_width = this.paper_width;
      var1.paper_height = this.paper_height;
      var1.prefer_css_page_size = this.prefer_css_page_size;
      var1.margin_type = this.margin_type;
      var1.margin_top = this.margin_top;
      var1.margin_bottom = this.margin_bottom;
      var1.margin_right = this.margin_right;
      var1.margin_left = this.margin_left;
      var1.page_ranges = this.page_ranges;
      var1.display_header_footer = this.display_header_footer;
      var1.header_template = this.header_template;
      var1.footer_template = this.footer_template;
      return var1;
   }

   public static enum MarginType {
      DEFAULT,
      NONE,
      CUSTOM;
   }
}
