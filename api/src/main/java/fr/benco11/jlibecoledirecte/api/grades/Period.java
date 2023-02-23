package fr.benco11.jlibecoledirecte.api.grades;

import java.util.List;

public interface Period {
    String id();

    String code();

    String name();

    boolean isAnnual();

    java.time.Period periodDate();

    boolean mockExam();

    boolean ended();

    Mark generalAverage();

    Teacher formTeacher();

    String formTeacherComment();

    String decision();

    String schoolClassComment();

    List<Discipline> disciplines();
}
