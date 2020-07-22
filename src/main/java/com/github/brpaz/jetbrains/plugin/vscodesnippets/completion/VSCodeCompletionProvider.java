package com.github.brpaz.jetbrains.plugin.vscodesnippets.completion;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageContext;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.SnippetsRegistry;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider.PackageProviderProcessor;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider.PackageProviderProcessorFactory;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

public class VSCodeCompletionProvider extends CompletionProvider<CompletionParameters> {

  private final SnippetsRegistry snippetsRegistry;

  private final PackageProviderProcessorFactory packageProviderProcessorFactory;
  private final Logger logger = Logger.getInstance(VSCodeCompletionProvider.class);

  LoadingCache<Pair<PackageContext, PsiFile>, Boolean> memoizedFilterByPackageFn =
      CacheBuilder.newBuilder()
          .expireAfterAccess(30, TimeUnit.SECONDS)
          .build(CacheLoader.from(this::processFilterByPackage));

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
    return snippet.getContext().getLanguages().stream()
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

    Pair<PackageContext, PsiFile> pair = new Pair<>(item.getContext().getPackageProvider(), file);

    return memoizedFilterByPackageFn.getUnchecked(pair);
  }

  private Boolean processFilterByPackage(Pair<PackageContext, PsiFile> args) {
    PackageProviderProcessor processor =
        packageProviderProcessorFactory.createFromProvider(args.getFirst().getProvider());

    return processor.process(args.getFirst().getName(), args.getSecond());
  }
}
