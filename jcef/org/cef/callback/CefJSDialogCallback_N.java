package org.cef.callback;

class CefJSDialogCallback_N extends CefNativeAdapter implements CefJSDialogCallback {
   @Override
   protected void finalize() throws Throwable {
      this.Continue(false, "");
      super.finalize();
   }

   @Override
   public void Continue(boolean var1, String var2) {
      try {
         this.N_Continue(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   private final native void N_Continue(long var1, boolean var3, String var4);
}
