package com.github.brpaz.jetbrains.plugin.vscodesnippets.service;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class SnippetsRegistryTest {

  private SnippetsRegistry registry;

  private List<JetbrainsSnippet> getMockSnippets() {
    List<JetbrainsSnippet> snippets = new ArrayList<>();
    snippets.add(
        new JetbrainsSnippet(
            "teste",
            new ArrayList<>(),
            "test",
            "test",
            new JetbrainsSnippet.Context(null, null, null)));

    return snippets;
  }

  @Test
  public void registry_SetsAndGetsSnippets() {
    registry = new SnippetsRegistry();
    registry.setSnippets(getMockSnippets());

    Assert.assertEquals(1, registry.getSnippets().size());
  }
}
