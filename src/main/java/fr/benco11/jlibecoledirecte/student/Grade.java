package fr.benco11.jlibecoledirecte.student;

public class Grade {
	private String coef;
	private String note;
	private int disciplineId;

	/**
	 * 
	 * @param note la note
	 * @param coef le coefficient de la note
	 * @param disciplineId l'id de la matière
	 */
	
	public Grade(String note, String coef, int disciplineId) {
		this.note = note;
		this.coef = coef;
		this.disciplineId = disciplineId;
	}

	/**
	 * Renvoie l'id de la matière
	 * @return l'id de la matière
	 */
	
	public int getDisciplineId() {
		return disciplineId;
	}
	
	/**
	 * Renvoie le coefficient
	 * @return le coefficient de la note
	 */

	public String getCoef() {
		return coef;
	}

	/**
	 * Renvoie la note
	 * @return la note
	 */
	
	public String getNote() {
		return note;
	}
}
