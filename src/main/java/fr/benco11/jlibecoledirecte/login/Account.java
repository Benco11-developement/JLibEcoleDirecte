package fr.benco11.jlibecoledirecte.login;

import java.util.Set;

import fr.benco11.jlibecoledirecte.Module;
import fr.benco11.jlibecoledirecte.Profile;

public class Account {
	private final int idLogin;
	private final int id;
	private final String uid;
	private final String typeCompte;
	private final String prenom;
	private final String nom;
	private final String email;
	private final String anneeScolaireCourante;
	private final Profile profile;
	private final String nomEtablissement;
	
	
	/**
	 * 
	 * @param idLogin le idLogin de l'utilisateur
	 * @param id l'id de l'utilisateur
	 * @param uid l'uid de l'utilisateur
	 * @param typeCompte le type du compte de l'utilisateur
	 * @param prenom prénom de l'utilisateur
	 * @param nom nom de l'utilisateur
	 * @param email email de l'utilisateur
	 * @param anneeScolaireCourante année scolaire de l'utilisateur s'il est un élève
	 * @param profile l'objet de type <code>Profile</code> correspondantà l'utilisateur
	 * @param modules liste des modules
	 * @param nomEtablissement nom de l'établissement de l'élève
	 */
	
	public Account(final int idLogin, final int id, final String uid, final String typeCompte, final String prenom, final String nom, final String email, final String anneeScolaireCourante, final Profile profile, final String nomEtablissement,
			Set<Module> modules) {
		this.nom = nom;
		this.anneeScolaireCourante = anneeScolaireCourante;
		this.email = email;
		this.prenom = prenom;
		this.typeCompte = typeCompte;
		this.idLogin = idLogin;
		this.id = id;
		this.uid = uid;
		this.profile = profile;
		this.nomEtablissement = nomEtablissement;
	}

	/**
	 * Renvoie le profil de l'utilisateur
	 * @return l'objet <code>Profile</code> de l'utilisateur
	 */
	
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Renvoie le prénom de l'utilisateur
	 * @return le prénom de l'utilisateur
	 */
	
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Renvoie le nom de l'utilisateur
	 * @return le nom de l'utilisateur
	 */
	
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie l'email de l'utilisateur
	 * @return l'adresse mail de l'utilisateur
	 */
	
	public String getEmail() {
		return email;
	}

	/**
	 * Renvoie l'année scolaire de l'utilisateur
	 * @return l'année scolaire de l'utilisateur
	 */
	
	public String getAnneeScolaireCourante() {
		return anneeScolaireCourante;
	}

	/**
	 * 
	 * @return le type du compte, <code>E</code> si c'est un élève
	 */
	
	public String getTypeCompte() {
		return typeCompte;
	}

	/**
	 * Renvoie le idLogin de l'utilisateur
	 * @return le idLogin de l'utilisateur
	 */
	
	public int getIdLogin() {
		return idLogin;
	}

	/**
	 * Renvoie l'id de l'utilisateur
	 * @return l'id de l'utilisateur
	 */
	
	public int getId() {
		return id;
	}

	/**
	 * Renvoie l'uid de l'utilisateur
	 * @return l'uid de l'utilisateur
	 */
	
	public String getUid() {
		return uid;
	}

	/**
	 * Renvoie le nom de l'établissement de l'élève
	 * @return le nom de l'établissement de l'élève
	 */

	public String getNomEtablissement() {
		return nomEtablissement;
	}
}
