package fr.benco11.jlibecoledirecte.lib.grades;

import fr.benco11.jlibecoledirecte.api.grades.Teacher;

public record DefaultTeacher(int id, String name) implements Teacher {
}
