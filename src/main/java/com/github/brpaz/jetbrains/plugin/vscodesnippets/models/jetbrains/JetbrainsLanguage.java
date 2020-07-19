package com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains;

public enum JetbrainsLanguage {
  NONE("none"),
  TEXT("TEXT"),
  MARKDOWN("Markdown"),
  EDITORCONFIG("Editorconfig"),
  JAVA("java"),
  KOTLIN("kotlin"),
  GROOVY("Groovy"),
  HTML("HTML"),
  XML("XML"),
  JSON("JSON"),
  PHP("PHP"),
  YAML("yaml"),
  LESS("LESS"),
  CSS("CSS"),
  COFFEESCRIPT("CoffeeScript"),
  VUE("Vue"),
  HANDLEBARS("Handlebars"),
  SASS("SASS"),
  DOCKERFILE("Dockerfile"),
  ANGULARJS("AngularJS"),
  ANGULAR("Angular2"),
  HAML("HAML"),
  SCSS("SCSS"),
  TYPESCRIPT("Typescript"),
  TYPESCRIPT_JSX("TypeScript JSX"),
  JAVASCRIPT("Javascript"),
  SQL("SQL"),
  GHERKIN("Gherkin"),
  BLADE("Blade"),
  TWIG("Twig"),
  PYTHON("Python"),
  GO("go");

  public final String label;

  private JetbrainsLanguage(String label) {
    this.label = label;
  }
}
