package fr.benco11.jlibecoledirecte.exceptions;

public class EcoleDirectePeriodeException extends Exception {
	private static final long serialVersionUID = 9159578679217469400L;
	
	@Override
	public String getMessage() {
		return "Impossible de trouver le trimestre correspondant !";
	}
}
