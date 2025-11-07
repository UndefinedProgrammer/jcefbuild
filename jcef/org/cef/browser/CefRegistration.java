package org.cef.browser;

abstract class CefRegistration {
   public abstract void dispose();

   @Override
   public void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }
}
