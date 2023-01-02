package fr.benco11.jlibecoledirecte.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
    private JsonUtils() {}
    public static final Gson gson = new GsonBuilder().create();

    public static String serialize(Object o) {
        return "data=" + gson.toJson(o);
    }
}
