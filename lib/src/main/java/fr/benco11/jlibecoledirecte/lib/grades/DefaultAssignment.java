package fr.benco11.jlibecoledirecte.lib.grades;

import fr.benco11.jlibecoledirecte.api.grades.Assignment;
import fr.benco11.jlibecoledirecte.api.grades.Mark;

import java.time.LocalDate;

public record DefaultAssignment(long id, String name, String periodCode, String disciplineCode,
                                String disciplineLabel, String subDisciplineCode,
                                String assignmentType, String comment, LocalDate date,
                                Mark mark) implements Assignment {
}
