package org.cef.callback;

class CefPrintJobCallback_N extends CefNativeAdapter implements CefPrintJobCallback {
   @Override
   protected void finalize() throws Throwable {
      this.Continue();
      super.finalize();
   }

   @Override
   public void Continue() {
      try {
         this.N_Continue(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private final native void N_Continue(long var1);
}
