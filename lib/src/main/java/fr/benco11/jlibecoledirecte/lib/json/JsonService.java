package fr.benco11.jlibecoledirecte.lib.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;

public interface JsonService {
    String serialize(Object object);

    String serializeEmpty();

    JsonObject deserialize(String json);

    <T> T deserialize(JsonElement jsonRepresentation, Type objectType);

    <T> T deserialize(JsonElement jsonRepresentation, Class<T> objectClass);

    <T> T deserialize(String json, Type objectType);

    <T> T deserialize(String json, Class<T> objectClass);

    JsonObject dataBlock(String json);
}
