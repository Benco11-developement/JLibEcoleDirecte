package fr.benco11.jlibecoledirecte;

import fr.benco11.jlibecoledirecte.user.Session;

import java.time.Duration;

public class EcoleDirecteSession {
    public static final Duration DEFAULT_SESSION_DURATION = Duration.ofHours(1);
    private final String username;
    private final String password;
    private final Duration sessionDuration;
    private Session session;

    public EcoleDirecteSession(String username, String password, Duration sessionDuration) {
        this.username = username;
        this.password = password;
        this.sessionDuration = sessionDuration;
    }
    public EcoleDirecteSession(String username, String password) {
        this(username, password, DEFAULT_SESSION_DURATION);
    }


}
