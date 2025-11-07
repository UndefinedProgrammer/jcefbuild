package org.cef.callback;

import org.cef.misc.BoolRef;
import org.cef.misc.IntRef;

public interface CefMenuModel {
   boolean clear();

   int getCount();

   boolean addSeparator();

   boolean addItem(int var1, String var2);

   boolean addCheckItem(int var1, String var2);

   boolean addRadioItem(int var1, String var2, int var3);

   CefMenuModel addSubMenu(int var1, String var2);

   boolean insertSeparatorAt(int var1);

   boolean insertItemAt(int var1, int var2, String var3);

   boolean insertCheckItemAt(int var1, int var2, String var3);

   boolean insertRadioItemAt(int var1, int var2, String var3, int var4);

   CefMenuModel insertSubMenuAt(int var1, int var2, String var3);

   boolean remove(int var1);

   boolean removeAt(int var1);

   int getIndexOf(int var1);

   int getCommandIdAt(int var1);

   boolean setCommandIdAt(int var1, int var2);

   String getLabel(int var1);

   String getLabelAt(int var1);

   boolean setLabel(int var1, String var2);

   boolean setLabelAt(int var1, String var2);

   CefMenuModel.MenuItemType getType(int var1);

   CefMenuModel.MenuItemType getTypeAt(int var1);

   int getGroupId(int var1);

   int getGroupIdAt(int var1);

   boolean setGroupId(int var1, int var2);

   boolean setGroupIdAt(int var1, int var2);

   CefMenuModel getSubMenu(int var1);

   CefMenuModel getSubMenuAt(int var1);

   boolean isVisible(int var1);

   boolean isVisibleAt(int var1);

   boolean setVisible(int var1, boolean var2);

   boolean setVisibleAt(int var1, boolean var2);

   boolean isEnabled(int var1);

   boolean isEnabledAt(int var1);

   boolean setEnabled(int var1, boolean var2);

   boolean setEnabledAt(int var1, boolean var2);

   boolean isChecked(int var1);

   boolean isCheckedAt(int var1);

   boolean setChecked(int var1, boolean var2);

   boolean setCheckedAt(int var1, boolean var2);

   boolean hasAccelerator(int var1);

   boolean hasAcceleratorAt(int var1);

   boolean setAccelerator(int var1, int var2, boolean var3, boolean var4, boolean var5);

   boolean setAcceleratorAt(int var1, int var2, boolean var3, boolean var4, boolean var5);

   boolean removeAccelerator(int var1);

   boolean removeAcceleratorAt(int var1);

   boolean getAccelerator(int var1, IntRef var2, BoolRef var3, BoolRef var4, BoolRef var5);

   boolean getAcceleratorAt(int var1, IntRef var2, BoolRef var3, BoolRef var4, BoolRef var5);

   public static final class MenuId {
      public static final int MENU_ID_BACK = 100;
      public static final int MENU_ID_FORWARD = 101;
      public static final int MENU_ID_RELOAD = 102;
      public static final int MENU_ID_RELOAD_NOCACHE = 103;
      public static final int MENU_ID_STOPLOAD = 104;
      public static final int MENU_ID_UNDO = 110;
      public static final int MENU_ID_REDO = 111;
      public static final int MENU_ID_CUT = 112;
      public static final int MENU_ID_COPY = 113;
      public static final int MENU_ID_PASTE = 114;
      public static final int MENU_ID_DELETE = 115;
      public static final int MENU_ID_SELECT_ALL = 116;
      public static final int MENU_ID_FIND = 130;
      public static final int MENU_ID_PRINT = 131;
      public static final int MENU_ID_VIEW_SOURCE = 132;
      public static final int MENU_ID_SPELLCHECK_SUGGESTION_0 = 200;
      public static final int MENU_ID_SPELLCHECK_SUGGESTION_1 = 201;
      public static final int MENU_ID_SPELLCHECK_SUGGESTION_2 = 202;
      public static final int MENU_ID_SPELLCHECK_SUGGESTION_3 = 203;
      public static final int MENU_ID_SPELLCHECK_SUGGESTION_4 = 204;
      public static final int MENU_ID_SPELLCHECK_SUGGESTION_LAST = 204;
      public static final int MENU_ID_NO_SPELLING_SUGGESTIONS = 205;
      public static final int MENU_ID_USER_FIRST = 26500;
      public static final int MENU_ID_USER_LAST = 28500;
   }

   public static enum MenuItemType {
      MENUITEMTYPE_NONE,
      MENUITEMTYPE_COMMAND,
      MENUITEMTYPE_CHECK,
      MENUITEMTYPE_RADIO,
      MENUITEMTYPE_SEPARATOR,
      MENUITEMTYPE_SUBMENU;
   }
}
