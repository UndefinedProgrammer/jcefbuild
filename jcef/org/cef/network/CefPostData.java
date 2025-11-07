package org.cef.network;

import java.util.Vector;

public abstract class CefPostData {
   CefPostData() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefPostData create() {
      return CefPostData_N.createNative();
   }

   public abstract void dispose();

   public abstract boolean isReadOnly();

   public abstract int getElementCount();

   public abstract void getElements(Vector<CefPostDataElement> var1);

   public abstract boolean removeElement(CefPostDataElement var1);

   public abstract boolean addElement(CefPostDataElement var1);

   public abstract void removeElements();

   @Override
   public String toString() {
      return this.toString(null);
   }

   public String toString(String var1) {
      Vector var2 = new Vector();
      this.getElements(var2);
      String var3 = "";

      for (CefPostDataElement var5 : var2) {
         var3 = var3 + var5.toString(var1) + "\n";
      }

      return var3;
   }
}
