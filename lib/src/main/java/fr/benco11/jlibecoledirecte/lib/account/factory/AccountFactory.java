package fr.benco11.jlibecoledirecte.lib.account.factory;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.AccountImplementation;
import fr.benco11.jlibecoledirecte.lib.account.BasicAccount;
import fr.benco11.jlibecoledirecte.lib.exception.runtime.EcoleDirecteAccountImplementationException;
import java.time.Duration;
import java.util.List;

public interface AccountFactory {
    static AccountFactory defaultFactory(AccountImplementation implementation) {
        return switch (implementation.type()) {
            case DEFAULT -> new DefaultAccountFactory();
            case CACHED, PRE_LOAD_CACHE -> {
                if (implementation.args().length < 1 || !(implementation.args()[0] instanceof Duration)) {
                    throw new EcoleDirecteAccountImplementationException();
                }
                yield new CachedAccountFactory(
                        implementation.type() == AccountImplementation.ImplementationType.PRE_LOAD_CACHE,
                        (Duration) implementation.args()[0]);
            }
        };
    }

    BasicAccount getAccount(
            AccountType accountType,
            List<EcoleDirecteModule> modules,
            PersonalDetails personalDetails,
            UserProfile userProfile,
            SessionContext context);
}
