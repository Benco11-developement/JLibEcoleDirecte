package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.*;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.utils.HttpService;
import java.util.List;

public abstract sealed class BasicAccount implements Account permits DefaultStudentAccount {
    protected final SessionContext context;
    protected final AccountType accountType;
    protected final List<EcoleDirecteModule> modules;
    protected final PersonalDetails personalDetails;
    protected final UserProfile userProfile;
    protected final HttpService httpService;

    protected BasicAccount(AccountData accountData, SessionContext context, HttpService httpService) {
        this.accountType = accountData.accountType();
        this.modules = accountData.modules();
        this.personalDetails = accountData.personalDetails();
        this.userProfile = accountData.userProfile();
        this.context = context;
        this.httpService = httpService;
    }

    @Override
    public UserProfile userProfile() {
        return userProfile;
    }

    @Override
    public PersonalDetails personalDetails() {
        return personalDetails;
    }

    @Override
    public List<EcoleDirecteModule> modules() {
        return modules;
    }

    @Override
    public AccountType accountType() {
        return accountType;
    }

    @Override
    public SessionContext sessionContext() {
        return context;
    }
}
