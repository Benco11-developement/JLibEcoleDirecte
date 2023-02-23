package fr.benco11.jlibecoledirecte.lib.user;

import fr.benco11.jlibecoledirecte.api.session.Session;
import fr.benco11.jlibecoledirecte.api.user.Account;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class DefaultSession implements Session {
    private final String token;
    private final long idLogin;
    private Instant endSession;
    private Date lastLogin;
    private Account account;

    public DefaultSession(String token, long idLogin, Duration sessionValidity, Date lastLogin,
            Account account) {
        this.token = token;
        this.idLogin = idLogin;
        this.endSession = Instant.now()
                                 .plus(sessionValidity);
        this.lastLogin = lastLogin;
        this.account = account;
    }

    public boolean isValid() {
        return endSession.isAfter(Instant.now());
    }

    @Override
    public Date lastLogin() {
        return lastLogin;
    }

    @Override
    public long idLogin() {
        return idLogin;
    }

    @Override
    public Account account() {
        return account;
    }


}
