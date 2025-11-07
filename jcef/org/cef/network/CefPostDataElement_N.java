package org.cef.network;

import org.cef.callback.CefNative;

class CefPostDataElement_N extends CefPostDataElement implements CefNative {
   private long N_CefHandle = 0L;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   public static CefPostDataElement createNative() {
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
   public void setToEmpty() {
      try {
         this.N_SetToEmpty(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void setToFile(String var1) {
      try {
         this.N_SetToFile(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setToBytes(int var1, byte[] var2) {
      try {
         this.N_SetToBytes(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   @Override
   public CefPostDataElement.Type getType() {
      try {
         return this.N_GetType(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFile() {
      try {
         return this.N_GetFile(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public int getBytesCount() {
      try {
         return this.N_GetBytesCount(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public int getBytes(int var1, byte[] var2) {
      try {
         return this.N_GetBytes(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
         return 0;
      }
   }

   private static final native CefPostDataElement_N N_Create();

   private final native void N_Dispose(long var1);

   private final native boolean N_IsReadOnly(long var1);

   private final native void N_SetToEmpty(long var1);

   private final native void N_SetToFile(long var1, String var3);

   private final native void N_SetToBytes(long var1, int var3, byte[] var4);

   private final native CefPostDataElement.Type N_GetType(long var1);

   private final native String N_GetFile(long var1);

   private final native int N_GetBytesCount(long var1);

   private final native int N_GetBytes(long var1, int var3, byte[] var4);
}
