package fr.benco11.jlibecoledirecte.lib.account;

public record AccountImplementation(ImplementationType type, Object... args) {
    public enum ImplementationType {
        DEFAULT, CACHED, PRE_LOAD_CACHE
    }
}
