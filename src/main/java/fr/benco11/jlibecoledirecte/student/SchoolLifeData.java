package fr.benco11.jlibecoledirecte.student;

import java.util.HashSet;
import java.util.Set;

public class SchoolLifeData {
	private Set<Absence> absencesRetards = new HashSet<Absence>(0);;
	private Set<SanctionEncouragement> sanctionsEncouragements = new HashSet<SanctionEncouragement>(0);;
	private SchoolLifeParameter parametrage;
	
	/**
	 * Renvoie les absences/retards
	 * @return le <code>Set</code> des absences/retards
	 */
	public Set<Absence> getAbsencesRetards() {
		return absencesRetards;
	}
	public SchoolLifeData(Set<Absence> absencesRetards, Set<SanctionEncouragement> sanctionsEncouragements, SchoolLifeParameter parametrage) {
		this.absencesRetards = absencesRetards;
		this.sanctionsEncouragements = sanctionsEncouragements;
		this.parametrage = parametrage;
	}
	
	/**
	 * Renvoie les paramètres de la vie scolaire
	 * @return l'objet de type <code>SchoolLifeParameter</code> correspondant aux paramètres de la vie scolaire
	 */
	public SchoolLifeParameter getParameter() {
		return parametrage;
	}
	
	/**
	 * Renvoie les sanctions/encouragements
	 * @return le <code>Set</code> des sanctions/encouragements
	 */
	public Set<SanctionEncouragement> getSanctionsEncouragements() {
		return sanctionsEncouragements;
	}
}
