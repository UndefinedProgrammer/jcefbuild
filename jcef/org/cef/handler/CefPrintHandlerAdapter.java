package org.cef.handler;

import java.awt.Dimension;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefNativeAdapter;
import org.cef.callback.CefPrintDialogCallback;
import org.cef.callback.CefPrintJobCallback;
import org.cef.misc.CefPrintSettings;

public abstract class CefPrintHandlerAdapter extends CefNativeAdapter implements CefPrintHandler {
   @Override
   public void onPrintStart(CefBrowser var1) {
   }

   @Override
   public void onPrintSettings(CefBrowser var1, CefPrintSettings var2, boolean var3) {
   }

   @Override
   public boolean onPrintDialog(CefBrowser var1, boolean var2, CefPrintDialogCallback var3) {
      return false;
   }

   @Override
   public boolean onPrintJob(CefBrowser var1, String var2, String var3, CefPrintJobCallback var4) {
      return false;
   }

   @Override
   public void onPrintReset(CefBrowser var1) {
   }

   @Override
   public Dimension getPdfPaperSize(CefBrowser var1, int var2) {
      int var3 = (int)(var2 / 300.0 * 2480.0);
      int var4 = (int)(var2 / 300.0 * 3508.0);
      return new Dimension(var3, var4);
   }
}
