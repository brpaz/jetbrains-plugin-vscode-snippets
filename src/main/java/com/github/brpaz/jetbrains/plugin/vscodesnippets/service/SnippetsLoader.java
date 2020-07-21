package com.github.brpaz.jetbrains.plugin.vscodesnippets.service;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeLanguage;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeSnippet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public final class SnippetsLoader {

  private static final String SNIPPETS_FILES_GLOB_PATTERN = "*.json";

  private static final Logger logger = Logger.getInstance(SnippetsLoader.class);

  private final Gson gson;

  public SnippetsLoader() {
    this.gson = new Gson();
  }

  public List<JetbrainsSnippet> load(String[] paths) {
    return Arrays.stream(paths)
        .map(this::readSnippetsDirectory)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  private List<JetbrainsSnippet> readSnippetsDirectory(String filePath) {
    List<JetbrainsSnippet> snippets = new ArrayList<>();
    try (DirectoryStream<Path> dirStream =
        Files.newDirectoryStream(Paths.get(filePath), SNIPPETS_FILES_GLOB_PATTERN)) {
      dirStream.forEach(path -> snippets.addAll(readSnippetFile(path)));

    } catch (Exception e) {
      logger.warn("Cannot read snippet Directory with path " + filePath, e);
    }

    return snippets;
  }

  private List<JetbrainsSnippet> readSnippetFile(Path path) {
    List<JetbrainsSnippet> snippetsList = new ArrayList<>();
    File f = path.toFile();
    VSCodeLanguage fileScope = getScopeFromFile(f);
    try {
      Map<String, VSCodeSnippet> snippets =
          gson.fromJson(
              new FileReader(f, StandardCharsets.UTF_8),
              new TypeToken<Map<String, VSCodeSnippet>>() {}.getType());
      for (Map.Entry<String, VSCodeSnippet> entry : snippets.entrySet()) {
        VSCodeSnippet s = entry.getValue();
        s.setLabel(entry.getKey());

        if (s.getScope().size() == 0 && fileScope != null) {
          List<VSCodeLanguage> scope = new ArrayList<>();
          scope.add(fileScope);
          s.setScope(scope);
        }

        if (s.isValid()) {
          snippetsList.add(JetbrainsSnippet.fromVSCodeSnippet(entry.getValue()));
        }

      }
    } catch (Exception e) {
      logger.warn("Cannot parse snippet file", e);
    }

    return snippetsList;
  }

  private VSCodeLanguage getScopeFromFile(File f) {
    String fileName = FilenameUtils.getBaseName(f.getName());

    try {
      return VSCodeLanguage.fromLabel(fileName);
    } catch (IllegalArgumentException e) {
        return null;
    }
  }
}
