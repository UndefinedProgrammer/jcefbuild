package org.cef.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class CefRequest {
   CefRequest() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefRequest create() {
      return CefRequest_N.createNative();
   }

   public abstract void dispose();

   public abstract long getIdentifier();

   public abstract boolean isReadOnly();

   public abstract String getURL();

   public abstract void setURL(String var1);

   public abstract String getMethod();

   public abstract void setMethod(String var1);

   public abstract void setReferrer(String var1, CefRequest.ReferrerPolicy var2);

   public abstract String getReferrerURL();

   public abstract CefRequest.ReferrerPolicy getReferrerPolicy();

   public abstract CefPostData getPostData();

   public abstract void setPostData(CefPostData var1);

   public abstract String getHeaderByName(String var1);

   public abstract void setHeaderByName(String var1, String var2, boolean var3);

   public abstract void getHeaderMap(Map<String, String> var1);

   public abstract void setHeaderMap(Map<String, String> var1);

   public abstract void set(String var1, String var2, CefPostData var3, Map<String, String> var4);

   public abstract int getFlags();

   public abstract void setFlags(int var1);

   public abstract String getFirstPartyForCookies();

   public abstract void setFirstPartyForCookies(String var1);

   public abstract CefRequest.ResourceType getResourceType();

   public abstract CefRequest.TransitionType getTransitionType();

   @Override
   public String toString() {
      String var1 = "\nHTTP-Request";
      var1 = var1 + "\n  flags: " + this.getFlags();
      var1 = var1 + "\n  resourceType: " + this.getResourceType();
      var1 = var1 + "\n  transitionType: " + this.getTransitionType();
      var1 = var1 + "\n  firstPartyForCookies: " + this.getFirstPartyForCookies();
      var1 = var1 + "\n  referrerURL: " + this.getReferrerURL();
      var1 = var1 + "\n  referrerPolicy: " + this.getReferrerPolicy();
      var1 = var1 + "\n    " + this.getMethod() + " " + this.getURL() + " HTTP/1.1\n";
      HashMap var2 = new HashMap();
      this.getHeaderMap(var2);
      Set var3 = var2.entrySet();
      String var4 = null;

      for (Entry var6 : var3) {
         String var7 = (String)var6.getKey();
         var1 = var1 + "    " + var7 + "=" + (String)var6.getValue() + "\n";
         if (var7.equals("Content-Type")) {
            var4 = (String)var6.getValue();
         }
      }

      CefPostData var15 = this.getPostData();
      if (var15 != null) {
         var1 = var1 + var15.toString(var4);
      }

      return var1;
   }

   public static final class CefUrlRequestFlags {
      public static final int UR_FLAG_NONE = 0;
      public static final int UR_FLAG_SKIP_CACHE = 1;
      public static final int UR_FLAG_ALLOW_CACHED_CREDENTIALS = 2;
      public static final int UR_FLAG_REPORT_UPLOAD_PROGRESS = 8;
      public static final int UR_FLAG_REPORT_RAW_HEADERS = 32;
      public static final int UR_FLAG_NO_DOWNLOAD_DATA = 64;
      public static final int UR_FLAG_NO_RETRY_ON_5XX = 128;
   }

   public static enum ReferrerPolicy {
      REFERRER_POLICY_DEFAULT,
      REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE,
      REFERRER_POLICY_REDUCE_REFERRER_GRANULARITY_ON_TRANSITION_CROSS_ORIGIN,
      REFERRER_POLICY_ORIGIN_ONLY_ON_TRANSITION_CROSS_ORIGIN,
      REFERRER_POLICY_NEVER_CLEAR_REFERRER,
      REFERRER_POLICY_ORIGIN,
      REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_CROSS_ORIGIN,
      REFERRER_POLICY_ORIGIN_CLEAR_ON_TRANSITION_FROM_SECURE_TO_INSECURE,
      REFERRER_POLICY_NO_REFERRER,
      REFERRER_POLICY_LAST_VALUE;
   }

   public static enum ResourceType {
      RT_MAIN_FRAME,
      RT_SUB_FRAME,
      RT_STYLESHEET,
      RT_SCRIPT,
      RT_IMAGE,
      RT_FONT_RESOURCE,
      RT_SUB_RESOURCE,
      RT_OBJECT,
      RT_MEDIA,
      RT_WORKER,
      RT_SHARED_WORKER,
      RT_PREFETCH,
      RT_FAVICON,
      RT_XHR,
      RT_PING,
      RT_SERVICE_WORKER,
      RT_CSP_REPORT,
      RT_PLUGIN_RESOURCE,
      RT_NAVIGATION_PRELOAD_MAIN_FRAME,
      RT_NAVIGATION_PRELOAD_SUB_FRAME;
   }

   public static enum TransitionFlags {
      TT_BLOCKED_FLAG(8388608),
      TT_FORWARD_BACK_FLAG(16777216),
      TT_CHAIN_START_FLAG(268435456),
      TT_CHAIN_END_FLAG(536870912),
      TT_CLIENT_REDIRECT_FLAG(1073741824),
      TT_SERVER_REDIRECT_FLAG(Integer.MIN_VALUE);

      private final int flag;

      private TransitionFlags(int var3) {
         this.flag = var3;
      }

      public int getValue() {
         return this.flag;
      }
   }

   public static enum TransitionType {
      TT_LINK(0),
      TT_EXPLICIT(1),
      TT_AUTO_SUBFRAME(3),
      TT_MANUAL_SUBFRAME(4),
      TT_FORM_SUBMIT(7),
      TT_RELOAD(8);

      private int value;

      private TransitionType(int var3) {
         this.value = var3;
      }

      public int getValue() {
         return this.value;
      }

      public int getSource() {
         return this.value & 0xFF;
      }

      public void addQualifier(CefRequest.TransitionFlags var1) {
         this.value = this.value | var1.getValue();
      }

      public void addQualifiers(int var1) {
         this.value |= var1 & -256;
      }

      public int getQualifiers() {
         return this.value & -256;
      }

      public void removeQualifier(CefRequest.TransitionFlags var1) {
         this.value = this.value & ~var1.getValue();
      }

      public boolean isSet(CefRequest.TransitionFlags var1) {
         return (this.value & var1.getValue()) != 0;
      }

      public boolean isRedirect() {
         return (this.value & -1073741824) != 0;
      }
   }
}
