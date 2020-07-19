package com.github.brpaz.jetbrains.plugin.vscodesnippets.completion;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.SnippetsRegistry;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider.PackageProviderProcessor;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider.PackageProviderProcessorFactory;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class VSCodeCompletionProvider extends CompletionProvider<CompletionParameters> {

  private final SnippetsRegistry snippetsRegistry;

  private final PackageProviderProcessorFactory packageProviderProcessorFactory;
  private final Logger logger = Logger.getInstance(VSCodeCompletionProvider.class);

  public VSCodeCompletionProvider(
      final SnippetsRegistry snippetsRegistry,
      final PackageProviderProcessorFactory packageProviderProcessorFactory) {
    super();
    this.snippetsRegistry = snippetsRegistry;
    this.packageProviderProcessorFactory = packageProviderProcessorFactory;
  }

  @Override
  protected void addCompletions(
      @NotNull CompletionParameters parameters,
      @NotNull ProcessingContext context,
      @NotNull CompletionResultSet result) {

    logger.info("Triggered VS Code Completions");

    this.snippetsRegistry.getSnippets().stream()
        .filter(item -> filterByLanguage(item, parameters.getOriginalFile().getLanguage()))
        .filter(item -> filterByPattern(item, parameters.getOriginalFile()))
        .filter(item -> filterByPackage(item, parameters.getOriginalFile()))
        .map(VsCodeLookupElement::new)
        .forEach(result::addElement);
  }

  private boolean filterByLanguage(JetbrainsSnippet snippet, Language lang) {
    return Arrays.stream(snippet.getContext().getLanguages())
        .anyMatch(item -> item.label.equalsIgnoreCase(lang.getID()));
  }

  private boolean filterByPattern(JetbrainsSnippet snippet, PsiFile file) {

    if (snippet.getContext() == null || snippet.getContext().getFilePatterns().size() == 0) {
      return true;
    }

    final String projectPath = file.getProject().getBasePath();

    String filePath = file.getVirtualFile().getPath().replace(projectPath, "");

    return snippet.getContext().getFilePatterns().stream()
        .anyMatch(
            glob ->
                FileSystems.getDefault().getPathMatcher("glob:" + glob).matches(Path.of(filePath)));
  }

  private boolean filterByPackage(JetbrainsSnippet item, PsiFile file) {

    if (item.getContext() == null || item.getContext().getPackageProvider() == null) {
      return true;
    }

    return processFilterByPackage(item, file);
  }

  private Boolean processFilterByPackage(JetbrainsSnippet item, PsiFile file) {
    PackageProviderProcessor processor =
        packageProviderProcessorFactory.createFromProvider(
            item.getContext().getPackageProvider().getProvider());

    return processor.process(item, file);
  }
}
