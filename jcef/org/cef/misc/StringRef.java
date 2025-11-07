package org.cef.misc;

public class StringRef {
   private String value_;

   public StringRef() {
   }

   public StringRef(String var1) {
      this.value_ = var1;
   }

   public void set(String var1) {
      this.value_ = var1;
   }

   public String get() {
      return this.value_;
   }
}
