package com.github.brpaz.jetbrains.plugin.vscodesnippets.service;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.intellij.openapi.components.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public final class SnippetsRegistry {

  private List<JetbrainsSnippet> snippets = new ArrayList<>();

  public void setSnippets(List<JetbrainsSnippet> snippets) {
    this.snippets = snippets;
  }

  public List<JetbrainsSnippet> getSnippets() {
    return this.snippets;
  }
}
