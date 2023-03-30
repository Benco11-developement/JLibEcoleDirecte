package fr.benco11.jlibecoledirecte.api.account;

import java.net.URL;

public interface UserProfile {
    long id();

    String email();

    String username();

    String password();

    String phone();

    URL pictureURL();
}
