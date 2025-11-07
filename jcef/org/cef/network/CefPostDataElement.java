package org.cef.network;

public abstract class CefPostDataElement {
   CefPostDataElement() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefPostDataElement create() {
      return CefPostDataElement_N.createNative();
   }

   public abstract void dispose();

   public abstract boolean isReadOnly();

   public abstract void setToEmpty();

   public abstract void setToFile(String var1);

   public abstract void setToBytes(int var1, byte[] var2);

   public abstract CefPostDataElement.Type getType();

   public abstract String getFile();

   public abstract int getBytesCount();

   public abstract int getBytes(int var1, byte[] var2);

   @Override
   public String toString() {
      return this.toString(null);
   }

   public String toString(String var1) {
      int var2 = this.getBytesCount();
      byte[] var3 = null;
      if (var2 > 0) {
         var3 = new byte[var2];
      }

      boolean var4 = false;
      if (var1 != null) {
         if (var1.startsWith("text/")) {
            var4 = true;
         } else if (var1.startsWith("application/xml")) {
            var4 = true;
         } else if (var1.startsWith("application/xhtml")) {
            var4 = true;
         } else if (var1.startsWith("application/x-www-form-urlencoded")) {
            var4 = true;
         }
      }

      String var5 = "";
      if (this.getType() == CefPostDataElement.Type.PDE_TYPE_BYTES) {
         int var6 = this.getBytes(var3.length, var3);
         var5 = var5 + "    Content-Length: " + var2 + "\n";
         if (var4) {
            var5 = var5 + "\n    " + new String(var3);
         } else {
            for (int var7 = 0; var7 < var6; var7++) {
               if (var7 % 40 == 0) {
                  var5 = var5 + "\n    ";
               }

               var5 = var5 + String.format("%02X", var3[var7]) + " ";
            }
         }

         var5 = var5 + "\n";
      } else if (this.getType() == CefPostDataElement.Type.PDE_TYPE_FILE) {
         var5 = var5 + "\n    Bytes of file: " + this.getFile() + "\n";
      }

      return var5;
   }

   public static enum Type {
      PDE_TYPE_EMPTY,
      PDE_TYPE_BYTES,
      PDE_TYPE_FILE;
   }
}
