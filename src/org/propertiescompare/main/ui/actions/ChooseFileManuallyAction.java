/*
 * Copyright (c) 2017 Stanislav Myachenkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.propertiescompare.main.ui.actions;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.CollectionListModel;
import org.propertiescompare.main.compare.PropertyFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ChooseFileManuallyAction extends AbstractAction {

  public static final String ACTION_LABEL = "Another file...";

  private final FileActionContext context;

  public ChooseFileManuallyAction(FileActionContext context) {
    super(ACTION_LABEL);
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    final FileChooserDescriptor fileChooserDescriptor =
        FileChooserDescriptorFactory.createSingleFileDescriptor("properties");

    final VirtualFile[] selectedFile = FileChooser.chooseFiles(fileChooserDescriptor, context.getProject(), null);

    if (selectedFile.length > 0) {
      VirtualFile newFile = selectedFile[0];
      PropertyFile newPropertiesFile = new PropertyFile(newFile);
      List<PropertyFile> propertyFiles = context.getFilesAsList();

      if (propertyFiles.stream().anyMatch(file -> file.getVirtualFile().equals(newFile))) {
        Messages.showInfoMessage(context.getProject(), "File is already in list", "Info");
      } else {
        CollectionListModel model = (CollectionListModel) context.getFiles().getModel();
        model.add(newPropertiesFile);
      }
    }
  }
}
