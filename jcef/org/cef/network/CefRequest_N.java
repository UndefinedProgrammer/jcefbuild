package org.cef.network;

import java.util.Map;
import org.cef.callback.CefNative;

class CefRequest_N extends CefRequest implements CefNative {
   private long N_CefHandle = 0L;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   public static CefRequest createNative() {
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
   public long getIdentifier() {
      try {
         return this.N_GetIdentifier(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0L;
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
   public String getURL() {
      try {
         return this.N_GetURL(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setURL(String var1) {
      try {
         this.N_SetURL(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public String getMethod() {
      try {
         return this.N_GetMethod(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setMethod(String var1) {
      try {
         this.N_SetMethod(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setReferrer(String var1, CefRequest.ReferrerPolicy var2) {
      try {
         this.N_SetReferrer(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   @Override
   public String getReferrerURL() {
      try {
         return this.N_GetReferrerURL(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public CefRequest.ReferrerPolicy getReferrerPolicy() {
      try {
         return this.N_GetReferrerPolicy(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public CefPostData getPostData() {
      try {
         return this.N_GetPostData(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setPostData(CefPostData var1) {
      try {
         this.N_SetPostData(this.N_CefHandle, var1);
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

   @Override
   public void set(String var1, String var2, CefPostData var3, Map<String, String> var4) {
      try {
         this.N_Set(this.N_CefHandle, var1, var2, var3, var4);
      } catch (UnsatisfiedLinkError var6) {
         var6.printStackTrace();
      }
   }

   @Override
   public int getFlags() {
      try {
         return this.N_GetFlags(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public void setFlags(int var1) {
      try {
         this.N_SetFlags(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public String getFirstPartyForCookies() {
      try {
         return this.N_GetFirstPartyForCookies(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setFirstPartyForCookies(String var1) {
      try {
         this.N_SetFirstPartyForCookies(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public CefRequest.ResourceType getResourceType() {
      try {
         return this.N_GetResourceType(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return CefRequest.ResourceType.RT_MAIN_FRAME;
      }
   }

   @Override
   public CefRequest.TransitionType getTransitionType() {
      try {
         return this.N_GetTransitionType(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return CefRequest.TransitionType.TT_AUTO_SUBFRAME;
      }
   }

   private static final native CefRequest_N N_Create();

   private final native void N_Dispose(long var1);

   private final native long N_GetIdentifier(long var1);

   private final native boolean N_IsReadOnly(long var1);

   private final native String N_GetURL(long var1);

   private final native void N_SetURL(long var1, String var3);

   private final native String N_GetMethod(long var1);

   private final native void N_SetMethod(long var1, String var3);

   private final native void N_SetReferrer(long var1, String var3, CefRequest.ReferrerPolicy var4);

   private final native String N_GetReferrerURL(long var1);

   private final native CefRequest.ReferrerPolicy N_GetReferrerPolicy(long var1);

   private final native CefPostData N_GetPostData(long var1);

   private final native void N_SetPostData(long var1, CefPostData var3);

   private final native String N_GetHeaderByName(long var1, String var3);

   private final native void N_SetHeaderByName(long var1, String var3, String var4, boolean var5);

   private final native void N_GetHeaderMap(long var1, Map<String, String> var3);

   private final native void N_SetHeaderMap(long var1, Map<String, String> var3);

   private final native void N_Set(long var1, String var3, String var4, CefPostData var5, Map<String, String> var6);

   private final native int N_GetFlags(long var1);

   private final native void N_SetFlags(long var1, int var3);

   private final native String N_GetFirstPartyForCookies(long var1);

   private final native void N_SetFirstPartyForCookies(long var1, String var3);

   private final native CefRequest.ResourceType N_GetResourceType(long var1);

   private final native CefRequest.TransitionType N_GetTransitionType(long var1);
}
