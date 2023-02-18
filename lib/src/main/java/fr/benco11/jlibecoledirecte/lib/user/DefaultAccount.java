package fr.benco11.jlibecoledirecte.lib.user;


import fr.benco11.jlibecoledirecte.api.user.Account;
import fr.benco11.jlibecoledirecte.api.user.AccountType;
import fr.benco11.jlibecoledirecte.api.user.Module;
import fr.benco11.jlibecoledirecte.api.user.User;

import java.util.List;

public class DefaultAccount implements Account {
    private long idLogin;
    private AccountType accountType;
    private List<Module> modules;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public String currentSchoolYear() {
        return null;
    }

    @Override
    public User user() {
        return null;
    }

    @Override
    public List<Module> modules() {
        return modules;
    }

    @Override
    public AccountType accountType() {
        return accountType;
    }
}
