package org.cef.callback;

import java.util.Date;

class CefDownloadItem_N extends CefNativeAdapter implements CefDownloadItem {
   @Override
   public boolean isValid() {
      try {
         return this.N_IsValid(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isInProgress() {
      try {
         return this.N_IsInProgress(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isComplete() {
      try {
         return this.N_IsComplete(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isCanceled() {
      try {
         return this.N_IsCanceled(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public long getCurrentSpeed() {
      try {
         return this.N_GetCurrentSpeed(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0L;
      }
   }

   @Override
   public int getPercentComplete() {
      try {
         return this.N_GetPercentComplete(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public long getTotalBytes() {
      try {
         return this.N_GetTotalBytes(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0L;
      }
   }

   @Override
   public long getReceivedBytes() {
      try {
         return this.N_GetReceivedBytes(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0L;
      }
   }

   @Override
   public Date getStartTime() {
      try {
         return this.N_GetStartTime(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public Date getEndTime() {
      try {
         return this.N_GetEndTime(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFullPath() {
      try {
         return this.N_GetFullPath(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public int getId() {
      try {
         return this.N_GetId(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public String getURL() {
      try {
         return this.N_GetURL(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getSuggestedFileName() {
      try {
         return this.N_GetSuggestedFileName(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getContentDisposition() {
      try {
         return this.N_GetContentDisposition(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getMimeType() {
      try {
         return this.N_GetMimeType(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   private final native boolean N_IsValid(long var1);

   private final native boolean N_IsInProgress(long var1);

   private final native boolean N_IsComplete(long var1);

   private final native boolean N_IsCanceled(long var1);

   private final native long N_GetCurrentSpeed(long var1);

   private final native int N_GetPercentComplete(long var1);

   private final native long N_GetTotalBytes(long var1);

   private final native long N_GetReceivedBytes(long var1);

   private final native Date N_GetStartTime(long var1);

   private final native Date N_GetEndTime(long var1);

   private final native String N_GetFullPath(long var1);

   private final native int N_GetId(long var1);

   private final native String N_GetURL(long var1);

   private final native String N_GetSuggestedFileName(long var1);

   private final native String N_GetContentDisposition(long var1);

   private final native String N_GetMimeType(long var1);
}
