package fr.benco11.jlibecoledirecte.lib.account.factory;

import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.AccountData;
import fr.benco11.jlibecoledirecte.lib.account.BasicAccount;
import fr.benco11.jlibecoledirecte.lib.account.DefaultStudentAccount;
import fr.benco11.jlibecoledirecte.lib.utils.HttpService;

public class DefaultAccountFactory implements AccountFactory {
    @Override
    public BasicAccount getAccount(AccountData accountData, SessionContext context, HttpService httpService) {

        // TODO autres implémentations
        return switch (accountData.accountType()) {
            case STUDENT -> new DefaultStudentAccount(accountData, context, httpService);
            case FAMILY -> null;
            case TEACHER -> null;
            case STAFF -> null;
        };
    }
}
