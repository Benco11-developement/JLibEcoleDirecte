package fr.benco11.jlibecoledirecte.lib.json;

import static fr.benco11.jlibecoledirecte.lib.utils.DateUtils.parseLocalDate;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (json.getAsString().isBlank()) {
            return null;
        }
        return parseLocalDate(json.getAsString(), FORMATTER);
    }

    public static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        @Override
        public JsonElement serialize(
                LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(FORMATTER.format(localDate));
        }
    }
}
