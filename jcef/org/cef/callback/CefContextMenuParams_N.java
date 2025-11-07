package org.cef.callback;

import java.util.Vector;

class CefContextMenuParams_N extends CefNativeAdapter implements CefContextMenuParams {
   @Override
   public int getXCoord() {
      try {
         return this.N_GetXCoord(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public int getYCoord() {
      try {
         return this.N_GetYCoord(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public int getTypeFlags() {
      try {
         return this.N_GetTypeFlags(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public String getLinkUrl() {
      try {
         return this.N_GetLinkUrl(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getUnfilteredLinkUrl() {
      try {
         return this.N_GetUnfilteredLinkUrl(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getSourceUrl() {
      try {
         return this.N_GetSourceUrl(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean hasImageContents() {
      try {
         return this.N_HasImageContents(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public String getPageUrl() {
      try {
         return this.N_GetPageUrl(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFrameUrl() {
      try {
         return this.N_GetFrameUrl(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getFrameCharset() {
      try {
         return this.N_GetFrameCharset(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public CefContextMenuParams.MediaType getMediaType() {
      try {
         return this.N_GetMediaType(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public int getMediaStateFlags() {
      try {
         return this.N_GetMediaStateFlags(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   @Override
   public String getSelectionText() {
      try {
         return this.N_GetSelectionText(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public String getMisspelledWord() {
      try {
         return this.N_GetMisspelledWord(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public boolean getDictionarySuggestions(Vector<String> var1) {
      try {
         return this.N_GetDictionarySuggestions(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isEditable() {
      try {
         return this.N_IsEditable(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean isSpellCheckEnabled() {
      try {
         return this.N_IsSpellCheckEnabled(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public int getEditStateFlags() {
      try {
         return this.N_GetEditStateFlags(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   private final native int N_GetXCoord(long var1);

   private final native int N_GetYCoord(long var1);

   private final native int N_GetTypeFlags(long var1);

   private final native String N_GetLinkUrl(long var1);

   private final native String N_GetUnfilteredLinkUrl(long var1);

   private final native String N_GetSourceUrl(long var1);

   private final native boolean N_HasImageContents(long var1);

   private final native String N_GetPageUrl(long var1);

   private final native String N_GetFrameUrl(long var1);

   private final native String N_GetFrameCharset(long var1);

   private final native CefContextMenuParams.MediaType N_GetMediaType(long var1);

   private final native int N_GetMediaStateFlags(long var1);

   private final native String N_GetSelectionText(long var1);

   private final native String N_GetMisspelledWord(long var1);

   private final native boolean N_GetDictionarySuggestions(long var1, Vector<String> var3);

   private final native boolean N_IsEditable(long var1);

   private final native boolean N_IsSpellCheckEnabled(long var1);

   private final native int N_GetEditStateFlags(long var1);
}
