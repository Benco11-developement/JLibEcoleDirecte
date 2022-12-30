package fr.benco11.jlibecoledirecte.student;

public class GradePeriode {
	private String idPeriode;
	private String codePeriode;
	private String periode;
	private GradeSetMatieres ensembleMatieres;
	
	/**
	 * Renvoie l'id de la période
	 * @return l'id de la période
	 */
	
	public String getIdPeriode() {
		return idPeriode;
	}

	/**
	 * Renvoie le code de la période
	 * @return le code de la période
	 */
	
	public String getCodePeriode() {
		return codePeriode;
	}

	/**
	 * Renvoie le nom de la période
	 * @return le nom de la période
	 */
	
	public String getPeriode() {
		return periode;
	}

	/**
	 * Renvoie un objet <code>GradeSetMatieres</code>
	 * @return l'objet <code>GradeSetMatieres</code> correspondant à l'ensemble des matières
	 */
	
	public GradeSetMatieres getEnsembleMatieres() {
		return ensembleMatieres;
	}

	/**
	 * 
	 * @param idPeriode l'id de la période
	 * @param codePeriode le code de la période
	 * @param periode le nom de la période
	 * @param ensembleMatieres l'ensemble de matières
	 */
	
	public GradePeriode(String idPeriode, String codePeriode, String periode, GradeSetMatieres ensembleMatieres) {
		this.idPeriode = idPeriode;
		this.codePeriode = codePeriode;
		this.periode = periode;
		this.ensembleMatieres = ensembleMatieres;
	}
}
