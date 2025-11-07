package org.cef.browser;

public interface CefFrame {
   void dispose();

   long getIdentifier();

   String getURL();

   String getName();

   boolean isMain();

   boolean isValid();

   boolean isFocused();

   CefFrame getParent();

   void executeJavaScript(String var1, String var2, int var3);

   void undo();

   void redo();

   void cut();

   void copy();

   void paste();

   void selectAll();
}
