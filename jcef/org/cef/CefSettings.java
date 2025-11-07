package org.cef;

public class CefSettings {
   public String browser_subprocess_path = null;
   public boolean windowless_rendering_enabled = true;
   public boolean command_line_args_disabled = false;
   public String cache_path = null;
   public boolean persist_session_cookies = false;
   public String user_agent = null;
   public String user_agent_product = null;
   public String locale = null;
   public String log_file = "cef_debug_axp.log";
   public CefSettings.LogSeverity log_severity = CefSettings.LogSeverity.LOGSEVERITY_INFO;
   public String javascript_flags = null;
   public String resources_dir_path = null;
   public String locales_dir_path = null;
   public boolean pack_loading_disabled = false;
   public int remote_debugging_port = 0;
   public int uncaught_exception_stack_size = 0;
   public CefSettings.ColorType background_color = null;
   public String cookieable_schemes_list = null;
   public boolean cookieable_schemes_exclude_defaults = false;

   public CefSettings clone() {
      CefSettings var1 = new CefSettings();
      var1.browser_subprocess_path = this.browser_subprocess_path;
      var1.windowless_rendering_enabled = this.windowless_rendering_enabled;
      var1.command_line_args_disabled = this.command_line_args_disabled;
      var1.cache_path = this.cache_path;
      var1.persist_session_cookies = this.persist_session_cookies;
      var1.user_agent = this.user_agent;
      var1.user_agent_product = this.user_agent_product;
      var1.locale = this.locale;
      var1.log_file = this.log_file;
      var1.log_severity = this.log_severity;
      var1.javascript_flags = this.javascript_flags;
      var1.resources_dir_path = this.resources_dir_path;
      var1.locales_dir_path = this.locales_dir_path;
      var1.pack_loading_disabled = this.pack_loading_disabled;
      var1.remote_debugging_port = this.remote_debugging_port;
      var1.uncaught_exception_stack_size = this.uncaught_exception_stack_size;
      if (this.background_color != null) {
         var1.background_color = this.background_color.clone();
      }

      var1.cookieable_schemes_list = this.cookieable_schemes_list;
      var1.cookieable_schemes_exclude_defaults = this.cookieable_schemes_exclude_defaults;
      return var1;
   }

   public class ColorType {
      private long color_value = 0L;

      private ColorType() {
      }

      public ColorType(int var2, int var3, int var4, int var5) {
         this.color_value = var2 << 24 | var3 << 16 | var4 << 8 | var5 << 0;
      }

      public long getColor() {
         return this.color_value;
      }

      public CefSettings.ColorType clone() {
         CefSettings.ColorType var1 = CefSettings.this.new ColorType();
         var1.color_value = this.color_value;
         return var1;
      }
   }

   public static enum LogSeverity {
      LOGSEVERITY_DEFAULT,
      LOGSEVERITY_VERBOSE,
      LOGSEVERITY_INFO,
      LOGSEVERITY_WARNING,
      LOGSEVERITY_ERROR,
      LOGSEVERITY_FATAL,
      LOGSEVERITY_DISABLE;
   }
}
