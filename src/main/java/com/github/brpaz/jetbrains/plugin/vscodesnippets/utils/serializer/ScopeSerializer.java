package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.serializer;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeLanguage;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ScopeSerializer
    implements JsonSerializer<String>, JsonDeserializer<List<VSCodeLanguage>> {

  @Override
  public List<VSCodeLanguage> deserialize(
      JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    List<VSCodeLanguage> languagesList = new ArrayList<>();

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
