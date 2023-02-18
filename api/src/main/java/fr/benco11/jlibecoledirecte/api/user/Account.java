package fr.benco11.jlibecoledirecte.api.user;

import java.util.List;

public interface Account {
    String currentSchoolYear();

    User user();

    List<Module> modules();

    AccountType accountType();
}
