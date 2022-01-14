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

    public String getDate() {
        return date;
    }

    public Set<String> getElementsProgramme() {
        return elementsProgramme;
    }

    public String getCoef() {
        return coef;
    }

    public String getValeur() {
        return valeur;
    }

    public String getDateSaisie() {
        return dateSaisie;
    }

    public String getMaxClasse() {
        return maxClasse;
    }

    public String getCodeSousMatiere() {
        return codeSousMatiere;
    }

    public String getDevoir() {
        return devoir;
    }

    public String getCodePeriode() {
        return codePeriode;
    }

    public boolean isNonSignificatif() {
        return nonSignificatif;
    }

    public boolean isEnLettre() {
        return enLettre;
    }

    public boolean isValeurisee() {
        return valeurisee;
    }

    public String getMoyenneClasse() {
        return moyenneClasse;
    }

    public String getTypeDevoir() {
        return typeDevoir;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public String getNoteSur() {
        return noteSur;
    }

    public String getLibelleMatiere() {
        return libelleMatiere;
    }

    public String getMinClasse() {
        return minClasse;
    }
}
