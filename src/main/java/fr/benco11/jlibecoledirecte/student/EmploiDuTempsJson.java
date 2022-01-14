package fr.benco11.jlibecoledirecte.student;

import java.util.Set;

public class EmploiDuTempsJson {

    private String token;
    private Set<Cours> data;
    private int code;

    /**
     *
     * @param code le code de la réponse
     * @param token le token de session
     * @param data le contenu de la réponse
     */

    public EmploiDuTempsJson(String token, Set<Cours> data, int code) {
        this.token = token;
        this.data = data;
        this.code = code;
    }

    /**
     * Renvoie le token
     * @return le token de la session
     */
    public String getToken() {
        return token;
    }

    /**
     * Renvoie le <code>Cours</code>
     * @return le contenu de la réponse sous forme d'un objet <code>Cours</code>
     */
    public Set<Cours> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
