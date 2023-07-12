package fr.benco11.jlibecoledirecte.lib.account;

import java.util.Arrays;

public record AccountImplementation(ImplementationType type, Object... args) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountImplementation a = (AccountImplementation) o;
        return type == a.type() && Arrays.equals(args, a.args());
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 6993599 * result + Arrays.hashCode(args);
        return result;
    }

    @Override
    public String toString() {
        return "AccountImplementation{" +
                "type=" + type +
                ", args=" + Arrays.toString(args) +
                '}';
    }

    public enum ImplementationType {
        DEFAULT, CACHED, PRE_LOAD_CACHE
    }
}
