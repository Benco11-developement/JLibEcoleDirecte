package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;

public record DefaultPersonalDetails(
        String firstName,
        String lastName,
        String sex,
        String schoolName,
        String currentSchoolYear,
        String className,
        String classCode)
        implements PersonalDetails {}
