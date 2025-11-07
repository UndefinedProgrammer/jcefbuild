package org.cef.handler;

import org.cef.CefApp;
import org.cef.callback.CefCommandLine;
import org.cef.callback.CefSchemeRegistrar;

public abstract class CefAppHandlerAdapter implements CefAppHandler {
   private String[] args_;

   public CefAppHandlerAdapter(String[] var1) {
      this.args_ = var1;
   }

   @Override
   public void onBeforeCommandLineProcessing(String var1, CefCommandLine var2) {
      if (var1.isEmpty() && this.args_ != null) {
         boolean var3 = false;

         for (String var7 : this.args_) {
            if (!var3 && var7.length() >= 2) {
               int var8 = var7.startsWith("--") ? 2 : (var7.startsWith("/") ? 1 : (var7.startsWith("-") ? 1 : 0));
               switch (var8) {
                  case 0:
                     var2.appendArgument(var7);
                     break;
                  case 2:
                     if (var7.length() == 2) {
                        var3 = true;
                        break;
                     }
                  case 1:
                     String[] var9 = var7.substring(var8).split("=");
                     if (var9.length == 2) {
                        var2.appendSwitchWithValue(var9[0], var9[1]);
                     } else {
                        var2.appendSwitch(var9[0]);
                     }
               }
            } else {
               var2.appendArgument(var7);
            }
         }
      }
   }

   @Override
   public boolean onBeforeTerminate() {
      return false;
   }

   @Override
   public void stateHasChanged(CefApp.CefAppState var1) {
   }

   @Override
   public void onRegisterCustomSchemes(CefSchemeRegistrar var1) {
   }

   @Override
   public void onContextInitialized() {
   }

   @Override
   public void onScheduleMessagePumpWork(long var1) {
      CefApp.getInstance().doMessageLoopWork(var1);
   }
}
