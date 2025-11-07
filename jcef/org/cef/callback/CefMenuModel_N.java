package org.cef.callback;

import org.cef.misc.BoolRef;
import org.cef.misc.IntRef;

class CefMenuModel_N extends CefNativeAdapter implements CefMenuModel {
   public CefMenuModel_N() {
   }

   @Override
   public boolean clear() {
      try {
         return this.N_Clear(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public int getCount() {
      try {
         return this.N_GetCount(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public boolean addSeparator() {
      try {
         return this.N_AddSeparator(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean addItem(int var1, String var2) {
      try {
         return this.N_AddItem(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean addCheckItem(int var1, String var2) {
      try {
         return this.N_AddCheckItem(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean addRadioItem(int var1, String var2, int var3) {
      try {
         return this.N_AddRadioItem(this.getNativeRef(null), var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
         return false;
      }
   }

   @Override
   public CefMenuModel addSubMenu(int var1, String var2) {
      try {
         return this.N_AddSubMenu(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean insertSeparatorAt(int var1) {
      try {
         return this.N_InsertSeparatorAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean insertItemAt(int var1, int var2, String var3) {
      try {
         return this.N_InsertItemAt(this.getNativeRef(null), var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean insertCheckItemAt(int var1, int var2, String var3) {
      try {
         return this.N_InsertCheckItemAt(this.getNativeRef(null), var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean insertRadioItemAt(int var1, int var2, String var3, int var4) {
      try {
         return this.N_InsertRadioItemAt(this.getNativeRef(null), var1, var2, var3, var4);
      } catch (UnsatisfiedLinkError var6) {
         var6.printStackTrace();
         return false;
      }
   }

   @Override
   public CefMenuModel insertSubMenuAt(int var1, int var2, String var3) {
      try {
         return this.N_InsertSubMenuAt(this.getNativeRef(null), var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean remove(int var1) {
      try {
         return this.N_Remove(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean removeAt(int var1) {
      try {
         return this.N_RemoveAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public int getIndexOf(int var1) {
      try {
         return this.N_GetIndexOf(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   @Override
   public int getCommandIdAt(int var1) {
      try {
         return this.N_GetCommandIdAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   @Override
   public boolean setCommandIdAt(int var1, int var2) {
      try {
         return this.N_SetCommandIdAt(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public String getLabel(int var1) {
      try {
         return this.N_GetLabel(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public String getLabelAt(int var1) {
      try {
         return this.N_GetLabelAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean setLabel(int var1, String var2) {
      try {
         return this.N_SetLabel(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setLabelAt(int var1, String var2) {
      try {
         return this.N_SetLabelAt(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public CefMenuModel.MenuItemType getType(int var1) {
      try {
         return this.N_GetType(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public CefMenuModel.MenuItemType getTypeAt(int var1) {
      try {
         return this.N_GetTypeAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public int getGroupId(int var1) {
      try {
         return this.N_GetGroupId(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   @Override
   public int getGroupIdAt(int var1) {
      try {
         return this.N_GetGroupIdAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   @Override
   public boolean setGroupId(int var1, int var2) {
      try {
         return this.N_SetGroupId(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setGroupIdAt(int var1, int var2) {
      try {
         return this.N_SetGroupIdAt(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public CefMenuModel getSubMenu(int var1) {
      try {
         return this.N_GetSubMenu(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public CefMenuModel getSubMenuAt(int var1) {
      try {
         return this.N_GetSubMenuAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean isVisible(int var1) {
      try {
         return this.N_IsVisible(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isVisibleAt(int var1) {
      try {
         return this.N_IsVisibleAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setVisible(int var1, boolean var2) {
      try {
         return this.N_SetVisible(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setVisibleAt(int var1, boolean var2) {
      try {
         return this.N_SetVisibleAt(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isEnabled(int var1) {
      try {
         return this.N_IsEnabled(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isEnabledAt(int var1) {
      try {
         return this.N_IsEnabledAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setEnabled(int var1, boolean var2) {
      try {
         return this.N_SetEnabled(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setEnabledAt(int var1, boolean var2) {
      try {
         return this.N_SetEnabledAt(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isChecked(int var1) {
      try {
         return this.N_IsChecked(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isCheckedAt(int var1) {
      try {
         return this.N_IsCheckedAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setChecked(int var1, boolean var2) {
      try {
         return this.N_SetChecked(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setCheckedAt(int var1, boolean var2) {
      try {
         return this.N_SetCheckedAt(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean hasAccelerator(int var1) {
      try {
         return this.N_HasAccelerator(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean hasAcceleratorAt(int var1) {
      try {
         return this.N_HasAcceleratorAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setAccelerator(int var1, int var2, boolean var3, boolean var4, boolean var5) {
      try {
         return this.N_SetAccelerator(this.getNativeRef(null), var1, var2, var3, var4, var5);
      } catch (UnsatisfiedLinkError var7) {
         var7.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean setAcceleratorAt(int var1, int var2, boolean var3, boolean var4, boolean var5) {
      try {
         return this.N_SetAcceleratorAt(this.getNativeRef(null), var1, var2, var3, var4, var5);
      } catch (UnsatisfiedLinkError var7) {
         var7.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean removeAccelerator(int var1) {
      try {
         return this.N_RemoveAccelerator(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean removeAcceleratorAt(int var1) {
      try {
         return this.N_RemoveAcceleratorAt(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean getAccelerator(int var1, IntRef var2, BoolRef var3, BoolRef var4, BoolRef var5) {
      try {
         return this.N_GetAccelerator(this.getNativeRef(null), var1, var2, var3, var4, var5);
      } catch (UnsatisfiedLinkError var7) {
         var7.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean getAcceleratorAt(int var1, IntRef var2, BoolRef var3, BoolRef var4, BoolRef var5) {
      try {
         return this.N_GetAcceleratorAt(this.getNativeRef(null), var1, var2, var3, var4, var5);
      } catch (UnsatisfiedLinkError var7) {
         var7.printStackTrace();
         return false;
      }
   }

   private final native boolean N_Clear(long var1);

   private final native int N_GetCount(long var1);

   private final native boolean N_AddSeparator(long var1);

   private final native boolean N_AddItem(long var1, int var3, String var4);

   private final native boolean N_AddCheckItem(long var1, int var3, String var4);

   private final native boolean N_AddRadioItem(long var1, int var3, String var4, int var5);

   private final native CefMenuModel N_AddSubMenu(long var1, int var3, String var4);

   private final native boolean N_InsertSeparatorAt(long var1, int var3);

   private final native boolean N_InsertItemAt(long var1, int var3, int var4, String var5);

   private final native boolean N_InsertCheckItemAt(long var1, int var3, int var4, String var5);

   private final native boolean N_InsertRadioItemAt(long var1, int var3, int var4, String var5, int var6);

   private final native CefMenuModel N_InsertSubMenuAt(long var1, int var3, int var4, String var5);

   private final native boolean N_Remove(long var1, int var3);

   private final native boolean N_RemoveAt(long var1, int var3);

   private final native int N_GetIndexOf(long var1, int var3);

   private final native int N_GetCommandIdAt(long var1, int var3);

   private final native boolean N_SetCommandIdAt(long var1, int var3, int var4);

   private final native String N_GetLabel(long var1, int var3);

   private final native String N_GetLabelAt(long var1, int var3);

   private final native boolean N_SetLabel(long var1, int var3, String var4);

   private final native boolean N_SetLabelAt(long var1, int var3, String var4);

   private final native CefMenuModel.MenuItemType N_GetType(long var1, int var3);

   private final native CefMenuModel.MenuItemType N_GetTypeAt(long var1, int var3);

   private final native int N_GetGroupId(long var1, int var3);

   private final native int N_GetGroupIdAt(long var1, int var3);

   private final native boolean N_SetGroupId(long var1, int var3, int var4);

   private final native boolean N_SetGroupIdAt(long var1, int var3, int var4);

   private final native CefMenuModel N_GetSubMenu(long var1, int var3);

   private final native CefMenuModel N_GetSubMenuAt(long var1, int var3);

   private final native boolean N_IsVisible(long var1, int var3);

   private final native boolean N_IsVisibleAt(long var1, int var3);

   private final native boolean N_SetVisible(long var1, int var3, boolean var4);

   private final native boolean N_SetVisibleAt(long var1, int var3, boolean var4);

   private final native boolean N_IsEnabled(long var1, int var3);

   private final native boolean N_IsEnabledAt(long var1, int var3);

   private final native boolean N_SetEnabled(long var1, int var3, boolean var4);

   private final native boolean N_SetEnabledAt(long var1, int var3, boolean var4);

   private final native boolean N_IsChecked(long var1, int var3);

   private final native boolean N_IsCheckedAt(long var1, int var3);

   private final native boolean N_SetChecked(long var1, int var3, boolean var4);

   private final native boolean N_SetCheckedAt(long var1, int var3, boolean var4);

   private final native boolean N_HasAccelerator(long var1, int var3);

   private final native boolean N_HasAcceleratorAt(long var1, int var3);

   private final native boolean N_SetAccelerator(long var1, int var3, int var4, boolean var5, boolean var6, boolean var7);

   private final native boolean N_SetAcceleratorAt(long var1, int var3, int var4, boolean var5, boolean var6, boolean var7);

   private final native boolean N_RemoveAccelerator(long var1, int var3);

   private final native boolean N_RemoveAcceleratorAt(long var1, int var3);

   private final native boolean N_GetAccelerator(long var1, int var3, IntRef var4, BoolRef var5, BoolRef var6, BoolRef var7);

   private final native boolean N_GetAcceleratorAt(long var1, int var3, IntRef var4, BoolRef var5, BoolRef var6, BoolRef var7);
}
