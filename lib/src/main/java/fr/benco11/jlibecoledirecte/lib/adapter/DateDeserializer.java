package fr.benco11.jlibecoledirecte.lib.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import fr.benco11.jlibecoledirecte.lib.exception.runtime.EcoleDirecteDateParsingException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static fr.benco11.jlibecoledirecte.lib.utils.DateUtils.format;
import static fr.benco11.jlibecoledirecte.lib.utils.DateUtils.parse;

public class DateDeserializer implements JsonDeserializer<Date> {
    public static final List<DateFormat> FORMATS = Arrays.asList(format("yyyy-MM-dd HH:mm"), format("yyyy-MM-dd")
            , format("HH:mm"));

    @Override
    public Date deserialize(JsonElement json, Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
        if (json.getAsString().isBlank()) return null;
        return FORMATS.stream()
                .map(f -> parse(json.getAsString(), f))
                .filter(Objects::nonNull)
                .findAny()
                .orElseThrow(EcoleDirecteDateParsingException::new);
    }
}
