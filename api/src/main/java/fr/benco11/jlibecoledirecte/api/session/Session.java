package fr.benco11.jlibecoledirecte.api.session;

import fr.benco11.jlibecoledirecte.api.account.Account;
import java.time.LocalDateTime;

public interface Session {
    SessionContext context();

    Account account();

    boolean isValid();

    LocalDateTime lastLogin();
}
