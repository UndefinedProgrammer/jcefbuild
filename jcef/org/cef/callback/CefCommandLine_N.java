package org.cef.callback;

import java.util.Map;
import java.util.Vector;

class CefCommandLine_N extends CefNativeAdapter implements CefCommandLine {
   @Override
   public void reset() {
      try {
         this.N_Reset(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
      }
   }

   @Override
   public String getProgram() {
      try {
         return this.N_GetProgram(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void setProgram(String var1) {
      try {
         this.N_SetProgram(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public boolean hasSwitches() {
      try {
         return this.N_HasSwitches(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean hasSwitch(String var1) {
      try {
         return this.N_HasSwitch(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   public String getSwitchValue(String var1) {
      try {
         return this.N_GetSwitchValue(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
         return null;
      }
   }

   @Override
   public Map<String, String> getSwitches() {
      try {
         return this.N_GetSwitches(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void appendSwitch(String var1) {
      try {
         this.N_AppendSwitch(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public void appendSwitchWithValue(String var1, String var2) {
      try {
         this.N_AppendSwitchWithValue(this.getNativeRef(null), var1, var2);
      } catch (UnsatisfiedLinkError var4) {
         var4.printStackTrace();
      }
   }

   @Override
   public boolean hasArguments() {
      try {
         return this.N_HasArguments(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return false;
      }
   }

   @Override
   public Vector<String> getArguments() {
      try {
         return this.N_GetArguments(this.getNativeRef(null));
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @Override
   public void appendArgument(String var1) {
      try {
         this.N_AppendArgument(this.getNativeRef(null), var1);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   @Override
   public String toString() {
      String var1 = "CefCommandLine [program='" + this.getProgram() + "'";
      if (this.hasSwitches()) {
         Map var2 = this.getSwitches();
         var1 = var1 + ", switches=" + var2;
      }

      if (this.hasArguments()) {
         Vector var3 = this.getArguments();
         var1 = var1 + ", arguments=" + var3;
      }

      return var1 + "]";
   }

   private final native void N_Reset(long var1);

   private final native String N_GetProgram(long var1);

   private final native void N_SetProgram(long var1, String var3);

   private final native boolean N_HasSwitches(long var1);

   private final native boolean N_HasSwitch(long var1, String var3);

   private final native String N_GetSwitchValue(long var1, String var3);

   private final native Map<String, String> N_GetSwitches(long var1);

   private final native void N_AppendSwitch(long var1, String var3);

   private final native void N_AppendSwitchWithValue(long var1, String var3, String var4);

   private final native boolean N_HasArguments(long var1);

   private final native Vector<String> N_GetArguments(long var1);

   private final native void N_AppendArgument(long var1, String var3);
}
