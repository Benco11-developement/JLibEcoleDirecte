package fr.benco11.jlibecoledirecte.api.grades;

import java.util.Date;

public interface Mark {
    double coef();

    double outOf();

    Date entryDate();

    double user();

    double schoolClass();

    double schoolClassMin();

    double schoolClassMax();

    boolean isValueBased();
}
