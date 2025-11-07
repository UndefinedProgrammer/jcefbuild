package org.cef.browser;

import java.awt.Component;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import org.cef.CefClient;
import org.cef.callback.CefPdfPrintCallback;
import org.cef.callback.CefRunFileDialogCallback;
import org.cef.callback.CefStringVisitor;
import org.cef.handler.CefDialogHandler;
import org.cef.handler.CefRenderHandler;
import org.cef.handler.CefWindowHandler;
import org.cef.misc.CefPdfPrintSettings;
import org.cef.network.CefRequest;

public interface CefBrowser {
   void createImmediately();

   Component getUIComponent();

   CefClient getClient();

   CefRenderHandler getRenderHandler();

   CefWindowHandler getWindowHandler();

   boolean canGoBack();

   void goBack();

   boolean canGoForward();

   void goForward();

   boolean isLoading();

   void reload();

   void reloadIgnoreCache();

   void stopLoad();

   int getIdentifier();

   CefFrame getMainFrame();

   CefFrame getFocusedFrame();

   CefFrame getFrame(long var1);

   CefFrame getFrame(String var1);

   Vector<Long> getFrameIdentifiers();

   Vector<String> getFrameNames();

   int getFrameCount();

   boolean isPopup();

   boolean hasDocument();

   void viewSource();

   void getSource(CefStringVisitor var1);

   void getText(CefStringVisitor var1);

   void loadRequest(CefRequest var1);

   void loadURL(String var1);

   void executeJavaScript(String var1, String var2, int var3);

   String getURL();

   void close(boolean var1);

   void setCloseAllowed();

   boolean doClose();

   void onBeforeClose();

   void setFocus(boolean var1);

   void setWindowVisibility(boolean var1);

   double getZoomLevel();

   void setZoomLevel(double var1);

   void runFileDialog(CefDialogHandler.FileDialogMode var1, String var2, String var3, Vector<String> var4, int var5, CefRunFileDialogCallback var6);

   void startDownload(String var1);

   void print();

   void printToPDF(String var1, CefPdfPrintSettings var2, CefPdfPrintCallback var3);

   void find(String var1, boolean var2, boolean var3, boolean var4);

   void stopFinding(boolean var1);

   CefBrowser getDevTools();

   CefBrowser getDevTools(Point var1);

   CefDevToolsClient getDevToolsClient();

   void replaceMisspelling(String var1);

   CompletableFuture<BufferedImage> createScreenshot(boolean var1);

   void setWindowlessFrameRate(int var1);

   CompletableFuture<Integer> getWindowlessFrameRate();
}
