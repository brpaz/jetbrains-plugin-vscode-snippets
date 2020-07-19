package com.github.brpaz.jetbrains.plugin.vscodesnippets.settings;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.SnippetsLoader;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.settings.ui.AppSettingsComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import javax.swing.*;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

/** Provides controller functionality for application settings. */
public class AppSettingsConfigurable implements Configurable {
  private AppSettingsComponent mySettingsComponent;

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "VS Code Snippets";
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return mySettingsComponent.getPreferredFocusedComponent();
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new AppSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    AppSettingsState settings = AppSettingsState.getInstance();

    return mySettingsComponent.getSnippetsPaths() != settings.snippetPaths;
  }

  @Override
  public void apply() throws ConfigurationException {
    AppSettingsState settings = AppSettingsState.getInstance();
    settings.snippetPaths = mySettingsComponent.getSnippetsPaths();

    reloadSnippets();
  }

  @Override
  public void reset() {
    AppSettingsState settings = AppSettingsState.getInstance();

    mySettingsComponent.setSnippetsPaths(settings.snippetPaths);
  }

  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

  private void reloadSnippets() {
    AppSettingsState settings = AppSettingsState.getInstance();

    SnippetsLoader snippetsLoader = ServiceManager.getService(SnippetsLoader.class);
    snippetsLoader.load(settings.snippetPaths.toArray(String[]::new));
  }
}
