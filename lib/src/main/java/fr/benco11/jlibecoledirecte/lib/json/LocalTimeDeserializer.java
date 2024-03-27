package fr.benco11.jlibecoledirecte.lib.json;

import static fr.benco11.jlibecoledirecte.lib.utils.DateUtils.parseLocalTime;

import com.google.gson.*;
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

    public static class LocalTimeSerializer implements JsonSerializer<LocalTime> {

        @Override
        public JsonElement serialize(
                LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(FORMATTER.format(localTime));
        }
    }
}
