package org.cef;

public class SystemBootstrap {
   private static SystemBootstrap.Loader loader_ = new SystemBootstrap.Loader() {
      @Override
      public void loadLibrary(String var1) {
         System.loadLibrary(var1);
      }
   };

   public static void setLoader(SystemBootstrap.Loader var0) {
      if (var0 == null) {
         throw new NullPointerException("Loader cannot be null");
      } else {
         loader_ = var0;
      }
   }

   public static void loadLibrary(String var0) {
      loader_.loadLibrary(var0);
   }

   public interface Loader {
      void loadLibrary(String var1);
   }
}
