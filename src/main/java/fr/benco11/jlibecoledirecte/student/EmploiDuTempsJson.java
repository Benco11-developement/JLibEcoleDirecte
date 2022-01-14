package fr.benco11.jlibecoledirecte.student;

import java.util.Set;

public class EmploiDuTempsJson {

    private String token;
    private Set<Cours> data;
    private int code;

    public EmploiDuTempsJson(String token, Set<Cours> data, int code) {
        this.token = token;
        this.data = data;
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public Set<Cours> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
