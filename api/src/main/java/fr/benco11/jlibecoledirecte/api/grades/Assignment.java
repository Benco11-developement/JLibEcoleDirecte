package fr.benco11.jlibecoledirecte.api.grades;

import java.util.Date;

public interface Assignment {
    long id();

    String name();

    String periodCode();

    String disciplineCode();

    Date date();

    Mark mark();

    boolean isValueBased();
}
