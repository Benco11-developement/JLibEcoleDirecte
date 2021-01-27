package fr.benco11.jlibecoledirecte.exceptions;

public class EcoleDirecteLoginException extends Exception {
	private static final long serialVersionUID = -6805933911887646483L;
	private String message;
	
	public EcoleDirecteLoginException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
