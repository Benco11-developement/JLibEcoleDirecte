package fr.benco11.jlibecoledirecte.lib.utils;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
