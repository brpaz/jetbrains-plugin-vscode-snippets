package com.github.brpaz.jetbrains.plugin.vscodesnippets.settings.ui;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.AddDeleteListPanel;
import java.util.Collections;

public class MyAddDeleteListPanel extends AddDeleteListPanel<String> {

  public MyAddDeleteListPanel(final String title) {
    super(title, Collections.<String>emptyList());
  }

  public void addItem(String item) {
    myListModel.addElement(item);
  }

  public void removeALlItems() {
    myListModel.removeAllElements();
  }

  public String[] getItems() {
    final Object[] itemList = getListItems();
    final String[] result = new String[itemList.length];
    for (int i = 0; i < itemList.length; i++) {
      result[i] = itemList[i].toString();
    }
    return result;
  }

  @Override
  protected String findItemToAdd() {

    final FileChooserDescriptor fileChooserDescriptor =
        FileChooserDescriptorFactory.createSingleFolderDescriptor()
            .withTitle("Select Folder containing your VS Code Snippets")
            .withShowHiddenFiles(true);

    VirtualFile[] paths = FileChooser.chooseFiles(fileChooserDescriptor, null, null);

    if (paths.length == 0) {
      return null;
    }

    return paths[0].getPath();
  }
}
