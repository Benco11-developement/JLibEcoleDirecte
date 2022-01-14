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

    public String getEnd_date() {
        return end_date;
    }

    public boolean isDispensable() {
        return dispensable;
    }

    public String getClasse() {
        return classe;
    }

    public boolean isFlexible() {
        return isFlexible;
    }

    public boolean isAnnule() {
        return isAnnule;
    }

    public String getColor() {
        return color;
    }

    public boolean isModifie() {
        return isModifie;
    }

    public int getDispense() {
        return dispense;
    }

    public String getClasseCode() {
        return classeCode;
    }

    public String getProf() {
        return prof;
    }

    public int getClasseId() {
        return classeId;
    }

    public String getMatiere() {
        return matiere;
    }

    public int getGroupeId() {
        return groupeId;
    }

    public String getSalle() {
        return salle;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public String getTypeCours() {
        return typeCours;
    }

    public boolean isContenuDeSeance() {
        return contenuDeSeance;
    }

    public boolean isDevoirAFaire() {
        return devoirAFaire;
    }

    public String getIcone() {
        return icone;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getGroupe() {
        return groupe;
    }

    public String getGroupeCode() {
        return groupeCode;
    }

    public String getStart_date() {
        return start_date;
    }
}
