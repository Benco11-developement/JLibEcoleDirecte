package fr.benco11.jlibecoledirecte.api.grades;

import java.util.List;

public interface Discipline {
    int id();

    int groupId();

    int row();

    int headcount();

    String code();

    String subCode();

    String name();

    Mark average();

    List<String> comments();

    boolean isSubDiscipline();

    boolean isGroupDiscipline();

    boolean isOptional();

    List<Teacher> teachers();

    List<Assignment> assignments();
}
