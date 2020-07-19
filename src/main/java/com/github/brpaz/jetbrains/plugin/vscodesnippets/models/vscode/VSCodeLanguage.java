package com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode;

public enum VSCodeLanguage {
  ABAP("abap"),
  WINDOWS_BAT("bat"),
  CLOJURE("clojure"),
  COFEESCRIPT("coffeescript"),
  C("c"),
  CPP("cpp"),
  CSHARP("csharp"),
  CSS("css"),
  DIFF("diff"),
  DOCKERFILE("dockerfile"),
  FSHARP("fsharp"),
  GO("go"),
  GROOVY("groovy"),
  HANDLEBARS("handlebars"),
  HTML("html"),
  INI("ini"),
  JAVA("java"),
  JAVASCRIPT("javascript"),
  JAVASCRIPT_REACT("javascriptreact"),
  JSON("json"),
  LATEX("latex"),
  LESS("less"),
  LUA("lua"),
  MAKEFILE("makefile"),
  MARKDOWN("markdown"),
  OBJECTIVEC("objective-c"),
  OBJECTIVECPP("objective-cpp"),
  PHP("php"),
  PLAINTEXT("plaintext"),
  POWERSHELL("powershell"),
  PUG("jade"),
  PYTHON("python"),
  R("r"),
  RUBY("ruby"),
  SCSS("scss"),
  SASS("sass"),
  SHELLSCRIPT("shellscript"),
  SQL("sql"),
  SWIFT("swift"),
  TYPESCRIPT("typescript"),
  TYPESCRIPT_REACT("typescriptreact"),
  TEX("tex"),
  XML("xml"),
  XSL("xsl"),
  YAML("yaml"),
  VUE("vue"),
  GHERKIN("text.gherkin.feature");

  public final String label;

  private VSCodeLanguage(String label) {
    this.label = label;
  }

  public static VSCodeLanguage fromLabel(String label) {
    for (VSCodeLanguage value : VSCodeLanguage.values()) {
      if (value.label.equals(label)) {
        return value;
      }
    }

    throw new IllegalArgumentException("Cannot find Language with label:" + label);
  }
}
