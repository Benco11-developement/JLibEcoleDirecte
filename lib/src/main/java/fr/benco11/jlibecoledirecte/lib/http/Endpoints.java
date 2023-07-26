package fr.benco11.jlibecoledirecte.lib.http;

public enum Endpoints {
    API("api.ecoledirecte.com/v3/"),
    LOGIN(API.url + "login.awp"),
    STUDENT(API.url + "eleves/%s/"),
    SETTINGS(API.url + "logins/%s.awp?verbe=get"),

    GRADE(STUDENT.url + "notes.awp?verbe=get"),
    SCHOOL_LIFE(STUDENT.url + "viescolaire.awp?verbe=get");

    private final String url;

    Endpoints(String url) {
        this.url = url;
    }

    public String asString(Object... args) {
        return url.formatted(args);
    }
}
