package fr.benco11.jlibecoledirecte.student;

import java.util.Set;

public class NoteJson {

    private int code;
    private NoteData data;
    private String token;

    public NoteJson(int code, NoteData data, String token) {
        this.code = code;
        this.data = data;
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public NoteData getData() {
        return data;
    }

    public String getToken() {
        return token;
    }
}
