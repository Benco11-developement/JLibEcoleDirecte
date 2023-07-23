package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import java.util.List;

public record AccountData(
        AccountType accountType,
        List<EcoleDirecteModule> modules,
        PersonalDetails personalDetails,
        UserProfile userProfile) {}
