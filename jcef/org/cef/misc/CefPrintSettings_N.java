package org.cef.misc;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Vector;
import org.cef.callback.CefNative;

class CefPrintSettings_N extends CefPrintSettings implements CefNative {
   private long N_CefHandle = 0L;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   public static CefPrintSettings createNative() {
      try {
         return N_Create();
      } catch (UnsatisfiedLinkError var1) {
         var1.printStackTrace();
         return null;
      }
   }

   @Override
   public void dispose() {
      try {
         this.N_Dispose(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public boolean isValid() {
      try {
         return this.N_IsValid(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isReadOnly() {
      try {
         return this.N_IsReadOnly(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void setOrientation(boolean var1) {
      try {
         this.N_SetOrientation(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public boolean isLandscape() {
      try {
         return this.N_IsLandscape(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void setPrinterPrintableArea(Dimension var1, Rectangle var2, boolean var3) {
      try {
         this.N_SetPrinterPrintableArea(this.N_CefHandle, var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
      }
   }

   @Override
   public void setDeviceName(String var1) {
      try {
         this.N_SetDeviceName(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public String getDeviceName() {
      try {
         return this.N_GetDeviceName(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setDPI(int var1) {
      try {
         this.N_SetDPI(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public int getDPI() {
      try {
         return this.N_GetDPI(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public void setPageRanges(Vector<CefPageRange> var1) {
      try {
         this.N_SetPageRanges(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public int getPageRangesCount() {
      try {
         return this.N_GetPageRangesCount(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public void getPageRanges(Vector<CefPageRange> var1) {
      try {
         this.N_GetPageRanges(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setSelectionOnly(boolean var1) {
      try {
         this.N_SetSelectionOnly(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public boolean isSelectionOnly() {
      try {
         return this.N_IsSelectionOnly(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void setCollate(boolean var1) {
      try {
         this.N_SetCollate(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public boolean willCollate() {
      try {
         return this.N_WillCollate(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public void setColorModel(CefPrintSettings.ColorModel var1) {
      try {
         this.N_SetColorModel(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public CefPrintSettings.ColorModel getColorModel() {
      try {
         return this.N_GetColorModel(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setCopies(int var1) {
      try {
         this.N_SetCopies(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public int getCopies() {
      try {
         return this.N_GetCopies(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public void setDuplexMode(CefPrintSettings.DuplexMode var1) {
      try {
         this.N_SetDuplexMode(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public CefPrintSettings.DuplexMode getDuplexMode() {
      try {
         return this.N_GetDuplexMode(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   private static final native CefPrintSettings_N N_Create();

   private final native void N_Dispose(long var1);

   private final native boolean N_IsValid(long var1);

   private final native boolean N_IsReadOnly(long var1);

   private final native void N_SetOrientation(long var1, boolean var3);

   private final native boolean N_IsLandscape(long var1);

   private final native void N_SetPrinterPrintableArea(long var1, Dimension var3, Rectangle var4, boolean var5);

   private final native void N_SetDeviceName(long var1, String var3);

   private final native String N_GetDeviceName(long var1);

   private final native void N_SetDPI(long var1, int var3);

   private final native int N_GetDPI(long var1);

   private final native void N_SetPageRanges(long var1, Vector<CefPageRange> var3);

   private final native int N_GetPageRangesCount(long var1);

   private final native void N_GetPageRanges(long var1, Vector<CefPageRange> var3);

   private final native void N_SetSelectionOnly(long var1, boolean var3);

   private final native boolean N_IsSelectionOnly(long var1);

   private final native void N_SetCollate(long var1, boolean var3);

   private final native boolean N_WillCollate(long var1);

   private final native void N_SetColorModel(long var1, CefPrintSettings.ColorModel var3);

   private final native CefPrintSettings.ColorModel N_GetColorModel(long var1);

   private final native void N_SetCopies(long var1, int var3);

   private final native int N_GetCopies(long var1);

   private final native void N_SetDuplexMode(long var1, CefPrintSettings.DuplexMode var3);

   private final native CefPrintSettings.DuplexMode N_GetDuplexMode(long var1);
}
