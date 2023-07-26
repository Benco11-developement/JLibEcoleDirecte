package fr.benco11.jlibecoledirecte.api.exception;

public class EcoleDirecteGradesFetchException extends EcoleDirecteException {
    public EcoleDirecteGradesFetchException(int code, String message) {
        super(code, message);
    }

    public EcoleDirecteGradesFetchException(Throwable cause) {
        super(cause);
    }

    public EcoleDirecteGradesFetchException() {
        super();
    }

    @Override
    public String getMessage() {
        return (message != null)
                ? "Erreur lors de la récupération des notes (http " + code + ") : " + message
                : "Erreur lors de la récupération des notes !";
    }
}
