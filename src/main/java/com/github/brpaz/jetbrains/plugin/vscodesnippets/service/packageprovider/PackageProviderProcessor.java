package com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageProvider;
import com.intellij.psi.PsiFile;

public interface PackageProviderProcessor {

  PackageProvider getProvider();

  Boolean process(String pkgName, PsiFile file);
}
