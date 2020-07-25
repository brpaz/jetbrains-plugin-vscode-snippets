package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils;

import static java.util.Map.entry;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsLanguage;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeLanguage;
import java.util.Map;

public class LanguagesMapper {

  private static final Map<VSCodeLanguage, JetbrainsLanguage> LANGUAGE_MAP =
      Map.ofEntries(
          entry(VSCodeLanguage.YAML, JetbrainsLanguage.YAML),
          entry(VSCodeLanguage.TYPESCRIPT, JetbrainsLanguage.TYPESCRIPT),
          entry(VSCodeLanguage.TYPESCRIPT_REACT, JetbrainsLanguage.TYPESCRIPT_JSX),
          entry(VSCodeLanguage.JAVASCRIPT, JetbrainsLanguage.JAVASCRIPT),
          entry(VSCodeLanguage.DOCKERFILE, JetbrainsLanguage.DOCKERFILE),
          entry(VSCodeLanguage.COFEESCRIPT, JetbrainsLanguage.COFFEESCRIPT),
          entry(VSCodeLanguage.CSS, JetbrainsLanguage.CSS),
          entry(VSCodeLanguage.SASS, JetbrainsLanguage.SASS),
          entry(VSCodeLanguage.SCSS, JetbrainsLanguage.SCSS),
          entry(VSCodeLanguage.PHP, JetbrainsLanguage.PHP),
          entry(VSCodeLanguage.PYTHON, JetbrainsLanguage.PYTHON),
          entry(VSCodeLanguage.GO, JetbrainsLanguage.GO),
          entry(VSCodeLanguage.HANDLEBARS, JetbrainsLanguage.HANDLEBARS),
          entry(VSCodeLanguage.HTML, JetbrainsLanguage.HTML));

  public static JetbrainsLanguage toJetbrains(VSCodeLanguage language) {
    return LANGUAGE_MAP.getOrDefault(language, null);
  }
}
