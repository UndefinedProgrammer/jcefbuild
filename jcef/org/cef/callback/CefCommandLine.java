package org.cef.callback;

import java.util.Map;
import java.util.Vector;

public interface CefCommandLine {
   void reset();

   String getProgram();

   void setProgram(String var1);

   boolean hasSwitches();

   boolean hasSwitch(String var1);

   String getSwitchValue(String var1);

   Map<String, String> getSwitches();

   void appendSwitch(String var1);

   void appendSwitchWithValue(String var1, String var2);

   boolean hasArguments();

   Vector<String> getArguments();

   void appendArgument(String var1);
}
