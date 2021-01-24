package fr.benco11.jlibecoledirecte.exceptions;

public class EcoleDirecteAccountTypeException extends Exception {
	private static final long serialVersionUID = 907571884065232447L;
	private int id;
	public EcoleDirecteAccountTypeException(int id) {
		this.id = id;
	}
	
	@Override
	public String getMessage() {
		return "Le compte utilisé n'est pas un compte " + ((id == 1) ? "élève" : "famille");
	}
	
}
