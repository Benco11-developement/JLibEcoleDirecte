package fr.benco11.jlibecoledirecte.api.exception;

public class LoginEcoleDirecteException extends EcoleDirecteException {
    public LoginEcoleDirecteException(int code, String message) {
        super(code, message);
    }

    public LoginEcoleDirecteException() {
        super();
    }

    @Override
    public String getMessage() {
        return (message != null) ? "Erreur lors de la connexion (code " + code + ") : " + message
                                 : "Erreur lors de la connexion !";
    }
}
