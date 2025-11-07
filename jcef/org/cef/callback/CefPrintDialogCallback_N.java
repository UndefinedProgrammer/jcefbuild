package org.cef.callback;

import org.cef.misc.CefPrintSettings;

class CefPrintDialogCallback_N extends CefNativeAdapter implements CefPrintDialogCallback {
   @Override
   protected void finalize() throws Throwable {
      this.cancel();
      super.finalize();
   }

   @Override
   public void Continue(CefPrintSettings var1) {
      try {
         this.N_Continue(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void cancel() {
      try {
         this.N_Cancel(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private final native void N_Continue(long var1, CefPrintSettings var3);

   private final native void N_Cancel(long var1);
}
