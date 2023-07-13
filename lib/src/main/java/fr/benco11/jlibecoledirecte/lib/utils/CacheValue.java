package fr.benco11.jlibecoledirecte.lib.utils;

import java.time.Instant;
import java.util.Optional;

public record CacheValue(Instant expires, Optional<Object> value) {
    public <T> Optional<T> value(Class<T> tClass) {
        return (value.isPresent() && tClass.isInstance(value.get()))
                ? Optional.of(tClass.cast(value.get()))
                : Optional.empty();
    }
}
