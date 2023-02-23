package fr.benco11.jlibecoledirecte.lib.user;

import fr.benco11.jlibecoledirecte.api.user.UserProfile;

import java.net.URL;

public record DefaultUserProfile(String email, String password, String username, String phone,
                                 URL pictureURL) implements UserProfile {
}
