package com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class NpmProcessor implements PackageProviderProcessor {

  private final Gson gson;

  private final Logger logger = Logger.getInstance(PackageProviderProcessor.class);

  public NpmProcessor() {
    this.gson = new Gson();
  }

  private Boolean hasDependency(String name, String filePath) {
    JsonObject json = null;
    try {
      json = this.gson.fromJson(new FileReader(filePath, StandardCharsets.UTF_8), JsonObject.class);
    } catch (Exception e) {
      logger.warn(e);
    }

    if (json.has("dependencies") && json.getAsJsonObject("dependencies").get(name) != null) {
      return true;
    }

    if (json.has("devDependencies") && json.getAsJsonObject("devDependencies").get(name) != null) {
      return true;
    }

    return false;
  }

  @Override
  public PackageProvider getProvider() {
    return PackageProvider.NPM;
  }

  @Override
  public Boolean process(String pkgName, PsiFile file) {
    final String projectRoot = file.getProject().getBasePath();
    PsiDirectory dir = file.getContainingDirectory();
    while (dir != null) {
      PsiFile pkgFile = dir.findFile("package.json");
      if (pkgFile != null) {
        return hasDependency(pkgName, pkgFile.getVirtualFile().getPath());
      }

      if (dir.getVirtualFile().getPath().equals(projectRoot)) {
        dir = null;
      } else {
        dir = dir.getParent();
      }
    }

    return false;
  }
}
