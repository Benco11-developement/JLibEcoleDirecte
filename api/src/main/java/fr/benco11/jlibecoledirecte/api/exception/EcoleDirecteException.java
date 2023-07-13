package fr.benco11.jlibecoledirecte.api.exception;

public abstract class EcoleDirecteException extends Exception {
    protected int code;
    protected String message;

    public EcoleDirecteException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public EcoleDirecteException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
