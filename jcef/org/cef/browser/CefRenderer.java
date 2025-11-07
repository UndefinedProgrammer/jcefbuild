package org.cef.browser;

import com.jogamp.opengl.GL2;
import java.awt.Rectangle;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

class CefRenderer {
   private boolean transparent_;
   private GL2 initialized_context_ = null;
   private int[] texture_id_ = new int[1];
   private int view_width_ = 0;
   private int view_height_ = 0;
   private float spin_x_ = 0.0F;
   private float spin_y_ = 0.0F;
   private Rectangle popup_rect_ = new Rectangle(0, 0, 0, 0);
   private Rectangle original_popup_rect_ = new Rectangle(0, 0, 0, 0);
   private boolean use_draw_pixels_ = false;

   protected CefRenderer(boolean var1) {
      this.transparent_ = var1;
   }

   protected boolean isTransparent() {
      return this.transparent_;
   }

   protected int getTextureID() {
      return this.texture_id_[0];
   }

   protected void initialize(GL2 var1) {
      if (this.initialized_context_ != var1) {
         this.initialized_context_ = var1;
         if (!var1.getContext().isHardwareRasterizer()) {
            System.out.println("opengl rendering may be slow as hardware rendering isn't available");
            this.use_draw_pixels_ = true;
         } else {
            var1.glHint(3155, 4354);
            var1.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
            var1.glPixelStorei(3317, 1);
            var1.glGenTextures(1, this.texture_id_, 0);

            assert this.texture_id_[0] != 0;

            var1.glBindTexture(3553, this.texture_id_[0]);
            var1.glTexParameteri(3553, 10241, 9728);
            var1.glTexParameteri(3553, 10240, 9728);
            var1.glTexEnvf(8960, 8704, 8448.0F);
         }
      }
   }

   protected void cleanup(GL2 var1) {
      if (this.texture_id_[0] != 0) {
         var1.glDeleteTextures(1, this.texture_id_, 0);
      }

      this.view_width_ = this.view_height_ = 0;
   }

   protected void render(GL2 var1) {
      if (!this.use_draw_pixels_ && this.view_width_ != 0 && this.view_height_ != 0) {
         assert this.initialized_context_ != null;

         float[] var2 = new float[]{0.0F, 1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, -1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -1.0F, 1.0F, 0.0F};
         FloatBuffer var3 = FloatBuffer.wrap(var2);
         var1.glClear(16384 | 256);
         var1.glMatrixMode(5888);
         var1.glLoadIdentity();
         var1.glViewport(0, 0, this.view_width_, this.view_height_);
         var1.glMatrixMode(5889);
         var1.glLoadIdentity();
         var1.glPushAttrib(1048575);
         var1.glBegin(7);
         var1.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
         var1.glVertex2f(-1.0F, -1.0F);
         var1.glVertex2f(1.0F, -1.0F);
         var1.glColor4f(0.0F, 0.0F, 1.0F, 1.0F);
         var1.glVertex2f(1.0F, 1.0F);
         var1.glVertex2f(-1.0F, 1.0F);
         var1.glEnd();
         var1.glPopAttrib();
         if (this.spin_x_ != 0.0F) {
            var1.glRotatef(-this.spin_x_, 1.0F, 0.0F, 0.0F);
         }

         if (this.spin_y_ != 0.0F) {
            var1.glRotatef(-this.spin_y_, 0.0F, 1.0F, 0.0F);
         }

         if (this.transparent_) {
            var1.glBlendFunc(1, 771);
            var1.glEnable(3042);
         }

         var1.glEnable(3553);

         assert this.texture_id_[0] != 0;

         var1.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         var1.glBindTexture(3553, this.texture_id_[0]);
         var1.glInterleavedArrays(10791, 0, var3);
         var1.glDrawArrays(7, 0, 4);
         var1.glDisable(3553);
         if (this.transparent_) {
            var1.glDisable(3042);
         }
      }
   }

