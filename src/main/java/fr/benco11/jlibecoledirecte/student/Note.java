package fr.benco11.jlibecoledirecte.student;

import java.util.Set;

public class Note {

    private String date;
    private Set<String> elementsProgramme;
    private String coef;
    private String valeur;
    private String dateSaisie;
    private String maxClasse;
    private String codeSousMatiere;
    private String devoir;
    private String codePeriode;
    private boolean nonSignificatif;
    private boolean enLettre;
    private boolean valeurisee;
    private String moyenneClasse;
    private String typeDevoir;
    private String codeMatiere;
    private String noteSur;
    private String libelleMatiere;
    private String minClasse;

    /**
     *
     * @param date la date de l'évaluation
     * @param elementsProgramme liste des éléments du cours présent dans l'évaluation
     * @param coef le coefficient de l'évaluation
     * @param valeur la note de l'élève
     * @param dateSaisie date d'ajout de la note sur école directe
     * @param maxClasse la meilleure note de la classe pour cette évaluation
     * @param codeSousMatiere code de la sous matière de l'évaluation vide si nul
     * @param devoir le nom de l'évaluation
     * @param codePeriode code du trimestre/semestre de l'évaluation
     * @param nonSignificatif la note compte-t-elle?
     * @param enLettre ?
     * @param valeurisee ?
     * @param moyenneClasse la moyenne de la classe pour cette évaluation
     * @param typeDevoir le type d'évaluation
     * @param codeMatiere code de la matière de l'évaluation
     * @param noteSur sur combien est l'évaluation
     * @param libelleMatiere le nom complet de la matière
     * @param minClasse la note minimum de la classe
     */

    public Note(String date, Set<String> elementsProgramme, String coef, String valeur, String dateSaisie, String maxClasse, String codeSousMatiere, String devoir, String codePeriode, boolean nonSignificatif, boolean enLettre, boolean valeurisee, String moyenneClasse, String typeDevoir, String codeMatiere, String noteSur, String libelleMatiere, String minClasse) {
        this.date = date;
        this.elementsProgramme = elementsProgramme;
        this.coef = coef;
        this.valeur = valeur;
        this.dateSaisie = dateSaisie;
        this.maxClasse = maxClasse;
        this.codeSousMatiere = codeSousMatiere;
        this.devoir = devoir;
        this.codePeriode = codePeriode;
        this.nonSignificatif = nonSignificatif;
        this.enLettre = enLettre;
        this.valeurisee = valeurisee;
        this.moyenneClasse = moyenneClasse;
        this.typeDevoir = typeDevoir;
        this.codeMatiere = codeMatiere;
        this.noteSur = noteSur;
        this.libelleMatiere = libelleMatiere;
        this.minClasse = minClasse;
    }

    /**
     * Renvoie la date de l'évaluation
     * @return la date de l'évaluation
     */

    public String getDate() {
        return date;
    }

    /**
     * Renvoie les éléments du programme à l'évaluation
     * @return les éléments du programme à l'évaluation
     */

    public Set<String> getElementsProgramme() {
        return elementsProgramme;
    }

    /**
     * Renvoie le coefficient de l'évaluation
     * @return le coefficient de l'évaluation
     */

    public String getCoef() {
        return coef;
    }

    /**
     * Renvoie la note de l'élève
     * @return la note de l'élève
     */

    public String getValeur() {
        return valeur;
    }

    /**
     * Renvoie la date de mise en ligne de l'évaluation sur école directe
     * @return la date de mise en ligne de l'évaluation sur école directe
     */

    public String getDateSaisie() {
        return dateSaisie;
    }

    /**
     * Renvoie la note maximum de la classe
     * @return la note maximum de la classe
     */

    public String getMaxClasse() {
        return maxClasse;
    }

    /**
     * Renvoie le code de la sous matière, vide si nul
     * @return le code de la sous matière, vide si nul
     */

    public String getCodeSousMatiere() {
        return codeSousMatiere;
    }

    /**
     * Renvoie le nom de l'évaluation
     * @return le nom de l'évaluation
     */

    public String getDevoir() {
        return devoir;
    }

    /**
     * Renvoie le code du trimestre/semestre de l'évaluation
     * @return le code du trimestre/semestre de l'évaluation
     */

    public String getCodePeriode() {
        return codePeriode;
    }

    /**
     * Renvoie ?
     * @return ?
     */

    public boolean isNonSignificatif() {
        return nonSignificatif;
    }

    /**
     * Renvoie ?
     * @return ?
     */

    public boolean isEnLettre() {
        return enLettre;
    }

    /**
     * Renvoie ?
     * @return ?
     */

    public boolean isValeurisee() {
        return valeurisee;
    }

    /**
     * Renvoie la note moyenne de la classe
     * @return la note moyenne de la classe
     */

    public String getMoyenneClasse() {
        return moyenneClasse;
    }

    /**
     * Renvoie le type de devoir
     * @return le type de devoir
     */

    public String getTypeDevoir() {
        return typeDevoir;
    }

    /**
     * Renvoie le code de la matière
     * @return le code de la matière
     */

    public String getCodeMatiere() {
        return codeMatiere;
    }

    /**
     * Renvoie sur combien la note est
     * @return sur combien la note est
     */

    public String getNoteSur() {
        return noteSur;
    }

    /**
     * Renvoie le nom de la matière de l'évaluation
     * @return le nom de la matière de l'évaluation
     */

    public String getLibelleMatiere() {
        return libelleMatiere;
    }

    /**
     * Renvoie la note minimum de la classe
     * @return la note minimum de la classe
     */

    public String getMinClasse() {
        return minClasse;
    }
}
