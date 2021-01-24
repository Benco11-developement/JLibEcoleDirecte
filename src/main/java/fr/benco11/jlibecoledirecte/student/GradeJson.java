package fr.benco11.jlibecoledirecte.student;

public class GradeJson {
	private String token;
	private GradeData data;
	private int code;

	
	public int getCode() {
		return code;
	}

	/**
	 * 
	 * @param token le token de session
	 * @param data le contenu de la réponse
	 */
	
	public GradeJson(String token, GradeData data) {
		this.token = token;
		this.data = data;
	}

	/**
	 * Renvoie le token
	 * @return le token de la session
	 */
	
	public String getToken() {
		return token;
	}

	/**
	 * Renvoie le <code>GradeData</code>
	 * @return le contenu de la réponse sous forme d'un objet <code>GradeData</code>
	 */
	
	public GradeData getData() {
		return data;
	}
	
	
}
