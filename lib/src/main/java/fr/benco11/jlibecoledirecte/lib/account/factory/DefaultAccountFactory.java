package fr.benco11.jlibecoledirecte.lib.account.factory;

import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.AccountData;
import fr.benco11.jlibecoledirecte.lib.account.BasicAccount;
import fr.benco11.jlibecoledirecte.lib.account.DefaultStudentAccount;
import fr.benco11.jlibecoledirecte.lib.session.SessionServices;

public class DefaultAccountFactory implements AccountFactory {
    @Override
    public BasicAccount getAccount(AccountData accountData, SessionContext context, SessionServices sessionServices) {

        // TODO autres implémentations
        return switch (accountData.accountType()) {
            case STUDENT -> new DefaultStudentAccount(accountData, context, sessionServices);
            case FAMILY -> null;
            case TEACHER -> null;
            case STAFF -> null;
        };
    }
}
