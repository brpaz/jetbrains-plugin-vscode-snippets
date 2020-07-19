package com.github.brpaz.jetbrains.plugin.vscodesnippets.extensions;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.SnippetsLoader;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.SnippetsRegistry;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.settings.AppSettingsState;
import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class LoadSnippetsActivity extends PreloadingActivity {
  private final Logger logger = Logger.getInstance(LoadSnippetsActivity.class);

  private final SnippetsLoader snippetsLoader = ServiceManager.getService(SnippetsLoader.class);

  private final SnippetsRegistry snippetsRegistry =
      ServiceManager.getService(SnippetsRegistry.class);

  private final AppSettingsState settings = ServiceManager.getService(AppSettingsState.class);

  public void preload(@NotNull ProgressIndicator indicator) {

    logger.info("Loading VS Code snippets");

    // TODO convert this to List
    String[] snippetsPaths = settings.snippetPaths.toArray(String[]::new);

    List<JetbrainsSnippet> snippetList = snippetsLoader.load(snippetsPaths);

    logger.info(
        String.format("Finished loading VS Code Snippets. Loaded %d snippets", snippetList.size()));
    snippetsRegistry.setSnippets(snippetList);
  }
}
