package fr.benco11.jlibecoledirecte.lib.grades;

import fr.benco11.jlibecoledirecte.api.grades.Mark;
import java.time.LocalDate;
import java.util.Optional;

public record DefaultMark(
        double coef,
        double outOf,
        Optional<LocalDate> entryDate,
        double user,
        double schoolClass,
        double schoolClassMin,
        double schoolClassMax,
        boolean isValueBased)
        implements Mark {}
