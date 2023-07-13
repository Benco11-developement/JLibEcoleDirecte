package fr.benco11.jlibecoledirecte.lib.account.factory;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.BasicAccount;
import fr.benco11.jlibecoledirecte.lib.account.DefaultStudentAccount;
import java.util.List;

public class DefaultAccountFactory implements AccountFactory {
    @Override
    public BasicAccount getAccount(
            AccountType accountType,
            List<EcoleDirecteModule> modules,
            PersonalDetails personalDetails,
            UserProfile userProfile,
            SessionContext context) {

        // TODO autres implémentations
        return switch (accountType) {
            case STUDENT -> new DefaultStudentAccount(accountType, modules, personalDetails, userProfile, context);
            case FAMILY -> null;
            case TEACHER -> null;
            case STAFF -> null;
        };
    }
}
