package org.cef.callback;

import java.util.Vector;

class CefFileDialogCallback_N extends CefNativeAdapter implements CefFileDialogCallback {
   @Override
   protected void finalize() throws Throwable {
      this.Cancel();
      super.finalize();
   }

   @Override
   public void Continue(Vector<String> var1) {
      try {
         this.N_Continue(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void Cancel() {
      try {
         this.N_Cancel(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private final native void N_Continue(long var1, Vector<String> var3);

   private final native void N_Cancel(long var1);
}
