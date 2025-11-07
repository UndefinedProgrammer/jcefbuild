package org.cef.callback;

import java.util.Vector;

public interface CefFileDialogCallback {
   void Continue(Vector<String> var1);

   void Cancel();
}
