package org.cef;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.cef.callback.CefSchemeHandlerFactory;
import org.cef.handler.CefAppHandler;
import org.cef.handler.CefAppHandlerAdapter;

public class CefApp extends CefAppHandlerAdapter {
   private static CefApp self = null;
   private static CefAppHandler appHandler_ = null;
   private static CefApp.CefAppState state_ = CefApp.CefAppState.NONE;
   private Timer workTimer_ = null;
   private HashSet<CefClient> clients_ = new HashSet<>();
   private CefSettings settings_ = null;

   private CefApp(String[] var1, CefSettings var2) throws UnsatisfiedLinkError {
      super(var1);
      if (var2 != null) {
         this.settings_ = var2.clone();
      }

      if (OS.isWindows()) {
         SystemBootstrap.loadLibrary("jawt");
         SystemBootstrap.loadLibrary("chrome_elf");
         SystemBootstrap.loadLibrary("libcef");
         SystemBootstrap.loadLibrary("jcef");
      } else if (OS.isLinux()) {
         SystemBootstrap.loadLibrary("cef");
      }

      if (appHandler_ == null) {
         appHandler_ = this;
      }

      try {
         Runnable var3 = new Runnable() {
            @Override
            public void run() {
               if (!CefApp.this.N_PreInitialize()) {
                  throw new IllegalStateException("Failed to pre-initialize native code");
               }
            }
         };
         if (SwingUtilities.isEventDispatchThread()) {
            var3.run();
         } else {
            SwingUtilities.invokeAndWait(var3);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   public static void addAppHandler(CefAppHandler var0) throws IllegalStateException {
      if (getState().compareTo(CefApp.CefAppState.NEW) > 0) {
         throw new IllegalStateException("Must be called before CefApp is initialized");
      } else {
         appHandler_ = var0;
      }
   }

   public static synchronized CefApp getInstance() throws UnsatisfiedLinkError {
      return getInstance(null, null);
   }

   public static synchronized CefApp getInstance(String[] var0) throws UnsatisfiedLinkError {
      return getInstance(var0, null);
   }

   public static synchronized CefApp getInstance(CefSettings var0) throws UnsatisfiedLinkError {
      return getInstance(null, var0);
   }

   public static synchronized CefApp getInstance(String[] var0, CefSettings var1) throws UnsatisfiedLinkError {
      if (var1 != null && getState() != CefApp.CefAppState.NONE && getState() != CefApp.CefAppState.NEW) {
         throw new IllegalStateException("Settings can only be passed to CEF before createClient is called the first time.");
      } else {
         if (self == null) {
            if (getState() == CefApp.CefAppState.TERMINATED) {
               throw new IllegalStateException("CefApp was terminated");
            }

            self = new CefApp(var0, var1);
            setState(CefApp.CefAppState.NEW);
         }

         return self;
      }
   }

   public final void setSettings(CefSettings var1) throws IllegalStateException {
      if (getState() != CefApp.CefAppState.NONE && getState() != CefApp.CefAppState.NEW) {
         throw new IllegalStateException("Settings can only be passed to CEF before createClient is called the first time.");
      } else {
         this.settings_ = var1.clone();
      }
   }

   public final CefApp.CefVersion getVersion() {
      try {
         return this.N_GetVersion();
      } catch (UnsatisfiedLinkError var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public static final CefApp.CefAppState getState() {
      synchronized (state_) {
         return state_;
      }
   }

   private static final void setState(final CefApp.CefAppState var0) {
      synchronized (state_) {
         state_ = var0;
      }

      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            if (CefApp.appHandler_ != null) {
               CefApp.appHandler_.stateHasChanged(var0);
            }
         }
      });
   }

   public final synchronized void dispose() {
      switch (getState()) {
         case NEW:
            setState(CefApp.CefAppState.TERMINATED);
            break;
         case INITIALIZING:
         case INITIALIZED:
            setState(CefApp.CefAppState.SHUTTING_DOWN);
            if (this.clients_.isEmpty()) {
               this.shutdown();
            } else {
               for (CefClient var3 : new HashSet<>(this.clients_)) {
                  var3.dispose();
               }
            }
         case NONE:
         case SHUTTING_DOWN:
         case TERMINATED:
      }
   }

   public synchronized CefClient createClient() {
      switch (getState()) {
         case NEW:
            setState(CefApp.CefAppState.INITIALIZING);
            this.initialize();
         case INITIALIZING:
         case INITIALIZED:
            CefClient var1 = new CefClient();
            this.clients_.add(var1);
            return var1;
         default:
            throw new IllegalStateException("Can't crate client in state " + state_);
      }
   }

   public boolean registerSchemeHandlerFactory(String var1, String var2, CefSchemeHandlerFactory var3) {
      try {
         return this.N_RegisterSchemeHandlerFactory(var1, var2, var3);
      } catch (Exception var5) {
         var5.printStackTrace();
         return false;
      }
   }

   public boolean clearSchemeHandlerFactories() {
      try {
         return this.N_ClearSchemeHandlerFactories();
      } catch (Exception var2) {
         var2.printStackTrace();
         return false;
      }
   }

   protected final synchronized void clientWasDisposed(CefClient var1) {
      this.clients_.remove(var1);
      if (this.clients_.isEmpty() && getState().compareTo(CefApp.CefAppState.SHUTTING_DOWN) >= 0) {
         this.shutdown();
      }
   }

   private final void initialize() {
      try {
         Runnable var1 = new Runnable() {
            @Override
            public void run() {
               String var1x = CefApp.getJcefLibPath();
               System.out.println("initialize on " + Thread.currentThread() + " with library path " + var1x);
               CefSettings var2x = CefApp.this.settings_ != null ? CefApp.this.settings_ : new CefSettings();
               if (OS.isMacintosh()) {
                  if (var2x.browser_subprocess_path == null) {
                     Path var3 = Paths.get(var1x, "../Frameworks/jcef Helper.app/Contents/MacOS/jcef Helper");
                     var2x.browser_subprocess_path = var3.normalize().toAbsolutePath().toString();
                  }
               } else if (OS.isWindows()) {
                  if (var2x.browser_subprocess_path == null) {
                     Path var4 = Paths.get(var1x, "jcef_helper.exe");
                     var2x.browser_subprocess_path = var4.normalize().toAbsolutePath().toString();
                  }
               } else if (OS.isLinux()) {
                  if (var2x.browser_subprocess_path == null) {
                     Path var5 = Paths.get(var1x, "jcef_helper");
                     var2x.browser_subprocess_path = var5.normalize().toAbsolutePath().toString();
                  }

                  if (var2x.resources_dir_path == null) {
                     Path var6 = Paths.get(var1x);
                     var2x.resources_dir_path = var6.normalize().toAbsolutePath().toString();
                  }

                  if (var2x.locales_dir_path == null) {
                     Path var7 = Paths.get(var1x, "locales");
                     var2x.locales_dir_path = var7.normalize().toAbsolutePath().toString();
                  }
               }

               if (CefApp.this.N_Initialize(CefApp.appHandler_, var2x)) {
                  CefApp.setState(CefApp.CefAppState.INITIALIZED);
               }
            }
         };
         if (SwingUtilities.isEventDispatchThread()) {
            var1.run();
         } else {
            SwingUtilities.invokeAndWait(var1);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   protected final void handleBeforeTerminate() {
      System.out.println("Cmd+Q termination request.");
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            var var1 = (CefAppHandler & CefAppHandler)(CefApp.appHandler_ == null ? this : CefApp.appHandler_);
            if (!var1.onBeforeTerminate()) {
               CefApp.this.dispose();
            }
         }
      });
   }

   private final void shutdown() {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            System.out.println("shutdown on " + Thread.currentThread());
            CefApp.this.N_Shutdown();
            CefApp.setState(CefApp.CefAppState.TERMINATED);
            CefApp.self = null;
         }
      });
   }

   public final void doMessageLoopWork(final long var1) {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            if (CefApp.getState() != CefApp.CefAppState.TERMINATED) {
               if (CefApp.this.workTimer_ != null) {
                  CefApp.this.workTimer_.stop();
                  CefApp.this.workTimer_ = null;
               }

               if (var1 <= 0L) {
                  CefApp.this.N_DoMessageLoopWork();
                  CefApp.this.doMessageLoopWork(33L);
               } else {
                  long var3 = var1;
                  if (var3 > 33L) {
                     var3 = 33L;
                  }

                  CefApp.this.workTimer_ = new Timer((int)var3, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent var1x) {
                        CefApp.this.workTimer_.stop();
                        CefApp.this.workTimer_ = null;
                        CefApp.this.N_DoMessageLoopWork();
                        CefApp.this.doMessageLoopWork(33L);
                     }
                  });
                  CefApp.this.workTimer_.start();
               }
            }
         }
      });
   }

   public static final boolean startup(String[] var0) {
      if (!OS.isLinux() && !OS.isMacintosh()) {
         return true;
      } else {
         SystemBootstrap.loadLibrary("jcef");
         return N_Startup(OS.isMacintosh() ? getCefFrameworkPath(var0) : null);
      }
   }

   private static final String getJcefLibPath() {
      String var0 = System.getProperty("java.library.path");
      String[] var1 = var0.split(System.getProperty("path.separator"));

      for (String var5 : var1) {
         File var6 = new File(var5);
         String[] var7 = var6.list(new FilenameFilter() {
            @Override
            public boolean accept(File var1, String var2) {
               return var2.equalsIgnoreCase("libjcef.dylib") || var2.equalsIgnoreCase("libjcef.so") || var2.equalsIgnoreCase("jcef.dll");
            }
         });
         if (var7 != null && var7.length != 0) {
            return var5;
         }
      }

      return var0;
   }

   private static final String getCefFrameworkPath(String[] var0) {
      String var1 = "--framework-dir-path=";

      for (String var5 : var0) {
         if (var5.startsWith(var1)) {
            return new File(var5.substring(var1.length())).getAbsolutePath();
         }
      }

      return new File(getJcefLibPath() + "/../Frameworks/Chromium Embedded Framework.framework").getAbsolutePath();
   }

   private static final native boolean N_Startup(String var0);

   private final native boolean N_PreInitialize();

   private final native boolean N_Initialize(CefAppHandler var1, CefSettings var2);

   private final native void N_Shutdown();

   private final native void N_DoMessageLoopWork();

   private final native CefApp.CefVersion N_GetVersion();

   private final native boolean N_RegisterSchemeHandlerFactory(String var1, String var2, CefSchemeHandlerFactory var3);

   private final native boolean N_ClearSchemeHandlerFactories();

   public static enum CefAppState {
      NONE,
      NEW,
      INITIALIZING,
      INITIALIZED,
      SHUTTING_DOWN,
      TERMINATED;
   }

   public final class CefVersion {
      public final int JCEF_COMMIT_NUMBER;
      public final int CEF_VERSION_MAJOR;
      public final int CEF_VERSION_MINOR;
      public final int CEF_VERSION_PATCH;
      public final int CEF_COMMIT_NUMBER;
      public final int CHROME_VERSION_MAJOR;
      public final int CHROME_VERSION_MINOR;
      public final int CHROME_VERSION_BUILD;
      public final int CHROME_VERSION_PATCH;

      private CefVersion(int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
         this.JCEF_COMMIT_NUMBER = var2;
         this.CEF_VERSION_MAJOR = var3;
         this.CEF_VERSION_MINOR = var4;
         this.CEF_VERSION_PATCH = var5;
         this.CEF_COMMIT_NUMBER = var6;
         this.CHROME_VERSION_MAJOR = var7;
         this.CHROME_VERSION_MINOR = var8;
         this.CHROME_VERSION_BUILD = var9;
         this.CHROME_VERSION_PATCH = var10;
      }

      public String getJcefVersion() {
         return this.CEF_VERSION_MAJOR + "." + this.CEF_VERSION_MINOR + "." + this.CEF_VERSION_PATCH + "." + this.JCEF_COMMIT_NUMBER;
      }

      public String getCefVersion() {
         return this.CEF_VERSION_MAJOR + "." + this.CEF_VERSION_MINOR + "." + this.CEF_VERSION_PATCH;
      }

      public String getChromeVersion() {
         return this.CHROME_VERSION_MAJOR + "." + this.CHROME_VERSION_MINOR + "." + this.CHROME_VERSION_BUILD + "." + this.CHROME_VERSION_PATCH;
      }

      @Override
      public String toString() {
         return "JCEF Version = " + this.getJcefVersion() + "\nCEF Version = " + this.getCefVersion() + "\nChromium Version = " + this.getChromeVersion();
      }
   }
}
