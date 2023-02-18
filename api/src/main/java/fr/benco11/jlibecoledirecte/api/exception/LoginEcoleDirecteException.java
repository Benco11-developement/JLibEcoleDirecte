package fr.benco11.jlibecoledirecte.api.exception;

public class LoginEcoleDirecteException extends EcoleDirecteException {
    public LoginEcoleDirecteException(int code, String message) {
        super(code, message);
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la connexion (code " + code + ") : " + message;
    }
}
