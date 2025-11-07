package org.cef.callback;

class CefCallback_N extends CefNativeAdapter implements CefCallback {
   @Override
   protected void finalize() throws Throwable {
      this.cancel();
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

   @Override
   public void cancel() {
      try {
         this.N_Cancel(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private final native void N_Continue(long var1);

   private final native void N_Cancel(long var1);
}
