package fr.benco11.jlibecoledirecte.api.schoollife;

import java.time.LocalDate;

public interface SchoolEvent {
    long id();

    String studentName();

    boolean isSanction();

    String type();

    LocalDate date();

    String displayDate();

    String description();

    String reason();

    boolean justified();

    String by();

    String comment();

    String justificationType();

    String toDo();

    LocalDate toDoDate();
}
