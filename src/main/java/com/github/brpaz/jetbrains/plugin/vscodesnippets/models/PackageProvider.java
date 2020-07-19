package com.github.brpaz.jetbrains.plugin.vscodesnippets.models;

public enum PackageProvider {
  NPM("npm");

  public final String label;

  private PackageProvider(String label) {
    this.label = label;
  }

  public static PackageProvider fromLabel(String label) {
    for (PackageProvider value : PackageProvider.values()) {
      if (value.label.equals(label)) {
        return value;
      }
    }
    throw new IllegalArgumentException("Cannot find Language with label:" + label);
  }
}
