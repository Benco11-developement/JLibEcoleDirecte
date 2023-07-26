package fr.benco11.jlibecoledirecte.api.exception;

public abstract class EcoleDirecteException extends Exception {
    protected final int code;
    protected final String message;

    protected EcoleDirecteException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    protected EcoleDirecteException(Throwable cause) {
        super(cause);
        code = -1;
        message = null;
    }

    protected EcoleDirecteException() {
        this(-1, null);
    }

    @Override
    public String getMessage() {
        return (message != null) ? message : super.getMessage();
    }
}
