package fr.benco11.jlibecoledirecte.lib.user;

import fr.benco11.jlibecoledirecte.api.user.AccountType;
import fr.benco11.jlibecoledirecte.api.user.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.user.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.user.UserProfile;

import java.util.List;

public final class StudentAccount extends DefaultAccount {
    public StudentAccount(long idLogin, AccountType accountType,
            List<EcoleDirecteModule> modules,
            PersonalDetails personalDetails,
            UserProfile userProfile) {
        super(idLogin, accountType, modules, personalDetails, userProfile);
    }
}
