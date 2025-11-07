package org.cef.callback;

import java.io.OutputStream;
import java.util.Vector;

class CefDragData_N extends CefDragData implements CefNative {
   private long N_CefHandle = 0L;

   @Override
   public void setNativeRef(String var1, long var2) {
      this.N_CefHandle = var2;
   }

   @Override
   public long getNativeRef(String var1) {
      return this.N_CefHandle;
   }

   public static CefDragData createNative() {
      try {
         return N_Create();
      } catch (UnsatisfiedLinkError var1) {
         var1.printStackTrace();
         return null;
      }
   }

   @Override
   public CefDragData clone() {
      try {
         return this.N_Clone(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
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
         return true;
      }
   }

   @Override
   public boolean isLink() {
      try {
         return this.N_IsLink(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isFragment() {
      try {
         return this.N_IsFragment(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isFile() {
      try {
         return this.N_IsFile(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public String getLinkURL() {
      try {
         return this.N_GetLinkURL(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getLinkTitle() {
      try {
         return this.N_GetLinkTitle(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getLinkMetadata() {
      try {
         return this.N_GetLinkMetadata(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFragmentText() {
      try {
         return this.N_GetFragmentText(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFragmentHtml() {
      try {
         return this.N_GetFragmentHtml(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFragmentBaseURL() {
      try {
         return this.N_GetFragmentBaseURL(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public int getFileContents(OutputStream var1) {
      try {
         return this.N_GetFileContents(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return 0;
      }
   }

   @Override
   public String getFileName() {
      try {
         return this.N_GetFileName(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean getFileNames(Vector<String> var1) {
      try {
         return this.N_GetFileNames(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public void setLinkURL(String var1) {
      try {
         this.N_SetLinkURL(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setLinkTitle(String var1) {
      try {
         this.N_SetLinkTitle(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setLinkMetadata(String var1) {
      try {
         this.N_SetLinkMetadata(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setFragmentText(String var1) {
      try {
         this.N_SetFragmentText(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setFragmentHtml(String var1) {
      try {
         this.N_SetFragmentHtml(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void setFragmentBaseURL(String var1) {
      try {
         this.N_SetFragmentBaseURL(this.N_CefHandle, var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void resetFileContents() {
      try {
         this.N_ResetFileContents(this.N_CefHandle);
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void addFile(String var1, String var2) {
      try {
         this.N_AddFile(this.N_CefHandle, var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   private static final native CefDragData_N N_Create();

   private final native CefDragData_N N_Clone(long var1);

   private final native void N_Dispose(long var1);

   private final native boolean N_IsReadOnly(long var1);

   private final native boolean N_IsLink(long var1);

   private final native boolean N_IsFragment(long var1);

   private final native boolean N_IsFile(long var1);

   private final native String N_GetLinkURL(long var1);

   private final native String N_GetLinkTitle(long var1);

   private final native String N_GetLinkMetadata(long var1);

   private final native String N_GetFragmentText(long var1);

   private final native String N_GetFragmentHtml(long var1);

   private final native String N_GetFragmentBaseURL(long var1);

   private final native int N_GetFileContents(long var1, OutputStream var3);

   private final native String N_GetFileName(long var1);

   private final native boolean N_GetFileNames(long var1, Vector<String> var3);

   private final native void N_SetLinkURL(long var1, String var3);

   private final native void N_SetLinkTitle(long var1, String var3);

   private final native void N_SetLinkMetadata(long var1, String var3);

   private final native void N_SetFragmentText(long var1, String var3);

   private final native void N_SetFragmentHtml(long var1, String var3);

   private final native void N_SetFragmentBaseURL(long var1, String var3);

   private final native void N_ResetFileContents(long var1);

   private final native void N_AddFile(long var1, String var3, String var4);

   @Override
   public String toString() {
      Vector var1 = new Vector();
      this.getFileNames(var1);
      String var2 = "{";

      for (String var4 : var1) {
         var2 = var2 + var4 + ",";
      }

      var2 = var2 + "}";
      return "CefDragData_N [isLink()="
         + this.isLink()
         + ", isFragment()="
         + this.isFragment()
         + ", isFile()="
         + this.isFile()
         + ", getLinkURL()="
         + this.getLinkURL()
         + ", getLinkTitle()="
         + this.getLinkTitle()
         + ", getLinkMetadata()="
         + this.getLinkMetadata()
         + ", getFragmentText()="
         + this.getFragmentText()
         + ", getFragmentHtml()="
         + this.getFragmentHtml()
         + ", getFragmentBaseURL()="
         + this.getFragmentBaseURL()
         + ", getFileName()="
         + this.getFileName()
         + ", getFileNames(vector)="
         + var2
         + "]";
   }
}
