package com.github.brpaz.jetbrains.plugin.vscodesnippets.extensions;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.VsCodeLookupElement;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.CompletionResolver;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.SnippetsRegistry;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider.PackageProviderProcessorFactory;
import com.intellij.codeInsight.completion.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class VSCodeCompletionContributor extends CompletionContributor {

  private final Logger logger = Logger.getInstance(LoadSnippetsActivity.class);

  private final CompletionResolver completionResolver;

  public VSCodeCompletionContributor() {
    completionResolver =
        new CompletionResolver(
            ServiceManager.getService(SnippetsRegistry.class),
            new PackageProviderProcessorFactory());

    extend(
        CompletionType.BASIC,
        PlatformPatterns.psiElement(),
        new CompletionProvider<>() {
          @Override
          protected void addCompletions(
              @NotNull CompletionParameters parameters,
              @NotNull ProcessingContext context,
              @NotNull CompletionResultSet result) {

            logger.info("Resolving Completions");
            List<VsCodeLookupElement> completions = completionResolver.resolve(parameters, context);

            logger.info(String.format("Resolved %d Completions", completions.size()));

            result.addAllElements(completions);
          }
        });
  }
}
