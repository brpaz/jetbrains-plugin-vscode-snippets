package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils.serializer;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageProvider;
import com.google.gson.*;
import java.lang.reflect.Type;

public class PackageProviderSerializer
    implements JsonSerializer<String>, JsonDeserializer<PackageProvider> {

  @Override
  public PackageProvider deserialize(
      JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException {
    String value = jsonElement.getAsString();

    return PackageProvider.fromLabel(value);
  }

  @Override
  public JsonElement serialize(
      String s, Type type, JsonSerializationContext jsonSerializationContext) {
    return jsonSerializationContext.serialize(s);
  }
}
