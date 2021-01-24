package fr.benco11.jlibecoledirecte;

import java.util.Set;

public class Module {
	private String code;
	private boolean enable;
	private int ordre;
	private Set<String> params;
	
	/**
	 * 
	 * @param code le nom de code du module
	 * @param enable <code>true</code> si le module est activé
	 * @param ordre l'ordre d'apparition du module
	 * @param params les paramètres du module
	 */
	public Module(String code, boolean enable, int ordre, Set<String> params) {
		super();
		this.code = code;
		this.enable = enable;
		this.ordre = ordre;
		this.params = params;
	}
	
	/**
	 * Renvoie le code du module
	 * @return le code du module
	 */
	
	public String getCode() {
		return code;
	}
	
	/**
	 * Renvoie si le module est activé ou non
	 * @return <code>true</code> si le module est activé
	 */
	
	public boolean isEnable() {
		return enable;
	}
	
	/**
	 * Renvoie l'ordre d'apparition du module
	 * @return l'ordre d'apparition du module
	 */
	
	public int getOrdre() {
		return ordre;
	}
	
	/**
	 * Renvoie tous les paramètres
	 * @return le <code>Set</code> de tous les paramètres
	 */
	
	public Set<String> getParams() {
		return params;
	}
}
