package fr.benco11.jlibecoledirecte.api.exception;

public class EcoleDirecteGradesFetchException extends EcoleDirecteException {
    private static final String DEFAULT_MESSAGE = "Erreur lors de la récupération des notes";

    public EcoleDirecteGradesFetchException(int httpCode, int responseCode, String message) {
        super(httpCode, responseCode, DEFAULT_MESSAGE, message);
    }

    public EcoleDirecteGradesFetchException(Throwable cause) {
        super(cause, DEFAULT_MESSAGE);
    }

    public EcoleDirecteGradesFetchException() {
        super(DEFAULT_MESSAGE);
    }
}
