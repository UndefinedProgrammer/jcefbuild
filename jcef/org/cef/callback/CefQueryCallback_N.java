package org.cef.callback;

class CefQueryCallback_N extends CefNativeAdapter implements CefQueryCallback {
   @Override
   protected void finalize() throws Throwable {
      this.failure(-1, "Unexpected call to CefQueryCallback_N::finalize()");
      super.finalize();
   }

   @Override
   public void success(String var1) {
      try {
         this.N_Success(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void failure(int var1, String var2) {
      try {
         this.N_Failure(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   private final native void N_Success(long var1, String var3);

   private final native void N_Failure(long var1, int var3, String var4);
}
