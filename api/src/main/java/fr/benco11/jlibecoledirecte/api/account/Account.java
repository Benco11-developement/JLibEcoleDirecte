package fr.benco11.jlibecoledirecte.api.account;

import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import java.util.List;

public interface Account {
    SessionContext sessionContext();

    UserProfile userProfile();

    PersonalDetails personalDetails();

    List<EcoleDirecteModule> modules();

    AccountType accountType();
}
