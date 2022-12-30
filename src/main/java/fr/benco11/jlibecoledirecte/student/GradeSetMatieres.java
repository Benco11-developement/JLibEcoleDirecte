package fr.benco11.jlibecoledirecte.student;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

public class GradeSetMatieres {
	private Set<GradeDiscipline> disciplines = new HashSet<GradeDiscipline>(0);
	private String appreciationPP;
	private String moyenneMax;
	private String moyenneMin;
	private String moyenneClasse;
	private String moyenneGenerale;
	private String dateCalcul;
	
	/**
	 *
	 * @param disciplines la liste des matières
	 */
	public GradeSetMatieres(Set<GradeDiscipline> disciplines) {
		this.disciplines = disciplines;
	}
	
	
	/**
	 * Renvoie l'appréciation de l'élève
	 * @return l'appréciation de l'élève
	 */
	
	@Nullable
	public String getAppreciationPP() {
		return appreciationPP;
	}
	
	/**
	 * Renvoie la moyenne maximum
	 * @return la moyenne maximum
	 */

	@Nullable
	public String getMaxAverage() {
		return moyenneMax;
	}

	/**
	 * Renvoie la moyenne minimum
	 * @return la moyenne minimum
	 */
	
	@Nullable
	public String getMinAverage() {
		return moyenneMin;
	}

	/**
	 * Renvoie la moyenne de la classe
	 * @return la moyenne de classe
	 */
	
	@Nullable
	public String getAverageClass() {
		return moyenneClasse;
	}
	
	/**
	 * Renvoie la moyenne de l'élève
	 * @return la moyenne de l'élève
	 */

	@Nullable
	public String getAverage() {
		return moyenneGenerale;
	}
	
	/**
	 * Renvoie la date de calcul des moyennes
	 * @return la date de calcul des moyennes
	 */

	@Nullable
	public String getDate() {
		return dateCalcul;
	}

	/**
	 * Renvoie la liste de toutes les matières
	 * @return le <code>Set</code> de toutes les matières
	 */

	public Set<GradeDiscipline> getDisciplines() {
		return disciplines;
	}
}
