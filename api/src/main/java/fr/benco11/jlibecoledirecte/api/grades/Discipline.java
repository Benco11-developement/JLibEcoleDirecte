package fr.benco11.jlibecoledirecte.api.grades;

import java.util.List;

public interface Discipline {
    int id();

    int row();

    String code();

    String subCode();

    String name();

    Mark average();

    String comment();

    boolean isSubDiscipline();

    List<Teacher> teachers();

    List<Assignment> assignments();
}
