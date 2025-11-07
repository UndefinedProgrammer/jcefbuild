package org.cef.callback;

class CefAuthCallback_N extends CefNativeAdapter implements CefAuthCallback {
   @Override
   protected void finalize() throws Throwable {
      this.cancel();
      super.finalize();
   }

   @Override
   public void Continue(String var1, String var2) {
      try {
         this.N_Continue(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
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

   private final native void N_Continue(long var1, String var3, String var4);

   private final native void N_Cancel(long var1);
}
