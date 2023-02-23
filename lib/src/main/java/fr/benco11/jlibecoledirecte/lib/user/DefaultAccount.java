package fr.benco11.jlibecoledirecte.lib.user;


import fr.benco11.jlibecoledirecte.api.user.*;

import java.util.List;

public abstract sealed class DefaultAccount implements Account permits StudentAccount {
    protected long idLogin;
    protected AccountType accountType;
    protected List<EcoleDirecteModule> modules;
    protected PersonalDetails personalDetails;
    protected UserProfile userProfile;

    public DefaultAccount(long idLogin, AccountType accountType, List<EcoleDirecteModule> modules,
            PersonalDetails personalDetails, UserProfile userProfile) {
        this.idLogin = idLogin;
        this.accountType = accountType;
        this.modules = modules;
        this.personalDetails = personalDetails;
        this.userProfile = userProfile;
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
}
