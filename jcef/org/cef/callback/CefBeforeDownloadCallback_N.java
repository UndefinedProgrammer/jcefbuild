package org.cef.callback;

class CefBeforeDownloadCallback_N extends CefNativeAdapter implements CefBeforeDownloadCallback {
   @Override
   protected void finalize() throws Throwable {
      this.Continue("", false);
      super.finalize();
   }

   @Override
   public void Continue(String var1, boolean var2) {
      try {
         this.N_Continue(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   private final native void N_Continue(long var1, String var3, boolean var4);
}
