package fr.benco11.jlibecoledirecte.student;

import java.util.Set;

public class NoteJson {

    private int code;
    private NoteData data;
    private String token;

    /**
     *
     * @param code le code de la réponse
     * @param token le token de session
     * @param data le contenu de la réponse
     */

    public NoteJson(int code, NoteData data, String token) {
        this.code = code;
        this.data = data;
        this.token = token;
    }


    public int getCode() {
        return code;
    }

    /**
     * Renvoie le <code>NoteData</code>
     * @return le contenu de la réponse sous forme d'un objet <code>NoteData</code>
     */
    public NoteData getData() {
        return data;
    }

    /**
     * Renvoie le token
     * @return le token de la session
     */
    public String getToken() {
        return token;
    }
}
