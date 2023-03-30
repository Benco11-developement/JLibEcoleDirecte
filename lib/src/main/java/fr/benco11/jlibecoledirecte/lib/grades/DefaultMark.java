package fr.benco11.jlibecoledirecte.lib.grades;

import fr.benco11.jlibecoledirecte.api.grades.Mark;

import java.util.Date;

public record DefaultMark(double coef, double outOf, Date entryDate, double user,
                          double schoolClass,
                          double schoolClassMin, double schoolClassMax,
                          boolean isValueBased) implements Mark {
}
