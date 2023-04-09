package fr.benco11.jlibecoledirecte.api.grades;

import java.time.LocalDate;
import java.util.Optional;

public interface Mark {
    double coef();

    double outOf();

    Optional<LocalDate> entryDate();

    double user();

    double schoolClass();

    double schoolClassMin();

    double schoolClassMax();

    boolean isValueBased();
}
