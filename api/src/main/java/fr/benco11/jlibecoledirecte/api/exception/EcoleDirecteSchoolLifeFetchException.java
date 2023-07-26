package fr.benco11.jlibecoledirecte.api.exception;

public class EcoleDirecteSchoolLifeFetchException extends EcoleDirecteException {
    private static final String DEFAULT_MESSAGE = "Erreur lors de la récupération des données de la vie scolaire";

    public EcoleDirecteSchoolLifeFetchException(int httpCode, int responseCode, String message) {
        super(httpCode, responseCode, DEFAULT_MESSAGE, message);
    }

    public EcoleDirecteSchoolLifeFetchException(Throwable cause) {
        super(cause, DEFAULT_MESSAGE);
    }

    public EcoleDirecteSchoolLifeFetchException() {
        super(DEFAULT_MESSAGE);
    }
}
