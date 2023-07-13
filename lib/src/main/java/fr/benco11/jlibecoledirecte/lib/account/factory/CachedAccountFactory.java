package fr.benco11.jlibecoledirecte.lib.account.factory;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.BasicAccount;
import fr.benco11.jlibecoledirecte.lib.account.CachedStudentAccount;
import fr.benco11.jlibecoledirecte.lib.exception.runtime.EcoleDirecteAccountImplementationException;
import java.time.Duration;
import java.util.List;

public class CachedAccountFactory implements AccountFactory {
    private final Duration cacheTimeout;
    private final boolean preload;

    public CachedAccountFactory(boolean preload, Duration cacheTimeout) {
        this.preload = preload;
        this.cacheTimeout = cacheTimeout;
    }

    @Override
    public BasicAccount getAccount(
            AccountType accountType,
            List<EcoleDirecteModule> modules,
            PersonalDetails personalDetails,
            UserProfile userProfile,
            SessionContext context) {

        // TODO autres implémentations
        try {
            return switch (accountType) {
                case STUDENT -> new CachedStudentAccount(
                        accountType, modules, personalDetails, userProfile, context, preload, cacheTimeout);
                case FAMILY -> null;
                case TEACHER -> null;
                case STAFF -> null;
            };
        } catch (Exception e) {
            throw new EcoleDirecteAccountImplementationException();
        }
    }
}
