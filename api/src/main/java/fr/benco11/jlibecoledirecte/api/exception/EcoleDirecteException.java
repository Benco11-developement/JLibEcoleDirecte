package fr.benco11.jlibecoledirecte.api.exception;

public abstract class EcoleDirecteException extends Exception {
    protected final int httpCode;
    protected final int responseCode;
    protected final String defaultMessage;
    protected final String specificMessage;

    protected EcoleDirecteException(int httpCode, int responseCode, String defaultMessage, String specificMessage) {
        this.httpCode = httpCode;
        this.defaultMessage = defaultMessage;
        this.specificMessage = specificMessage;
        this.responseCode = responseCode;
    }

    protected EcoleDirecteException(int httpCode, String defaultMessage) {
        this(httpCode, -1, defaultMessage, null);
    }

    protected EcoleDirecteException(String defaultMessage) {
        this(-1, defaultMessage);
    }

    protected EcoleDirecteException(Throwable cause, String defaultMessage) {
        super(cause);
        this.defaultMessage = defaultMessage;
        httpCode = -1;
        specificMessage = null;
        responseCode = -1;
    }

    @Override
    public String getMessage() {
        StringBuilder response = new StringBuilder();
        if (defaultMessage != null && !defaultMessage.isEmpty()) {
            response.append(defaultMessage);
            if (httpCode != -1) {
                response.append(" (HTTP ").append(httpCode);
                if (responseCode != -1) {
                    response.append(", code ").append(responseCode);
                }
                response.append(")");
            }
            if (specificMessage != null && !specificMessage.isEmpty()) {
                response.append(" : ").append(specificMessage);
            }
        } else {
            response.append(super.getMessage());
        }
        return response.toString();
    }
}
