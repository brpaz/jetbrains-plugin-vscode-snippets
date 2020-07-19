package com.github.brpaz.jetbrains.plugin.vscodesnippets.settings.ui;

import com.intellij.util.ui.FormBuilder;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class AppSettingsComponent {
  private final JPanel myPanel;
  private final MyAddDeleteListPanel myAddDeleteListPanel;

  public AppSettingsComponent() {
    myAddDeleteListPanel = new MyAddDeleteListPanel("Snippet Paths");
    myPanel = FormBuilder.createFormBuilder().addComponent(myAddDeleteListPanel).getPanel();
  }

  public JPanel getPanel() {
    return myAddDeleteListPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return myAddDeleteListPanel;
  }

  public List<String> getSnippetsPaths() {
    return Arrays.asList(myAddDeleteListPanel.getItems());
  }

  public void setSnippetsPaths(List<String> paths) {
    myAddDeleteListPanel.removeALlItems();
    for (String path : paths) {
      myAddDeleteListPanel.addItem(path);
    }
  }
}
