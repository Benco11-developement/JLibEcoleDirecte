package fr.benco11.jlibecoledirecte.lib.json;

import static fr.benco11.jlibecoledirecte.lib.utils.DateUtils.parseLocalDateTime;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (json.getAsString().isBlank()) {
            return null;
        }
        return parseLocalDateTime(json.getAsString(), FORMATTER);
    }

    public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

        @Override
        public JsonElement serialize(
                LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(FORMATTER.format(localDateTime));
        }
    }
}
