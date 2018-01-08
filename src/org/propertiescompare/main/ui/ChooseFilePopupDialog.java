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

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.propertiescompare.main.compare.PropertyFile;
import org.propertiescompare.main.compare.ProjectPropertyFiles;
import org.propertiescompare.main.ui.actions.*;
import org.propertiescompare.main.ui.actions.diff.ShowAllEntriesAction;
import org.propertiescompare.main.ui.actions.diff.ShowDifferentEntriesAction;
import org.propertiescompare.main.ui.actions.diff.ShowEqualEntriesAction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChooseFilePopupDialog extends DialogWrapper {

  private PropertyFile startFile;
  private List<PropertyFile> files;
  private JBList<PropertyFile> jbFileList;
  private Project project;

  public ChooseFilePopupDialog(@Nullable Project project, ProjectPropertyFiles files, PropertyFile startFile) {
    super(project);
    this.project = project;
    this.files = files.getFiles();
    this.startFile = startFile;
    super.setTitle("Select file to compare with");
    super.init();
  }

  @NotNull
  @Override
  protected Action[] createActions() {
    List<Action> actions = new ArrayList<>();
    FileActionContext actionContext = new FileActionContext(project, startFile, jbFileList);
    actions.add(new ShowDifferentEntriesAction(actionContext));
    actions.add(new ShowEqualEntriesAction(actionContext));
    actions.add(new ShowAllEntriesAction(actionContext));
    actions.add(new ChooseFileManuallyAction(actionContext));
    return actions.toArray(new Action[actions.size()]);
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    final JPanel panel = new JPanel();
    final CollectionListModel<PropertyFile> filesList = new CollectionListModel<>(files);
    panel.setLayout(new BorderLayout());

    jbFileList = new JBList<>(filesList);
    jbFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jbFileList.setCellRenderer(new ColoredListCellRenderer<PropertyFile>() {
      @Override
      protected void customizeCellRenderer(@NotNull JList jList, PropertyFile file, int i, boolean b, boolean b1) {
        append(file.getFullPath());
      }
    });
    return new PropertyListToolbarDecorator(jbFileList).createPanel();
  }
}
