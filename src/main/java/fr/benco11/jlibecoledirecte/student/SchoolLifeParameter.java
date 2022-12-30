package fr.benco11.jlibecoledirecte.student;

public class SchoolLifeParameter {
	private boolean justificationEnLigne;
    private boolean absenceCommentaire;
    private boolean retardCommentaire;
    private boolean sanctionsVisible;
    private boolean sanctionParQui;
    private boolean sanctionCommentaire;
    private boolean encouragementsVisible;
    private boolean encouragementParQui;
    private boolean encouragementCommentaire;
    
    public SchoolLifeParameter(boolean justificationEnLigne, boolean absenceCommentaire, boolean retardCommentaire, boolean sanctionsVisible, boolean sanctionParQui, boolean sanctionCommentaire,
			boolean encouragementsVisible, boolean encouragementParQui, boolean encouragementCommentaire) {
		this.justificationEnLigne = justificationEnLigne;
		this.absenceCommentaire = absenceCommentaire;
		this.retardCommentaire = retardCommentaire;
		this.sanctionsVisible = sanctionsVisible;
		this.sanctionParQui = sanctionParQui;
		this.sanctionCommentaire = sanctionCommentaire;
		this.encouragementsVisible = encouragementsVisible;
		this.encouragementParQui = encouragementParQui;
		this.encouragementCommentaire = encouragementCommentaire;
	}
	public boolean isJustificationEnLigne() {
		return justificationEnLigne;
	}
	public boolean isAbsenceCommentaire() {
		return absenceCommentaire;
	}
	public boolean isRetardCommentaire() {
		return retardCommentaire;
	}
	public boolean isSanctionsVisible() {
		return sanctionsVisible;
	}
	public boolean isSanctionParQui() {
		return sanctionParQui;
	}
	public boolean isSanctionCommentaire() {
		return sanctionCommentaire;
	}
	public boolean isEncouragementsVisible() {
		return encouragementsVisible;
	}
	public boolean isEncouragementParQui() {
		return encouragementParQui;
	}
	public boolean isEncouragementCommentaire() {
		return encouragementCommentaire;
	}
	
}
