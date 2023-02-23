package fr.benco11.jlibecoledirecte.lib.user;

import fr.benco11.jlibecoledirecte.api.user.PersonalDetails;

public record DefaultPersonalDetails(String firstName, String lastName, String sex,
                                     String schoolName, String currentSchoolYear, String className,
                                     String classCode) implements PersonalDetails {
}
