package com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageProvider;

public class PackageProviderProcessorFactory {

  public PackageProviderProcessor createFromProvider(PackageProvider provider) {
    switch (provider) {
      case NPM:
        return new NpmProcessor();
      default:
        throw new IllegalArgumentException("Invalid package provider " + provider);
    }
  }
}
