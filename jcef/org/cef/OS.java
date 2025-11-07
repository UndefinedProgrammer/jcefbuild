package org.cef;

public class OS {
   private static OS.OSType osType = OS.OSType.OSUndefined;

   public static final boolean isWindows() {
      return getOSType() == OS.OSType.OSWindows;
   }

   public static final boolean isMacintosh() {
      return getOSType() == OS.OSType.OSMacintosh;
   }

   public static final boolean isLinux() {
      return getOSType() == OS.OSType.OSLinux;
   }

   private static final OS.OSType getOSType() {
      if (osType == OS.OSType.OSUndefined) {
         String var0 = System.getProperty("os.name").toLowerCase();
         if (var0.startsWith("windows")) {
            osType = OS.OSType.OSWindows;
         } else if (var0.startsWith("linux")) {
            osType = OS.OSType.OSLinux;
         } else if (var0.startsWith("mac")) {
            osType = OS.OSType.OSMacintosh;
         } else {
            osType = OS.OSType.OSUnknown;
         }
      }

      return osType;
   }

   private static enum OSType {
      OSUndefined,
      OSLinux,
      OSWindows,
      OSMacintosh,
      OSUnknown;
   }
}
