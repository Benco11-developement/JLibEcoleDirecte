package fr.benco11.jlibecoledirecte.lib.account.factory;

import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.AccountData;
import fr.benco11.jlibecoledirecte.lib.account.BasicAccount;
import fr.benco11.jlibecoledirecte.lib.account.CachedStudentAccount;
import fr.benco11.jlibecoledirecte.lib.exception.runtime.EcoleDirecteAccountImplementationException;
import fr.benco11.jlibecoledirecte.lib.session.SessionServices;
import java.time.Duration;

public class CachedAccountFactory implements AccountFactory {
    private final Duration cacheTimeout;
    private final boolean preload;

    public CachedAccountFactory(boolean preload, Duration cacheTimeout) {
        this.preload = preload;
        this.cacheTimeout = cacheTimeout;
    }

    @Override
    public BasicAccount getAccount(AccountData accountData, SessionContext context, SessionServices sessionServices) {

        // TODO autres implémentations
        try {
            return switch (accountData.accountType()) {
                case STUDENT -> new CachedStudentAccount(accountData, context, preload, cacheTimeout, sessionServices);
                case FAMILY -> null;
                case TEACHER -> null;
                case STAFF -> null;
            };
        } catch (Exception e) {
            throw new EcoleDirecteAccountImplementationException();
        }
    }
}
