package fr.benco11.jlibecoledirecte.lib.grades;

import fr.benco11.jlibecoledirecte.api.grades.Discipline;
import fr.benco11.jlibecoledirecte.api.grades.Mark;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.grades.Teacher;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record DefaultPeriod(
        String id,
        String code,
        String name,
        boolean annual,
        java.time.Period date,
        boolean mockExam,
        boolean ended,
        Mark average,
        Teacher formTeacher,
        String formTeacherComment,
        String schoolClassComment,
        String conferenceDecision,
        Optional<LocalDateTime> conferenceDate,
        List<Discipline> disciplines)
        implements Period {}
