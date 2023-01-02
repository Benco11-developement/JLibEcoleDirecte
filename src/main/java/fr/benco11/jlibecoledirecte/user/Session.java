package fr.benco11.jlibecoledirecte.user;

import java.time.Duration;
import java.time.Instant;

public class Session {
    private String token;
    private String idLogin;
    private Instant endSession;

    public Session(String token, String idLogin, Duration sessionValidity) {
        this.token = token;
        this.idLogin = idLogin;
        this.endSession = Instant.now().plus(sessionValidity);
    }

    public boolean isValid() {
        return endSession.isAfter(Instant.now());
    }
}
