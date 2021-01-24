package fr.benco11.jlibecoledirecte;

import fr.benco11.jlibecoledirecte.student.EcoleClass;

public class Profile {
	private String sexe;
	private EcoleClass classe;
	private String photo;

	/**
	 * 
	 * @param gender genre de l'utilisateur <code>M</code> s'il est un homme, <code>F</code> si elle est une femme
	 * @param classe objet de type <code>EcoleClasse</code> correspondant à la classe de l'élève
	 * @param photo lien de la photo de l'élève
	 */
	
	public Profile(String gender, EcoleClass classe, String photo) {
		this.sexe = gender;
		this.classe = classe;
		this.photo = photo;
	}
	
	/**
	 * Renvoie le genre de l'élève
	 * @return le genre de l'élève
	 */
	public String getGender() {
		return sexe;
	}
	
	/**
	 * Renvoie l'objet de type <code>EcoleClass</code> de l'élève
	 * @return l'objet de type <code>EcoleClass</code> de l'élève
	 */
	
	public EcoleClass getClasse() {
		return classe;
	}
	
	/**
	 * Renvoie le lien de la photo de l'élève
	 * @return le lien de la photo de l'élève
	 */
	
	public String getPhoto() {
		return photo;
	}
	
}
