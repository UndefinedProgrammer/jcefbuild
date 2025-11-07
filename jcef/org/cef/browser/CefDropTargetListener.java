package org.cef.browser;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;
import org.cef.callback.CefDragData;

class CefDropTargetListener implements DropTargetListener {
   private CefBrowser_N browser_;
   private CefDragData dragData_ = null;
   private int dragOperations_ = 1;
   private int dragModifiers_ = 0;
   private int acceptOperations_ = 1;

   CefDropTargetListener(CefBrowser_N var1) {
      this.browser_ = var1;
   }

   @Override
   public void dragEnter(DropTargetDragEvent var1) {
      this.CreateDragData(var1);
      this.browser_.dragTargetDragEnter(this.dragData_, var1.getLocation(), this.dragModifiers_, this.dragOperations_);
   }

   @Override
   public void dragExit(DropTargetEvent var1) {
      this.AssertDragData();
      this.browser_.dragTargetDragLeave();
      this.ClearDragData();
   }

   @Override
   public void dragOver(DropTargetDragEvent var1) {
      this.AssertDragData();
      this.browser_.dragTargetDragOver(var1.getLocation(), this.dragModifiers_, this.dragOperations_);
   }

   @Override
   public void dropActionChanged(DropTargetDragEvent var1) {
      this.AssertDragData();
      this.acceptOperations_ = var1.getDropAction();
      switch (this.acceptOperations_) {
         case 0:
            this.dragOperations_ = 1;
            this.dragModifiers_ = 0;
            this.acceptOperations_ = 1;
            break;
         case 1:
            this.dragOperations_ = 1;
            this.dragModifiers_ = 4;
            break;
         case 2:
            this.dragOperations_ = 16;
            this.dragModifiers_ = 2;
            break;
         case 1073741824:
            this.dragOperations_ = 2;
            this.dragModifiers_ = 6;
      }
   }

   @Override
   public void drop(DropTargetDropEvent var1) {
      this.AssertDragData();
      this.browser_.dragTargetDrop(var1.getLocation(), this.dragModifiers_);
      var1.acceptDrop(this.acceptOperations_);
      var1.dropComplete(true);
      this.ClearDragData();
   }

   private void CreateDragData(DropTargetDragEvent var1) {
      assert this.dragData_ == null;

      this.dragData_ = createDragData(var1);
      this.dropActionChanged(var1);
   }

   private void AssertDragData() {
      assert this.dragData_ != null;
   }

   private void ClearDragData() {
      this.dragData_ = null;
   }

   private static CefDragData createDragData(DropTargetDragEvent var0) {
      CefDragData var1 = CefDragData.create();
      Transferable var2 = var0.getTransferable();
      DataFlavor[] var3 = var2.getTransferDataFlavors();

      for (DataFlavor var7 : var3) {
         try {
            if (var7.isFlavorTextType()) {
               Object var8 = var2.getTransferData(var7);
               if (var8 instanceof String) {
                  var1.setFragmentText((String)var8);
               }
            } else if (var7.isFlavorJavaFileListType()) {
               for (File var10 : (List)var2.getTransferData(var7)) {
                  var1.addFile(var10.getPath(), var10.getName());
               }
            }
         } catch (Exception var11) {
            var11.printStackTrace();
         }
      }

      return var1;
   }
}
