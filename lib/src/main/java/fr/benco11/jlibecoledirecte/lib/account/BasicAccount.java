package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.*;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import java.util.List;

public abstract sealed class BasicAccount implements Account permits DefaultStudentAccount {
    protected final SessionContext context;
    protected final AccountType accountType;
    protected final List<EcoleDirecteModule> modules;
    protected final PersonalDetails personalDetails;
    protected final UserProfile userProfile;

    protected BasicAccount(
            AccountType accountType,
            List<EcoleDirecteModule> modules,
            PersonalDetails personalDetails,
            UserProfile userProfile,
            SessionContext context) {
        this.accountType = accountType;
        this.modules = modules;
        this.personalDetails = personalDetails;
        this.userProfile = userProfile;
        this.context = context;
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
