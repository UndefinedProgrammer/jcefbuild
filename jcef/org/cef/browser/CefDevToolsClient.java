package org.cef.browser;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CefDevToolsClient implements AutoCloseable {
   private final Map<Integer, CompletableFuture<String>> queuedCommands_ = Collections.synchronizedMap(new HashMap<>());
   private final Set<CefDevToolsClient.EventListener> eventListeners_ = Collections.synchronizedSet(new LinkedHashSet<>());
   private CefRegistration registration_;
   private final CefBrowser_N browser_;

   CefDevToolsClient(CefBrowser_N var1) {
      this.browser_ = var1;
      this.registration_ = var1.addDevToolsMessageObserver(new CefDevToolsMessageObserver() {
         @Override
         public void onDevToolsMethodResult(CefBrowser var1, int var2, boolean var3, String var4) {
            CompletableFuture var5 = CefDevToolsClient.this.getQueuedCommand(var2);
            if (var3) {
               var5.complete(var4);
            } else {
               var5.completeExceptionally(new CefDevToolsClient.DevToolsException("DevTools method failed", var4));
            }
         }

         @Override
         public void onDevToolsEvent(CefBrowser var1, String var2, String var3) {
            for (CefDevToolsClient.EventListener var5 : CefDevToolsClient.this.eventListeners_) {
               var5.onEvent(var2, var3);
            }
         }
      });
   }

   @Override
   public void close() {
      this.queuedCommands_.clear();
      this.eventListeners_.clear();
      this.registration_ = null;
   }

   public boolean isClosed() {
      return this.registration_ == null;
   }

   private CompletableFuture<String> getQueuedCommand(Integer var1) {
      return this.queuedCommands_.computeIfAbsent(var1, var0 -> new CompletableFuture<>());
   }

   public CompletableFuture<String> executeDevToolsMethod(String var1) {
      return this.executeDevToolsMethod(var1, null);
   }

   public CompletableFuture<String> executeDevToolsMethod(String var1, String var2) {
      if (this.isClosed()) {
         CompletableFuture var3 = new CompletableFuture();
         var3.completeExceptionally(new CefDevToolsClient.DevToolsException("Client is closed"));
         return var3;
      } else {
         return this.browser_.executeDevToolsMethod(var1, var2).thenCompose(this::getQueuedCommand);
      }
   }

   public void addEventListener(CefDevToolsClient.EventListener var1) {
      this.eventListeners_.add(var1);
   }

   public void removeEventListener(CefDevToolsClient.EventListener var1) {
      this.eventListeners_.remove(var1);
   }

   public static final class DevToolsException extends Exception {
      private static final long serialVersionUID = 3952948449841375372L;
      private final String json_;

      public DevToolsException(String var1) {
         this(var1, null);
      }

      public DevToolsException(String var1, String var2) {
         super(var1);
         this.json_ = var2;
      }

      @Override
      public String getMessage() {
         String var1 = super.getMessage();
         if (this.json_ != null) {
            var1 = var1 + ": " + this.json_;
         }

         return var1;
      }

      public String getJson() {
         return this.json_;
      }
   }

   public interface EventListener {
      void onEvent(String var1, String var2);
   }
}
