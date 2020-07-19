package com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageContext;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.serializer.PrefixSerializer;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.serializer.ScopeSerializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class VSCodeSnippet {

  private String label;

  @JsonAdapter(PrefixSerializer.class)
  private List<String> prefix;

  private List<String> body;

  @JsonAdapter(ScopeSerializer.class)
  private List<VSCodeLanguage> scope;

  private String description;

  private Context context;

  public static class Context {
    private List<String> patterns = new ArrayList<>();

    @SerializedName("package")
    private PackageContext pkg;

    public List<String> getPatterns() {
      return patterns;
    }

    public PackageContext getPackage() {
      return pkg;
    }
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<String> getPrefix() {
    return prefix;
  }

  public List<String> getBody() {
    return body;
  }

  public List<VSCodeLanguage> getScope() {
    return scope;
  }

  public String getDescription() {
    return description;
  }

  public Context getContext() {
    return context;
  }
}
