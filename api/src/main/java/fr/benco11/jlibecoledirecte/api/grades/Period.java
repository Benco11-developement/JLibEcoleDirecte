package fr.benco11.jlibecoledirecte.api.grades;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    String schoolClassComment();

    String conferenceDecision();

    Optional<LocalDateTime> conferenceDate();

    List<Discipline> disciplines();
}
