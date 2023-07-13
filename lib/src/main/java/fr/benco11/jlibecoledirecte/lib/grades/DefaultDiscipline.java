package fr.benco11.jlibecoledirecte.lib.grades;

import fr.benco11.jlibecoledirecte.api.grades.Assignment;
import fr.benco11.jlibecoledirecte.api.grades.Discipline;
import fr.benco11.jlibecoledirecte.api.grades.Mark;
import fr.benco11.jlibecoledirecte.api.grades.Teacher;
import java.util.List;

public record DefaultDiscipline(
        int id,
        int groupId,
        int row,
        int headcount,
        String code,
        String subCode,
        String name,
        Mark average,
        List<String> comments,
        boolean isSubDiscipline,
        boolean isGroupDiscipline,
        boolean isOptional,
        List<Teacher> teachers,
        List<Assignment> assignments)
        implements Discipline {}
