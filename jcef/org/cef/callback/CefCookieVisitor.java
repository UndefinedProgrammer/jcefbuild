package org.cef.callback;

import org.cef.misc.BoolRef;
import org.cef.network.CefCookie;

public interface CefCookieVisitor {
   boolean visit(CefCookie var1, int var2, int var3, BoolRef var4);
}
