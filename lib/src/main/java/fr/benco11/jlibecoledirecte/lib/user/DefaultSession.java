package fr.benco11.jlibecoledirecte.lib.user;

import fr.benco11.jlibecoledirecte.api.session.Session;
import fr.benco11.jlibecoledirecte.api.user.Account;
import fr.benco11.jlibecoledirecte.lib.dto.output.login.AccountDTO;
import fr.benco11.jlibecoledirecte.lib.dto.output.login.SettingsDTO;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class DefaultSession implements Session {
    private String token;
    private long idLogin;
    private Instant endSession;
    private Date lastLogin;
    private Account account;

    public DefaultSession(String token, long idLogin, Duration sessionValidity,
            AccountDTO accountDTO, SettingsDTO settingsDTO) {
        this.token = token;
        this.idLogin = idLogin;
        this.endSession = Instant.now()
                                 .plus(sessionValidity);

    }

    public boolean isValid() {
        return endSession.isAfter(Instant.now());
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
