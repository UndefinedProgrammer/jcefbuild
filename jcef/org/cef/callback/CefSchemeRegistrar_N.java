package org.cef.callback;

class CefSchemeRegistrar_N extends CefNativeAdapter implements CefSchemeRegistrar {
   @Override
   public boolean addCustomScheme(String var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8) {
      try {
         return this.N_AddCustomScheme(var1, var2, var3, var4, var5, var6, var7, var8);
      } catch (UnsatisfiedLinkError var10) {
         var10.printStackTrace();
         return false;
      }
   }

   private final native boolean N_AddCustomScheme(String var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8);
}
