package fr.benco11.jlibecoledirecte.student;

import java.util.HashSet;
import java.util.Set;

public class GradeData {
	private Set<GradePeriode> periodes = new HashSet<GradePeriode>(0);

	/**
	 * Renvoie la liste des périodes
	 * @return un <code>Set</code> des périodes
	 */
	public Set<GradePeriode> getPeriodes() {
		return periodes;
	}
}
