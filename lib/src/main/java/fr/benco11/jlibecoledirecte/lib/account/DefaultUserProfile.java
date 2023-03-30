package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.UserProfile;

import java.net.URL;

public record DefaultUserProfile(long id, String email, String password, String username, String phone,
                                 URL pictureURL) implements UserProfile {
}
