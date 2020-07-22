package com.github.brpaz.jetbrains.plugin.vscodesnippets.models;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.serializer.PackageProviderSerializer;
import com.google.gson.annotations.JsonAdapter;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PackageContext that = (PackageContext) o;
    return provider == that.provider && name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(provider, name);
  }
}
