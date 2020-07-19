package com.github.brpaz.jetbrains.plugin.vscodesnippets.extensions;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.completion.VSCodeCompletionProvider;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.SnippetsRegistry;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider.PackageProviderProcessorFactory;
import com.intellij.codeInsight.completion.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.patterns.PlatformPatterns;

public class VSCodeCompletionContributor extends CompletionContributor {

  public VSCodeCompletionContributor() {
    extend(
        CompletionType.BASIC,
        PlatformPatterns.psiElement(),
        new VSCodeCompletionProvider(
            ServiceManager.getService(SnippetsRegistry.class),
            new PackageProviderProcessorFactory()));
  }
}