   protected void onPopupSize(Rectangle var1) {
      if (var1.width > 0 && var1.height > 0) {
         this.original_popup_rect_ = var1;
         this.popup_rect_ = this.getPopupRectInWebView(this.original_popup_rect_);
      }
   }

   protected Rectangle getPopupRect() {
      return (Rectangle)this.popup_rect_.clone();
   }

   protected Rectangle getPopupRectInWebView(Rectangle var1) {
      if (var1.x < 0) {
         var1.x = 0;
      }

      if (var1.y < 0) {
         var1.y = 0;
      }

      if (var1.x + var1.width > this.view_width_) {
         var1.x = this.view_width_ - var1.width;
      }

      if (var1.y + var1.height > this.view_height_) {
         var1.y = this.view_height_ - var1.height;
      }

      if (var1.x < 0) {
         var1.x = 0;
      }

      if (var1.y < 0) {
         var1.y = 0;
      }

      return var1;
   }

   protected void clearPopupRects() {
      this.popup_rect_.setBounds(0, 0, 0, 0);
      this.original_popup_rect_.setBounds(0, 0, 0, 0);
   }

   protected void onPaint(GL2 var1, boolean var2, Rectangle[] var3, ByteBuffer var4, int var5, int var6) {
      this.initialize(var1);
      if (this.use_draw_pixels_) {
         var1.glRasterPos2f(-1.0F, 1.0F);
         var1.glPixelZoom(1.0F, -1.0F);
         var1.glDrawPixels(var5, var6, 32993, 5121, var4);
      } else {
         if (this.transparent_) {
            var1.glEnable(3042);
         }

         var1.glEnable(3553);

         assert this.texture_id_[0] != 0;

         var1.glBindTexture(3553, this.texture_id_[0]);
         if (!var2) {
            int var7 = this.view_width_;
            int var8 = this.view_height_;
            this.view_width_ = var5;
            this.view_height_ = var6;
            var1.glPixelStorei(3314, this.view_width_);
            if (var7 == this.view_width_ && var8 == this.view_height_) {
               for (int var9 = 0; var9 < var3.length; var9++) {
                  Rectangle var10 = var3[var9];
                  var1.glPixelStorei(3316, var10.x);
                  var1.glPixelStorei(3315, var10.y);
                  var1.glTexSubImage2D(3553, 0, var10.x, var10.y, var10.width, var10.height, 32993, 33639, var4);
               }
            } else {
               var1.glPixelStorei(3316, 0);
               var1.glPixelStorei(3315, 0);
               var1.glTexImage2D(3553, 0, 6408, this.view_width_, this.view_height_, 0, 32993, 33639, var4);
            }
         } else if (var2 && this.popup_rect_.width > 0 && this.popup_rect_.height > 0) {
            int var13 = 0;
            int var14 = this.popup_rect_.x;
            int var15 = 0;
            int var16 = this.popup_rect_.y;
            int var11 = var5;
            int var12 = var6;
            if (var14 < 0) {
               var13 = -var14;
               var14 = 0;
            }

            if (var16 < 0) {
               var15 = -var16;
               var16 = 0;
            }

            if (var14 + var5 > this.view_width_) {
               var11 = var5 - (var14 + var5 - this.view_width_);
            }

            if (var16 + var6 > this.view_height_) {
               var12 = var6 - (var16 + var6 - this.view_height_);
            }

            var1.glPixelStorei(3314, var5);
            var1.glPixelStorei(3316, var13);
            var1.glPixelStorei(3315, var15);
            var1.glTexSubImage2D(3553, 0, var14, var16, var11, var12, 32993, 33639, var4);
         }

         var1.glDisable(3553);
         if (this.transparent_) {
            var1.glDisable(3042);
         }
      }
   }

   protected void setSpin(float var1, float var2) {
      this.spin_x_ = var1;
      this.spin_y_ = var2;
   }

   protected void incrementSpin(float var1, float var2) {
      this.spin_x_ -= var1;
      this.spin_y_ -= var2;
   }
}
