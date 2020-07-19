package com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageProvider;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsSnippet;
import com.intellij.psi.PsiFile;

public interface PackageProviderProcessor {

  PackageProvider getProvider();

  Boolean process(JetbrainsSnippet item, PsiFile file);
}
