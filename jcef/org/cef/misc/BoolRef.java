package org.cef.misc;

public class BoolRef {
   private boolean value_;

   public BoolRef() {
   }

   public BoolRef(boolean var1) {
      this.value_ = var1;
   }

   public void set(boolean var1) {
      this.value_ = var1;
   }

   public boolean get() {
      return this.value_;
   }
}
