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
package org.propertiescompare.main.ui.actions.diff;

import com.intellij.diff.DiffContentFactory;
import com.intellij.diff.DiffManager;
import com.intellij.diff.contents.DiffContent;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.openapi.util.Pair;
import org.propertiescompare.main.compare.PropertyFile;
import org.propertiescompare.main.compare.result.CompareResult;
import org.propertiescompare.main.ui.actions.FileActionContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public abstract class AbstractDiffAction extends AbstractAction {

  protected FileActionContext context;

  public AbstractDiffAction(String actionLabel, FileActionContext context) {
    super(actionLabel);
    this.context = context;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    Pair<PropertyFile, PropertyFile> files = context.getComparedFiles();
    Optional.ofNullable(files.getSecond()).ifPresent(s -> {
      CompareResult result = createCompareResult(files);
      DiffContent left = DiffContentFactory.getInstance().create(result.getLeftSide());
      DiffContent right = DiffContentFactory.getInstance().create(result.getRightSide());
      SimpleDiffRequest request = new SimpleDiffRequest("Result", left, right,
          files.getFirst().getFullPath(), files.getSecond().getFullPath());
      DiffManager.getInstance().showDiff(context.getProject(), request);
    });
  }

  protected abstract CompareResult createCompareResult(Pair<PropertyFile, PropertyFile> files);

}
