package org.cef.misc;

public class IntRef {
   private int value_;

   public IntRef() {
   }

   public IntRef(int var1) {
      this.value_ = var1;
   }

   public void set(int var1) {
      this.value_ = var1;
   }

   public int get() {
      return this.value_;
   }
}
