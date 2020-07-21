package com.github.brpaz.jetbrains.plugin.vscodesnippets.completion;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.codeInsight.template.impl.LiveTemplateLookupElementImpl;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.codeInsight.template.impl.TemplateImplUtil;
import com.intellij.codeInsight.template.impl.Variable;
import com.intellij.util.containers.hash.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VsCodeLookupElement extends LiveTemplateLookupElementImpl {
  private final JetbrainsSnippet snippet;

  public VsCodeLookupElement(JetbrainsSnippet snippet) {
    super(buildTemplateFromSnippet(snippet), true);
    this.snippet = snippet;
  }

  private static TemplateImpl buildTemplateFromSnippet(JetbrainsSnippet snippet) {
    TemplateImpl tpl = new TemplateImpl(snippet.getLabel(), "");
    tpl.setString(snippet.getBody());
    tpl.setDescription(snippet.getDescription());

    LinkedHashMap<String, Variable> variables = TemplateImplUtil.parseVariables(snippet.getBody());

    for (Variable variable : variables.values()) {
      tpl.addVariable(
          variable.getName(),
          variable.getExpressionString(),
          variable.getDefaultValueString(),
          variable.isAlwaysStopAt());
    }

    tpl.parseSegments();

    return tpl;
  }

  @Override
  public AutoCompletionPolicy getAutoCompletionPolicy() {
    return AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE;
  }

  @Override
  public Set<String> getAllLookupStrings() {
    List<String> lookupStrings = new ArrayList<>();
    lookupStrings.add(snippet.getLabel());
    lookupStrings.addAll(snippet.getPrefix());

    return new HashSet<>(lookupStrings);
  }
}
