package fr.benco11.jlibecoledirecte.api.exception;

public class EcoleDirecteSchoolLifeFetchException extends EcoleDirecteException {

    public EcoleDirecteSchoolLifeFetchException(int code, String message) {
        super(code, message);
    }

    public EcoleDirecteSchoolLifeFetchException() {
        super();
    }

    @Override
    public String getMessage() {
        return (message != null) ? "Erreur lors de la récupération des données de la vie scolaire (code " + code + ") : " + message
                : "Erreur lors de la récupération des données de la vie scolaire !";
    }
}
