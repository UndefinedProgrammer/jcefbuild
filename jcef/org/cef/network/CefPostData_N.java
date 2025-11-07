package org.cef.network;

import java.util.Vector;
import org.cef.callback.CefNative;

class CefPostData_N extends CefPostData implements CefNative {
   private long N_CefHandle = 0L;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   public static CefPostData createNative() {
      try {
         return N_Create();
      } catch (UnsatisfiedLinkError var1) {
         var1.printStackTrace();
         return null;
      }
   }

   @Override
   public void dispose() {
      try {
         this.N_Dispose(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public boolean isReadOnly() {
      try {
         return this.N_IsReadOnly(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public int getElementCount() {
      try {
         return this.N_GetElementCount(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public void getElements(Vector<CefPostDataElement> var1) {
      try {
         this.N_GetElements(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public boolean removeElement(CefPostDataElement var1) {
      try {
         return this.N_RemoveElement(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean addElement(CefPostDataElement var1) {
      try {
         return this.N_AddElement(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public void removeElements() {
      try {
         this.N_RemoveElements(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private static final native CefPostData_N N_Create();

   private final native void N_Dispose(long var1);

   private final native boolean N_IsReadOnly(long var1);

   private final native int N_GetElementCount(long var1);

   private final native void N_GetElements(long var1, Vector<CefPostDataElement> var3);

   private final native boolean N_RemoveElement(long var1, CefPostDataElement var3);

   private final native boolean N_AddElement(long var1, CefPostDataElement var3);

   private final native void N_RemoveElements(long var1);
}
