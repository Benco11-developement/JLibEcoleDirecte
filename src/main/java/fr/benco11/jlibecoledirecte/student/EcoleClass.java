package fr.benco11.jlibecoledirecte.student;

public class EcoleClass {
	private int id;
	private String code;
	private String libelle;
	
	/**
	 * 
	 * @param id id de la classe
	 * @param code code de la classe
	 * @param libelle nom de la classe
	 */
	
	public EcoleClass(int id, String code, String libelle) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
	}
	
	/**
	 * Renvoie l'id de la classe
	 * @return l'id de la classe
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Renvoie le code de la classe
	 * @return le code de la classe
	 */
	
	public String getCode() {
		return code;
	}
	
	/**
	 * Renvoie le nom de la classe
	 * @return le libelle de la classe
	 */
	
	public String getLibelle() {
		return libelle;
	}
	
	
}
