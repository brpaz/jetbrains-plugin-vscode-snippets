package com.github.brpaz.jetbrains.plugin.vscodesnippets.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Supports storing the application settings in a persistent way. The State and Storage annotations
 * define the name of the data and the file name where these persistent application settings are
 * stored.
 */
@State(
    name = "dev.brunopaz.jetbrainsvscode.settings",
    storages = {@Storage("VsCodeSnippetsPlugin.xml")})
public class AppSettingsState implements PersistentStateComponent<AppSettingsState> {

  public List<String> snippetPaths = new ArrayList<>();

  public static AppSettingsState getInstance() {
    return ServiceManager.getService(AppSettingsState.class);
  }

  @Nullable
  @Override
  public AppSettingsState getState() {
    return this;
  }

  @Override
  public void loadState(@NotNull AppSettingsState state) {
    XmlSerializerUtil.copyBean(state, this);
  }
}
