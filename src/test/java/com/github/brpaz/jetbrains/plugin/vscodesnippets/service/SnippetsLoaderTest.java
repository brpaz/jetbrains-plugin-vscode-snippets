package com.github.brpaz.jetbrains.plugin.vscodesnippets.service;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SnippetsLoaderTest {

  private final SnippetsLoader loader = new SnippetsLoader();

  @Before
  public void setUp() throws Exception {}

  @Test
  public void load_returnsSnippetsList() throws IOException {
    Path snippetsFolder = Paths.get("src", "test", "resources", "snippets");

    String[] paths = {snippetsFolder.toFile().getAbsolutePath()};

    List<JetbrainsSnippet> snippets = loader.load(paths);
    Assert.assertEquals(10, snippets.size());
  }

  @Test
  public void load_invalidJsonFIle_returnsEmptyList() throws IOException {
    Path snippetsFolder = Paths.get("src", "test", "resources", "invalidjson");

    String[] paths = {snippetsFolder.toFile().getAbsolutePath()};

    List<JetbrainsSnippet> snippets = loader.load(paths);
    Assert.assertEquals(0, snippets.size());
  }

  @Test
  public void load_validAndInvalid_returnsSnippetsList() {
    Path validSnippetsFolder = Paths.get("src", "test", "resources", "snippets");
    Path invalidSnippetsFolder = Paths.get("src", "test", "resources", "invalidjson");
    Path noExistingSnippetsFolder = Paths.get("src", "test", "resources", "snippetfdfdfds");

    String[] paths = {
      validSnippetsFolder.toFile().getAbsolutePath(),
      invalidSnippetsFolder.toFile().getAbsolutePath(),
      noExistingSnippetsFolder.toFile().getAbsolutePath()
    };

    List<JetbrainsSnippet> snippets = loader.load(paths);
    Assert.assertEquals(10, snippets.size());
  }
}
