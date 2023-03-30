package fr.benco11.jlibecoledirecte.api.exception.runtime;

public abstract class EcoleDirecteRuntimeException extends RuntimeException {
    protected final int code;
    protected final String message;

    public EcoleDirecteRuntimeException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
