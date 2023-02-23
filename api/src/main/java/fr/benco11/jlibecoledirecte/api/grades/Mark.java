package fr.benco11.jlibecoledirecte.api.grades;

import java.util.Date;

public interface Mark {
    int coef();

    double outOf();

    Date entryDate();

    double user();

    double schoolClassGrade();

    double schoolClassMin();

    double schoolClassMax();
}
