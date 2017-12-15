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
package org.propertiescompare.main.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;
import org.propertiescompare.main.compare.PropertyFile;
import org.propertiescompare.main.compare.ProjectPropertyFiles;

import java.util.Optional;

public class SelectPropertiesAction extends AnAction {

  public SelectPropertiesAction() {
    super("PropertiesCompare");
  }

  public void actionPerformed(AnActionEvent event) {
    Project project = event.getData(PlatformDataKeys.PROJECT);
    ProjectPropertyFiles propertyFiles = new ProjectPropertyFiles(project);
    PropertyFile startFile = new PropertyFile(event.getData(CommonDataKeys.VIRTUAL_FILE));
    DialogWrapper demoDialog = new ChooseFilePopupDialog(project, propertyFiles, startFile);
    demoDialog.show();
  }

  @Override
  public void update(AnActionEvent e) {
    Optional<VirtualFile> file = Optional.ofNullable(e.getData(CommonDataKeys.VIRTUAL_FILE));
    file.ifPresent(f -> e.getPresentation().setEnabledAndVisible("properties".equals(f.getExtension())));
  }

}