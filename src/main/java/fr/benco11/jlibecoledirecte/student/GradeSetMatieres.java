package fr.benco11.jlibecoledirecte.student;

import java.util.HashSet;
import java.util.Set;

public class GradeSetMatieres {
	private Set<GradeDiscipline> disciplines = new HashSet<GradeDiscipline>(0);

	/**
	 * 
	 * @param disciplines la liste des matières
	 */
	public GradeSetMatieres(Set<GradeDiscipline> disciplines) {
		this.disciplines = disciplines;
	}
	
	/**
	 * Renvoie la liste de toutes les matières
	 * @return le <code>Set</code> de toutes les matières
	 */

	public Set<GradeDiscipline> getDisciplines() {
		return disciplines;
	}
}
