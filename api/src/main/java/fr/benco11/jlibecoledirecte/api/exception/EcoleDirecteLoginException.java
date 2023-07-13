package fr.benco11.jlibecoledirecte.api.exception;

public class EcoleDirecteLoginException extends EcoleDirecteException {
    public EcoleDirecteLoginException(int code, String message) {
        super(code, message);
    }

    public EcoleDirecteLoginException() {
        super();
    }

    @Override
    public String getMessage() {
        return (message != null)
                ? "Erreur lors de la connexion (code " + code + ") : " + message
                : "Erreur lors de la connexion !";
    }
}
