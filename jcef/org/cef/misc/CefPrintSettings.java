package org.cef.misc;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Vector;

public abstract class CefPrintSettings {
   CefPrintSettings() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefPrintSettings create() {
      return CefPrintSettings_N.createNative();
   }

   public abstract void dispose();

   public abstract boolean isValid();

   public abstract boolean isReadOnly();

   public abstract void setOrientation(boolean var1);

   public abstract boolean isLandscape();

   public abstract void setPrinterPrintableArea(Dimension var1, Rectangle var2, boolean var3);

   public abstract void setDeviceName(String var1);

   public abstract String getDeviceName();

   public abstract void setDPI(int var1);

   public abstract int getDPI();

   public abstract void setPageRanges(Vector<CefPageRange> var1);

   public abstract int getPageRangesCount();

   public abstract void getPageRanges(Vector<CefPageRange> var1);

   public abstract void setSelectionOnly(boolean var1);

   public abstract boolean isSelectionOnly();

   public abstract void setCollate(boolean var1);

   public abstract boolean willCollate();

   public abstract void setColorModel(CefPrintSettings.ColorModel var1);

   public abstract CefPrintSettings.ColorModel getColorModel();

   public abstract void setCopies(int var1);

   public abstract int getCopies();

   public abstract void setDuplexMode(CefPrintSettings.DuplexMode var1);

   public abstract CefPrintSettings.DuplexMode getDuplexMode();

   public static enum ColorModel {
      COLOR_MODEL_UNKNOWN,
      COLOR_MODEL_GRAY,
      COLOR_MODEL_COLOR,
      COLOR_MODEL_CMYK,
      COLOR_MODEL_CMY,
      COLOR_MODEL_KCMY,
      COLOR_MODEL_CMY_K,
      COLOR_MODEL_BLACK,
      COLOR_MODEL_GRAYSCALE,
      COLOR_MODEL_RGB,
      COLOR_MODEL_RGB16,
      COLOR_MODEL_RGBA,
      COLOR_MODEL_COLORMODE_COLOR,
      COLOR_MODEL_COLORMODE_MONOCHROME,
      COLOR_MODEL_HP_COLOR_COLOR,
      COLOR_MODEL_HP_COLOR_BLACK,
      COLOR_MODEL_PRINTOUTMODE_NORMAL,
      COLOR_MODEL_PRINTOUTMODE_NORMAL_GRAY,
      COLOR_MODEL_PROCESSCOLORMODEL_CMYK,
      COLOR_MODEL_PROCESSCOLORMODEL_GREYSCALE,
      COLOR_MODEL_PROCESSCOLORMODEL_RGB;
   }

   public static enum DuplexMode {
      DUPLEX_MODE_UNKNOWN,
      DUPLEX_MODE_SIMPLEX,
      DUPLEX_MODE_LONG_EDGE,
      DUPLEX_MODE_SHORT_EDGE;
   }
}
