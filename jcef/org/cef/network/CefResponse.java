package org.cef.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.cef.handler.CefLoadHandler;

public abstract class CefResponse {
   CefResponse() {
   }

   @Override
   protected void finalize() throws Throwable {
      this.dispose();
      super.finalize();
   }

   public static final CefResponse create() {
      return CefResponse_N.createNative();
   }

   public abstract void dispose();

   public abstract boolean isReadOnly();

   public abstract CefLoadHandler.ErrorCode getError();

   public abstract void setError(CefLoadHandler.ErrorCode var1);

   public abstract int getStatus();

   public abstract void setStatus(int var1);

   public abstract String getStatusText();

   public abstract void setStatusText(String var1);

   public abstract String getMimeType();

   public abstract void setMimeType(String var1);

   public abstract String getHeaderByName(String var1);

   public abstract void setHeaderByName(String var1, String var2, boolean var3);

   public abstract void getHeaderMap(Map<String, String> var1);

   public abstract void setHeaderMap(Map<String, String> var1);

   @Override
   public String toString() {
      String var1 = "\nHTTP-Response:";
      var1 = var1 + "\n  error: " + this.getError();
      var1 = var1 + "\n  readOnly: " + this.isReadOnly();
      var1 = var1 + "\n    HTTP/1.1 " + this.getStatus() + " " + this.getStatusText();
      var1 = var1 + "\n    Content-Type: " + this.getMimeType();
      HashMap var2 = new HashMap();
      this.getHeaderMap(var2);

      for (Entry var5 : var2.entrySet()) {
         var1 = var1 + "    " + (String)var5.getKey() + "=" + (String)var5.getValue() + "\n";
      }

      return var1;
   }
}
