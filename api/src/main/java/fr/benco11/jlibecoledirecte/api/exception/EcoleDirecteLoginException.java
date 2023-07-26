package fr.benco11.jlibecoledirecte.api.exception;

public class EcoleDirecteLoginException extends EcoleDirecteException {
    private static final String DEFAULT_MESSAGE = "Erreur lors de la connexion";

    public EcoleDirecteLoginException(int httpCode, int responseCode, String message) {
        super(httpCode, responseCode, DEFAULT_MESSAGE, message);
    }

    public EcoleDirecteLoginException(Throwable cause) {
        super(cause, DEFAULT_MESSAGE);
    }

    public EcoleDirecteLoginException() {
        super(DEFAULT_MESSAGE);
    }
}
