package org.cef.browser;

import org.cef.callback.CefNativeAdapter;

class CefFrame_N extends CefNativeAdapter implements CefFrame {
   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   @Override
   public void dispose() {
      try {
         this.N_Dispose(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public long getIdentifier() {
      try {
         return this.N_GetIdentifier(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return -1L;
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
   public String getName() {
      try {
         return this.N_GetName(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean isMain() {
      try {
         return this.N_IsMain(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

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
   public boolean isFocused() {
      try {
         return this.N_IsFocused(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public CefFrame getParent() {
      try {
         return this.N_GetParent(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void executeJavaScript(String var1, String var2, int var3) {
      try {
         this.N_ExecuteJavaScript(this.getNativeRef(null), var1, var2, var3);
      } catch (UnsatisfiedLinkError var5) {
         var5.printStackTrace();
      }
   }

   @Override
   public void undo() {
      try {
         this.N_Undo(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void redo() {
      try {
         this.N_Redo(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void cut() {
      try {
         this.N_Cut(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void copy() {
      try {
         this.N_Copy(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void paste() {
      try {
         this.N_Paste(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public void selectAll() {
      try {
         this.N_SelectAll(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   private final native void N_Dispose(long var1);

   private final native long N_GetIdentifier(long var1);

   private final native String N_GetURL(long var1);

   private final native String N_GetName(long var1);

   private final native boolean N_IsMain(long var1);

   private final native boolean N_IsValid(long var1);

   private final native boolean N_IsFocused(long var1);

   private final native CefFrame N_GetParent(long var1);

   private final native void N_ExecuteJavaScript(long var1, String var3, String var4, int var5);

   private final native void N_Undo(long var1);

   private final native void N_Redo(long var1);

   private final native void N_Cut(long var1);

   private final native void N_Copy(long var1);

   private final native void N_Paste(long var1);

   private final native void N_SelectAll(long var1);
}
