package fr.benco11.jlibecoledirecte.exceptions;

public class EcoleDirecteIOException extends Exception {
	private static final long serialVersionUID = 1645146293682048190L;
	
	@Override
	public String getMessage() {
		return "Une erreur de réseau s'est produite !";
	}
	
}
