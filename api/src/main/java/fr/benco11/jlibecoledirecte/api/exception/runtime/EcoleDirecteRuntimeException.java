package fr.benco11.jlibecoledirecte.api.exception.runtime;

public abstract class EcoleDirecteRuntimeException extends RuntimeException {
    protected final String message;

    protected EcoleDirecteRuntimeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
