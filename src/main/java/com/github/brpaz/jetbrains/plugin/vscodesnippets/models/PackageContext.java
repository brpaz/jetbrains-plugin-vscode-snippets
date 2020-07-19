package com.github.brpaz.jetbrains.plugin.vscodesnippets.models;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.serializer.PackageProviderSerializer;
import com.google.gson.annotations.JsonAdapter;

public class PackageContext {

  @JsonAdapter(PackageProviderSerializer.class)
  private final PackageProvider provider;

  private final String name;

  public PackageContext(PackageProvider provider, String name) {
    this.provider = provider;
    this.name = name;
  }

  public PackageProvider getProvider() {
    return provider;
  }

  public String getName() {
    return name;
  }
}
