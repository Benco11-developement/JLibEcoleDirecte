package fr.benco11.jlibecoledirecte.api.exception;

public class GradesFetchEcoleDirecteException extends EcoleDirecteException {
    public GradesFetchEcoleDirecteException(int code, String message) {
        super(code, message);
    }

    public GradesFetchEcoleDirecteException() {
        super();
    }

    @Override
    public String getMessage() {
        return (message != null) ? "Erreur lors de la récupération des notes (code " + code + ") : " + message
                : "Erreur lors de la récupération des notes !";
    }
}
