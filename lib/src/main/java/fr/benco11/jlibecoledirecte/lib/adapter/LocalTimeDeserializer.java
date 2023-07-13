package fr.benco11.jlibecoledirecte.lib.adapter;

import static fr.benco11.jlibecoledirecte.lib.utils.DateUtils.parseLocalTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (json.getAsString().isBlank()) {
            return null;
        }
        return parseLocalTime(json.getAsString(), FORMATTER);
    }
}
