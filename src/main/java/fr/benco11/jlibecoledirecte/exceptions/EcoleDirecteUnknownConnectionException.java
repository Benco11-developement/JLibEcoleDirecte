package fr.benco11.jlibecoledirecte.exceptions;

public class EcoleDirecteUnknownConnectionException extends Exception {
	private static final long serialVersionUID = -4682370957544062464L;
	@Override
	public String getMessage() {
		return "Une erreur inconnue s'est produite";
	}
}
