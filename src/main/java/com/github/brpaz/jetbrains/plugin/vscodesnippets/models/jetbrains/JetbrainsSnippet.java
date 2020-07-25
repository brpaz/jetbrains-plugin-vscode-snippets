package com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageContext;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeLanguage;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeSnippet;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.LanguagesMapper;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.PlaceholderProcessor;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;

public class JetbrainsSnippet {

  private final String label;

  private final String description;

  private final String body;

  private final List<String> prefix;

  private final Context context;

  public JetbrainsSnippet(
      String label, List<String> prefix, String description, String body, Context context) {
    this.label = label;
    this.description = description;
    this.body = body;
    this.prefix = prefix;
    this.context = context;
  }

  public String getLabel() {
    return label;
  }

  public Context getContext() {
    return context;
  }

  public String getDescription() {
    return description;
  }

  public String getBody() {
    return body;
  }

  public List<String> getPrefix() {
    return prefix;
  }

  public static class Context {
    private final Set<JetbrainsLanguage> languages;

    private final List<String> filePatterns;

    private final PackageContext packageProvider;

    public Context(
        Set<JetbrainsLanguage> languages,
        List<String> filePatterns,
        PackageContext packageProvider) {
      this.languages = languages;
      this.filePatterns = filePatterns;
      this.packageProvider = packageProvider;
    }

    public Set<JetbrainsLanguage> getLanguages() {
      return languages;
    }

    public List<String> getFilePatterns() {
      return filePatterns;
    }

    public PackageContext getPackageProvider() {
      return packageProvider;
    }
  }

  public static JetbrainsSnippet fromVSCodeSnippet(VSCodeSnippet codeSnippet) {

    String body = StringUtils.join(codeSnippet.getBody(), "\n");
    Set<VSCodeLanguage> languageScope = codeSnippet.getScope();

    Set<JetbrainsLanguage> languages =
        languageScope.stream()
            .map(LanguagesMapper::toJetbrains)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

    Context context =
        new Context(
            languages,
            codeSnippet.getContext() != null ? codeSnippet.getContext().getPatterns() : null,
            codeSnippet.getContext() != null ? codeSnippet.getContext().getPackage() : null);

    return new JetbrainsSnippet(
        codeSnippet.getLabel(),
        codeSnippet.getPrefix(),
        codeSnippet.getDescription(),
        PlaceholderProcessor.process(body),
        context);
  }
}
