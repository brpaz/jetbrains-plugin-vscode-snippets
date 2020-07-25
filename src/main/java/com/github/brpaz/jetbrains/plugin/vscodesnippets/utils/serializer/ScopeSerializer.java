package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.serializer;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeLanguage;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class ScopeSerializer
    implements JsonSerializer<String>, JsonDeserializer<Set<VSCodeLanguage>> {

  @Override
  public Set<VSCodeLanguage> deserialize(
      JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    Set<VSCodeLanguage> languagesList = new HashSet<>();

    String[] languages = jsonElement.getAsString().split(",");

    if (languages.length == 0) {
      return languagesList;
    }

    for (String language : languages) {
      try {
        languagesList.add(VSCodeLanguage.fromLabel(language));
      } catch (IllegalArgumentException e) {
      }
    }

    return languagesList;
  }

  @Override
  public JsonElement serialize(
      String s, Type type, JsonSerializationContext jsonSerializationContext) {
    return jsonSerializationContext.serialize(s);
  }
}
