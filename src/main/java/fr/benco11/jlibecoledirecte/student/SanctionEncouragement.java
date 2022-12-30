package fr.benco11.jlibecoledirecte.student;

public class SanctionEncouragement {
	private int id;
	private int idEleve;
	private String nomEleve;
	private String typeElement;
	private String date;
	private String displayDate;
	private String libelle;
	private String motif;
	private boolean justifie;
	private String par;
	private String commentaire;
	private String aFaire;
	private String dateDeroulement;
	
	/**
	 * 
	 * @param id l'id de l'élève
	 * @param idEleve l'idEleve de l'élève
	 * @param nomEleve le nom de l'élève
	 * @param typeElement le type d'absence
	 * @param date la date où l'absence a été renseigné
	 * @param displayDate la date donnée de l'absence
	 * @param libelle le libelle de l'absence
	 * @param motif le motif de l'absence
	 * @param justifie <code>true</code> si l'absence est justifiée
	 * @param par le distributeur de l'absence
	 * @param commentaire le commentaire de l'absence
	 * @param aFaire le travail à faire
	 * @param dateDeroulement la date de déroulement de l'absence
	 */
	public SanctionEncouragement(int id, int idEleve, String nomEleve, String typeElement, String date, String displayDate, String libelle, String motif, String par, 
			String commentaire, boolean justifie, String aFaire, String dateDeroulement) {
		this.id = id;
		this.idEleve = idEleve;
		this.nomEleve = nomEleve;
		this.typeElement = typeElement;
		this.date = date;
		this.displayDate = displayDate;
		this.libelle = libelle;
		this.motif = motif;
		this.par = par;
		this.commentaire = commentaire;
		this.aFaire = aFaire;
		this.dateDeroulement = dateDeroulement;
	}
	
	/**
	 * Renvoie l'id de l'élève
	 * @return l'id de l'élève
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Renvoie l'<code>idEleve</code> de l'élève
	 * @return l'<code>idEleve</code> de l'élève
	 */
	public int getIdEleve() {
		return idEleve;
	}
	
	/**
	 * Renvoie le nom de l'élève
	 * @return le nom de l'élève
	 */
	public String getName() {
		return nomEleve;
	}
	
	/**
	 * Renvoie le type de l'absence
	 * @return le type de l'absence
	 */
	public String getTypeElement() {
		return typeElement;
	}
	
	/**
	 * Renvoie la date où l'absence a été renseigné
	 * @return la date où l'absence a été renseigné
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Renvoie la date donnée de l'absence
	 * @return la date donnée de l'absence
	 */
	public String getDisplayDate() {
		return displayDate;
	}
	
	/**
	 * Renvoie le libelle de l'absence
	 * @return le libelle de l'absence
	 */
	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * Renvoie le motif de l'absence
	 * @return le motif de l'absence
	 */
	public String getReason() {
		return motif;
	}
	
	/**
	 * Renvoie <code>true</code> si l'absence est justifiée
	 * @return <code>true</code> si l'absence est justifiée
	 */
	public boolean isJustified() {
		return justifie;
	}
	
	/**
	 * Renvoie le distributeur de l'absence
	 * @return le distributeur de l'absence
	 */
	public String getBy() {
		return par;
	}
	
	/**
	 * Renvoie le commentaire de l'absence
	 * @return le commentaire de l'absence
	 */
	public String getCommentary() {
		return commentaire;
	}
	
	/**
	 * Renvoie le travail à faire
	 * @return le travail à faire
	 */
	public String getToDo() {
		return aFaire;
	}
	
	/**
	 * Renvoie la date de l'action
	 * @return la date de l'action
	 */ 
	public String getDateOfAction() {
		return dateDeroulement;
	}
}
