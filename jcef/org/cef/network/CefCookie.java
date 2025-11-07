package org.cef.network;

import java.util.Date;

public final class CefCookie {
   public final String name;
   public final String value;
   public final String domain;
   public final String path;
   public final boolean secure;
   public final boolean httponly;
   public final Date creation;
   public final Date lastAccess;
   public final boolean hasExpires;
   public final Date expires;

   public CefCookie(String var1, String var2, String var3, String var4, boolean var5, boolean var6, Date var7, Date var8, boolean var9, Date var10) {
      this.name = var1;
      this.value = var2;
      this.domain = var3;
      this.path = var4;
      this.secure = var5;
      this.httponly = var6;
      this.creation = var7;
      this.lastAccess = var8;
      this.hasExpires = var9;
      this.expires = var10;
   }
}
