package org.cef.network;

import java.util.Map;
import org.cef.callback.CefNative;
import org.cef.handler.CefLoadHandler;

class CefResponse_N extends CefResponse implements CefNative {
   private long N_CefHandle = 0L;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   public static CefResponse createNative() {
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
   public CefLoadHandler.ErrorCode getError() {
      try {
         return this.N_GetError(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setError(CefLoadHandler.ErrorCode var1) {
      try {
         this.N_SetError(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public int getStatus() {
      try {
         return this.N_GetStatus(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public void setStatus(int var1) {
      try {
         this.N_SetStatus(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public String getStatusText() {
      try {
         return this.N_GetStatusText(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setStatusText(String var1) {
      try {
         this.N_SetStatusText(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public String getMimeType() {
      try {
         return this.N_GetMimeType(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setMimeType(String var1) {
      try {
         this.N_SetMimeType(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public String getHeaderByName(String var1) {
      try {
         return this.N_GetHeaderByName(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public void setHeaderByName(String var1, String var2, boolean var3) {
      try {
         this.N_SetHeaderByName(this.N_CefHandle, var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
      }
   }

   @Override
   public void getHeaderMap(Map<String, String> var1) {
      try {
         this.N_GetHeaderMap(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setHeaderMap(Map<String, String> var1) {
      try {
         this.N_SetHeaderMap(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   private static final native CefResponse_N N_Create();

   private final native void N_Dispose(long var1);

   private final native boolean N_IsReadOnly(long var1);

   private final native CefLoadHandler.ErrorCode N_GetError(long var1);

   private final native void N_SetError(long var1, CefLoadHandler.ErrorCode var3);

   private final native int N_GetStatus(long var1);

   private final native void N_SetStatus(long var1, int var3);

   private final native String N_GetStatusText(long var1);

   private final native void N_SetStatusText(long var1, String var3);

   private final native String N_GetMimeType(long var1);

   private final native void N_SetMimeType(long var1, String var3);

   private final native String N_GetHeaderByName(long var1, String var3);

   private final native void N_SetHeaderByName(long var1, String var3, String var4, boolean var5);

   private final native void N_GetHeaderMap(long var1, Map<String, String> var3);

   private final native void N_SetHeaderMap(long var1, Map<String, String> var3);
}
