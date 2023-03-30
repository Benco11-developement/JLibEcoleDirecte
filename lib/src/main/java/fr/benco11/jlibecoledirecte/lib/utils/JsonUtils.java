package fr.benco11.jlibecoledirecte.lib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.benco11.jlibecoledirecte.lib.adapter.DateDeserializer;
import fr.benco11.jlibecoledirecte.lib.adapter.DoubleDeserializer;

import java.util.Date;

public class JsonUtils {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer())
            .registerTypeAdapter(Double.class, new DoubleDeserializer())
            .create();

    private JsonUtils() {
    }

    public static String serialize(Object o) {
        return "data=" + GSON.toJson(o);
    }

    public static JsonObject deserialize(String json) {
        return deserialize(json, JsonObject.class);
    }

    public static <T> T deserialize(JsonElement json, Class<T> tClass) {
        return GSON.fromJson(json, tClass);
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        return GSON.fromJson(json, tClass);
    }
}
