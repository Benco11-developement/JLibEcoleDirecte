package fr.benco11.jlibecoledirecte.lib.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtils {
    private JsonUtils() {
    }

    public static final Gson gson = new GsonBuilder().serializeNulls()
                                                     .create();

    public static String serialize(Object o) {
        return "data=" + gson.toJson(o);
    }

    public static JsonObject deserialize(String json) {
        return deserialize(json, JsonObject.class);
    }

    public static <T> T deserialize(JsonElement json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }
}
