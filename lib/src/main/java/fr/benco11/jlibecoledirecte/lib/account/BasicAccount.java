package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.*;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.http.HttpService;
import fr.benco11.jlibecoledirecte.lib.json.JsonService;
import fr.benco11.jlibecoledirecte.lib.session.SessionServices;
import java.util.List;

public abstract sealed class BasicAccount implements Account permits DefaultStudentAccount {
    protected final SessionContext context;
    protected final AccountType accountType;
    protected final List<EcoleDirecteModule> modules;
    protected final PersonalDetails personalDetails;
    protected final UserProfile userProfile;
    protected final HttpService httpService;
    protected final JsonService jsonService;

    protected BasicAccount(AccountData accountData, SessionContext context, SessionServices sessionServices) {
        this.accountType = accountData.accountType();
        this.modules = accountData.modules();
        this.personalDetails = accountData.personalDetails();
        this.userProfile = accountData.userProfile();
        this.context = context;
        this.httpService = sessionServices.httpService();
        this.jsonService = sessionServices.jsonService();
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
    public boolean isModuleEnabled(ModuleType type) {
        return modules.stream()
                .anyMatch(module -> module.enabled() && module.code().equalsIgnoreCase(type.code()));
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
