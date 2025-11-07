package org.cef.handler;

import java.util.Vector;
import org.cef.browser.CefBrowser;
import org.cef.callback.CefFileDialogCallback;

public interface CefDialogHandler {
   boolean onFileDialog(CefBrowser var1, CefDialogHandler.FileDialogMode var2, String var3, String var4, Vector<String> var5, CefFileDialogCallback var6);

   public static enum FileDialogMode {
      FILE_DIALOG_OPEN,
      FILE_DIALOG_OPEN_MULTIPLE,
      FILE_DIALOG_SAVE;
   }
}
