package fr.benco11.jlibecoledirecte.lib.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DefaultJsonService implements JsonService {
    public final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer.LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer.LocalDateSerializer())
            .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer.LocalTimeSerializer())
            .registerTypeAdapter(Double.class, new DoubleDeserializer())
            .disableHtmlEscaping()
            .create();

    @Override
    public String serialize(Object o) {
        if (o == null) return serializeEmpty();
        return "data=" + gson.toJson(o);
    }

    @Override
    public String serializeEmpty() {
        return "data={}";
    }

    @Override
    public JsonObject deserialize(String json) {
        return deserialize(json, JsonObject.class);
    }

    @Override
    public <T> T deserialize(JsonElement json, Type type) {
        return gson.fromJson(json, type);
    }

    @Override
    public <T> T deserialize(JsonElement json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    public <T> T deserialize(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    @Override
    public JsonObject dataBlock(String json) {
        return deserialize(json).getAsJsonObject("data");
    }
}
