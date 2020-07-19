package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils;

public class PlaceholderProcessor {

  public static final String process(String body) {
    return body.replaceAll("\\$\\{([0-9])\\}", "\\$VAR$1\\$")
        .replaceAll("\\$\\{[0-9]\\:([\\w\\s]+)\\}", "$1");
  }
}
