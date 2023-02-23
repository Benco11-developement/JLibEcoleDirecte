package fr.benco11.jlibecoledirecte.api.user;

import java.util.List;

public interface Account {

    UserProfile userProfile();

    PersonalDetails personalDetails();

    List<EcoleDirecteModule> modules();

    AccountType accountType();
}
