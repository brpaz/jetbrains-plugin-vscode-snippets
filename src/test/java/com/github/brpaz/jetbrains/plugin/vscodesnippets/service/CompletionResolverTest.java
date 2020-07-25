package com.github.brpaz.jetbrains.plugin.vscodesnippets.service;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageContext;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.VsCodeLookupElement;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsLanguage;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider.PackageProviderProcessorFactory;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.lang.Language;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CompletionResolverTest {

  private SnippetsRegistry snippetsRegistry;

  private CompletionResolver completionResolver;

  @Mock
  private final PackageProviderProcessorFactory packageProviderProcessorFactory =
    new PackageProviderProcessorFactory();

  @Mock
  private CompletionParameters parameters;

  @Mock
  private ProcessingContext processingContext;

  @Mock
  private Application application;

  @Mock
  private PsiFile file;

  @Mock
  private VirtualFile vf;

  @Mock
  private Project project;

  @Before
  public void setup() {
    snippetsRegistry = new SnippetsRegistry();
    completionResolver = new CompletionResolver(snippetsRegistry, packageProviderProcessorFactory);

    when(application.isUnitTestMode()).thenReturn(true);
    ApplicationManager.setApplication(application, application);

    when(file.getVirtualFile()).thenReturn(vf);
    when(file.getProject()).thenReturn(project);
    when(project.getBasePath()).thenReturn("/tmp");
    when(parameters.getOriginalFile()).thenReturn(file);
    when(vf.getPath()).thenReturn("/tmp/test.js");

    Language language = Language.findLanguageByID("javascript");

    if (language == null) {
      language = new Language("javascript") {
        @Override
        public @NotNull String getID() {
          return "javascript";
        }
      };
    }

    when(file.getLanguage()).thenReturn(language);
  }

  @Test
  public void resolve_emptySnippetsList() {
    snippetsRegistry.setSnippets(new ArrayList<>());
    List<VsCodeLookupElement> result =
      this.completionResolver.resolve(parameters, processingContext);
    assertEquals(0, result.size());
  }

  @Test
  public void resolve_filterByLanguage() {

    JetbrainsSnippet snippet1 = buildSnippet(
      "snippet1",
      Collections.singleton(JetbrainsLanguage.JAVASCRIPT),
      new ArrayList<>(),
      null
    );

    JetbrainsSnippet snippet2 = buildSnippet(
      "snippet2",
      Collections.singleton(JetbrainsLanguage.GO),
      new ArrayList<>(),
      null
    );

    List<JetbrainsSnippet> snippets = List.of(snippet1, snippet2);

    snippetsRegistry.setSnippets(snippets);

    List<VsCodeLookupElement> result =
      this.completionResolver.resolve(parameters, processingContext);
    assertEquals(1, result.size());
    assertEquals("snippet1", result.get(0).getLookupString());
  }

  @Test
  public void resolve_filterByLanguageAndPattern() {
    JetbrainsSnippet snippet1 = buildSnippet(
      "snippet1",
      Collections.singleton(JetbrainsLanguage.JAVASCRIPT),
      Collections.singletonList("**/test.js"),
      null
    );

    JetbrainsSnippet snippet2 = buildSnippet(
      "snippet2",
      Collections.singleton(JetbrainsLanguage.JAVASCRIPT),
      Collections.singletonList("**/abc.js"),
      null
    );

    List<JetbrainsSnippet> snippets = List.of(snippet1, snippet2);
    snippetsRegistry.setSnippets(snippets)
    ;
    List<VsCodeLookupElement> result =
      this.completionResolver.resolve(parameters, processingContext);
    assertEquals(1, result.size());
    assertEquals("snippet1", result.get(0).getLookupString());
  }

  private JetbrainsSnippet buildSnippet(String label, Set<JetbrainsLanguage> languages, List<String> filePatterns, PackageContext packageContext) {
    return new JetbrainsSnippet(
      label,
      Collections.singletonList(label),
      "Snippet description",
      "snippet body",
      new JetbrainsSnippet.Context(languages, filePatterns, packageContext)
    );
  }
}
