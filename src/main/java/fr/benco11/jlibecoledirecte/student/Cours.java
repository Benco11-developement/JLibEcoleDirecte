package fr.benco11.jlibecoledirecte.student;

public class Cours {

    private String end_date;
    private boolean dispensable;
    private String classe;
    private boolean isFlexible;
    private boolean isAnnule;
    private String color;
    private boolean isModifie;
    private int dispense;
    private String classeCode;
    private String prof;
    private int classeId;
    private String matiere;
    private int groupeId;
    private String salle;
    private String codeMatiere;
    private String typeCours;
    private boolean contenuDeSeance;
    private boolean devoirAFaire;
    private String icone;
    private int id;
    private String text;
    private String groupe;
    private String groupeCode;
    private String start_date;

    /**
     *
     * @param end_date la date de fin du cours
     * @param dispensable l'élève peut se dispenser d'une présence au cours
     * @param classe la classe de l'élève
     * @param isFlexible ?
     * @param isAnnule le cours est-il annulé
     * @param color la couleur du cours sur école directe
     * @param isModifie le cours a été modifié
     * @param dispense le nombre de dispensés (?)
     * @param classeCode le code de la classe
     * @param prof le nom du professeur
     * @param classeId l'id de la classe
     * @param matiere la matière du cours
     * @param groupeId l'id du groupe (0 si aucun groupe)
     * @param salle la salle du cours
     * @param codeMatiere le code de la matière
     * @param typeCours le type de cours
     * @param contenuDeSeance un contenu de séance est-il disponible ?
     * @param devoirAFaire des devoirs sont-ils disponible dans le cahier de texte
     * @param icone l'icône du cours
     * @param id l'id du cours
     * @param text ?
     * @param groupe le nom du groupe, vide si nul
     * @param groupeCode le code du groupe, vide si nul
     * @param start_date la date de début du cours
     */

    public Cours(String end_date, boolean dispensable, String classe, boolean isFlexible, boolean isAnnule, String color, boolean isModifie, int dispense, String classeCode, String prof, int classeId, String matiere, int groupeId, String salle, String codeMatiere, String typeCours, boolean contenuDeSeance, boolean devoirAFaire, String icone, int id, String text, String groupe, String groupeCode, String start_date) {
        this.end_date = end_date;
        this.dispensable = dispensable;
        this.classe = classe;
        this.isFlexible = isFlexible;
        this.isAnnule = isAnnule;
        this.color = color;
        this.isModifie = isModifie;
        this.dispense = dispense;
        this.classeCode = classeCode;
        this.prof = prof;
        this.classeId = classeId;
        this.matiere = matiere;
        this.groupeId = groupeId;
        this.salle = salle;
        this.codeMatiere = codeMatiere;
        this.typeCours = typeCours;
        this.contenuDeSeance = contenuDeSeance;
        this.devoirAFaire = devoirAFaire;
        this.icone = icone;
        this.id = id;
        this.text = text;
        this.groupe = groupe;
        this.groupeCode = groupeCode;
        this.start_date = start_date;
    }

    /**
     * Renvoie la date de fin du cours
     * @return la date de fin du cours
     */

    public String getEnd_date() {
        return end_date;
    }

    /**
     * Renvoie si le cours est dispensable
     * @return si le cours est dispensable
     */

    public boolean isDispensable() {
        return dispensable;
    }

    /**
     * Renvoie le nom de la classe
     * @return le nom de la classe
     */

    public String getClasse() {
        return classe;
    }

    /**
     * Renvoie ?
     * @return ?
     */

    public boolean isFlexible() {
        return isFlexible;
    }

    /**
     * Renvoie si le cours est annulé
     * @return si le cours est annulé
     */

    public boolean isAnnule() {
        return isAnnule;
    }

    /**
     * Renvoie la couleur sur école directe
     * @return la couleur sur école directe
     */

    public String getColor() {
        return color;
    }

    /**
     * Renvoie si le cours est modifié
     * @return si le cours est modifié
     */

    public boolean isModifie() {
        return isModifie;
    }

    /**
     * Renvoie le nombre de dispenses
     * @return le nombre de dispenses
     */

    public int getDispense() {
        return dispense;
    }

    /**
     * Renvoie le code de la classe
     * @return le code de la classe
     */

    public String getClasseCode() {
        return classeCode;
    }

    /**
     * Renvoie le nom du professeur
     * @return le nom du professeur
     */

    public String getProf() {
        return prof;
    }

    /**
     * Renvoie l'id de la classe
     * @return l'id de la classe
     */

    public int getClasseId() {
        return classeId;
    }

    /**
     * Renvoie le nom de la matière
     * @return le nom de la matière
     */

    public String getMatiere() {
        return matiere;
    }

    /**
     * Renvoie l'id du groupe
     * @return l'id du groupe
     */

    public int getGroupeId() {
        return groupeId;
    }

    /**
     * Renvoie la salle du cours
     * @return la salle du cours
     */

    public String getSalle() {
        return salle;
    }

    /**
     * Renvoie le code de la matière
     * @return le code de la matière
     */

    public String getCodeMatiere() {
        return codeMatiere;
    }

    /**
     * Renvoie le type de cours
     * @return le type de cours
     */

    public String getTypeCours() {
        return typeCours;
    }

    /**
     * Renvoie si il y a un contenu de séance ou non
     * @return si il y a un contenu de séance ou non
     */

    public boolean isContenuDeSeance() {
        return contenuDeSeance;
    }

    /**
     * Renvoie s'il y a des devoirs à faire ou non
     * @return s'il y a des devoirs à faire ou non
     */

    public boolean isDevoirAFaire() {
        return devoirAFaire;
    }

    /**
     * Renvoie l'icône du cours, vide si nul
     * @return l'icône du cours, vide si nul
     */

    public String getIcone() {
        return icone;
    }

    /**
     * Renvoie l'id de la matière
     * @return l'id de la matière
     */

    public int getId() {
        return id;
    }

    /**
     * Renvoie ?
     * @return ?
     */

    public String getText() {
        return text;
    }

    /**
     * Renvoie le nom du groupe, vide si nul
     * @return le nom du groupe, vide si nul
     */

    public String getGroupe() {
        return groupe;
    }

    /**
     * Renvoie le code du groupe, vide si nul
     * @return le code du groupe, vide si nul
     */

    public String getGroupeCode() {
        return groupeCode;
    }

    /**
     * Renvoie la date de début du cours
     * @return la date de début du cours
     */

    public String getStart_date() {
        return start_date;
    }
}
