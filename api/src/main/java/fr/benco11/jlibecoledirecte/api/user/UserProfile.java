package fr.benco11.jlibecoledirecte.api.user;

import java.net.URL;

public interface UserProfile {
    String email();

    String username();

    String password();

    String phone();

    URL pictureURL();
}
