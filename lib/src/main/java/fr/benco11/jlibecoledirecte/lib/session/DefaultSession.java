package fr.benco11.jlibecoledirecte.lib.session;

import fr.benco11.jlibecoledirecte.api.account.Account;
import fr.benco11.jlibecoledirecte.api.session.Session;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class DefaultSession implements Session {

    private final SessionContext sessionContext;
    private final Instant endSession;
    private final LocalDateTime lastLogin;
    private final Account account;

    public DefaultSession(SessionContext sessionContext, Duration sessionValidity, LocalDateTime lastLogin,
                          Account account) {
        this.sessionContext = sessionContext;
        this.endSession = Instant.now()
                .plus(sessionValidity);
        this.lastLogin = lastLogin;
        this.account = account;
    }

    public boolean isValid() {
        return endSession.isAfter(Instant.now());
    }

    @Override
    public LocalDateTime lastLogin() {
        return lastLogin;
    }

    @Override
    public SessionContext context() {
        return sessionContext;
    }

    @Override
    public Account account() {
        return account;
    }


}
