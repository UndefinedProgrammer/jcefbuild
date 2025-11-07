package org.cef.handler;

import java.awt.Dimension;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefNative;
import org.cef.callback.CefPrintDialogCallback;
import org.cef.callback.CefPrintJobCallback;
import org.cef.misc.CefPrintSettings;

public interface CefPrintHandler extends CefNative {
   void onPrintStart(CefBrowser var1);

   void onPrintSettings(CefBrowser var1, CefPrintSettings var2, boolean var3);

   boolean onPrintDialog(CefBrowser var1, boolean var2, CefPrintDialogCallback var3);

   boolean onPrintJob(CefBrowser var1, String var2, String var3, CefPrintJobCallback var4);

   void onPrintReset(CefBrowser var1);

   Dimension getPdfPaperSize(CefBrowser var1, int var2);
}
