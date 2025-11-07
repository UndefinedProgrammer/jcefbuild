package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.misc.BoolRef;

public interface CefKeyboardHandler {
   boolean onPreKeyEvent(CefBrowser var1, CefKeyboardHandler.CefKeyEvent var2, BoolRef var3);

   boolean onKeyEvent(CefBrowser var1, CefKeyboardHandler.CefKeyEvent var2);

   public static final class CefKeyEvent {
      public final CefKeyboardHandler.CefKeyEvent.EventType type;
      public final int modifiers;
      public final int windows_key_code;
      public final int native_key_code;
      public final boolean is_system_key;
      public final char character;
      public final char unmodified_character;
      public final boolean focus_on_editable_field;

      CefKeyEvent(CefKeyboardHandler.CefKeyEvent.EventType var1, int var2, int var3, int var4, boolean var5, char var6, char var7, boolean var8) {
         this.type = var1;
         this.modifiers = var2;
         this.windows_key_code = var3;
         this.native_key_code = var4;
         this.is_system_key = var5;
         this.character = var6;
         this.unmodified_character = var7;
         this.focus_on_editable_field = var8;
      }

      @Override
      public String toString() {
         return "CefKeyEvent [type="
            + this.type
            + ", modifiers="
            + this.modifiers
            + ", windows_key_code="
            + this.windows_key_code
            + ", native_key_code="
            + this.native_key_code
            + ", is_system_key="
            + this.is_system_key
            + ", character="
            + this.character
            + ", unmodified_character="
            + this.unmodified_character
            + ", focus_on_editable_field="
            + this.focus_on_editable_field
            + "]";
      }

      public static enum EventType {
         KEYEVENT_RAWKEYDOWN,
         KEYEVENT_KEYDOWN,
         KEYEVENT_KEYUP,
         KEYEVENT_CHAR;
      }
   }
}
