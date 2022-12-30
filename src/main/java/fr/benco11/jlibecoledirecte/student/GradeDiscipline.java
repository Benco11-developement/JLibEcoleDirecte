package fr.benco11.jlibecoledirecte.student;

public class GradeDiscipline {
	private int id;
	private String discipline;
	private String moyenne;
	private String moyenneClasse;
	private String moyenneMin;
	private String moyenneMax;
	private String coef;
	
	/**
	 * 
	 * @param id l'id de la matière
	 * @param discipline le nom de la matière
	 * @param moyenne la moyenne de l'élève
	 * @param moyenneClasse la moyenne de la classe
	 * @param moyenneMin la moyenne minimum
	 * @param moyenneMax la moyenne maximum
	 */
	
	public GradeDiscipline(int id, String discipline, String moyenne, String moyenneClasse, String moyenneMin,
			String moyenneMax) {
		super();
		this.id = id;
		this.discipline = discipline;
		this.moyenne = moyenne;
		this.moyenneClasse = moyenneClasse;
		this.moyenneMin = moyenneMin;
		this.moyenneMax = moyenneMax;
	}
	
	/**
	 * Renvoie l'id de la matière
	 * @return l'id de la matière
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Renvoie le nom de la matière
	 * @return le nom de la matière
	 */
	
	public String getDiscipline() {
		return discipline;
	}
	
	/**
	 * Renvoie la moyenne de l'élève
	 * @return la moyenne de l'élève
	 */
	
	public String getAverage() {
		return moyenne;
	}
	
	/**
	 * Renvoie la moyenne de classe
	 * @return la moyenne de la classe
	 */
	
	public String getClassAverage() {
		return moyenneClasse;
	}
	
	/**
	 * Renvoie la moyenne minimum
	 * @return la moyenne minimum
	 */
	
	public String getMinAverage() {
		return moyenneMin;
	}
	
	/**
	 * Renvoie la moyenne maximum
	 * @return la moyenne maximum
	 */
	
	public String getMaxAverage() {
		return moyenneMax;
	}
	
	/**
	 * Renvoie le coefficient
	 * @return le coefficient de la matière
	 */
	
	public String getCoef() {
		return coef;
	}
}
