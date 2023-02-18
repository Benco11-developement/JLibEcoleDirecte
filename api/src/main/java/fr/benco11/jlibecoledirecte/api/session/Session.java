package fr.benco11.jlibecoledirecte.api.session;

import fr.benco11.jlibecoledirecte.api.user.Account;

public interface Session {
    long idLogin();

    Account account();

    boolean isValid();
}
