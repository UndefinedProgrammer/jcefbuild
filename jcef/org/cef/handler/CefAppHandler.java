package org.cef.handler;

import org.cef.CefApp;
import org.cef.callback.CefCommandLine;
import org.cef.callback.CefSchemeRegistrar;

public interface CefAppHandler {
   void onBeforeCommandLineProcessing(String var1, CefCommandLine var2);

   boolean onBeforeTerminate();

   void stateHasChanged(CefApp.CefAppState var1);

   void onRegisterCustomSchemes(CefSchemeRegistrar var1);

   void onContextInitialized();

   void onScheduleMessagePumpWork(long var1);
}
