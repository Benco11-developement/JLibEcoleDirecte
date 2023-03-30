package fr.benco11.jlibecoledirecte.api.grades;

import java.util.Date;

public interface Assignment {
    long id();

    String name();

    String periodCode();

    String disciplineCode();

    String disciplineLabel();

    String subDisciplineCode();

    String assignmentType();

    String comment();

    Date date();

    Mark mark();

}
