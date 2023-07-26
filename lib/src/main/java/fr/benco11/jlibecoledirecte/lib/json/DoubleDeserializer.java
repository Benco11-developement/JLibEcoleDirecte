package fr.benco11.jlibecoledirecte.lib.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import fr.benco11.jlibecoledirecte.lib.exception.runtime.EcoleDirecteDoubleParsingException;
import fr.benco11.jlibecoledirecte.lib.utils.NumberUtils;
import java.lang.reflect.Type;

public class DoubleDeserializer implements JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return NumberUtils.parseDouble(json.getAsString(), new EcoleDirecteDoubleParsingException());
    }
}
