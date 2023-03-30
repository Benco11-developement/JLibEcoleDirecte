package fr.benco11.jlibecoledirecte.api.session;

import fr.benco11.jlibecoledirecte.api.account.Account;

import java.util.Date;

public interface Session {
    SessionContext context();

    Account account();

    boolean isValid();

    Date lastLogin();
}
