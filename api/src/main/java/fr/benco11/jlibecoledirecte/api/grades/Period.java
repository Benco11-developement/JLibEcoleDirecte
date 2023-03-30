package fr.benco11.jlibecoledirecte.api.grades;

import java.util.List;

public interface Period {
    String id();

    String code();

    String name();

    boolean annual();

    java.time.Period date();

    boolean mockExam();

    boolean ended();

    Mark average();

    Teacher formTeacher();

    String formTeacherComment();

    String decision();

    String schoolClassComment();

    List<Discipline> disciplines();
}
