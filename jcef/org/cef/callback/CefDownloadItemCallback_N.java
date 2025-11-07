package org.cef.callback;

class CefDownloadItemCallback_N extends CefNativeAdapter implements CefDownloadItemCallback {
   @Override
   protected void finalize() throws Throwable {
      try {
         this.N_Dispose(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }

      super.finalize();
   }

   @Override
   public void cancel() {
      try {
         this.N_Cancel(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void pause() {
      try {
         this.N_Pause(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void resume() {
      try {
         this.N_Resume(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private final native void N_Dispose(long var1);

   private final native void N_Cancel(long var1);

   private final native void N_Pause(long var1);

   private final native void N_Resume(long var1);
}
