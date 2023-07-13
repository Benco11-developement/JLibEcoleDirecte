package fr.benco11.jlibecoledirecte.lib.schoollife;

import fr.benco11.jlibecoledirecte.api.schoollife.SchoolEvent;
import java.time.LocalDate;

public record DefaultSchoolEvent(
        long id,
        String studentName,
        boolean isSanction,
        String type,
        LocalDate date,
        String displayDate,
        String description,
        String reason,
        boolean justified,
        String by,
        String comment,
        String justificationType,
        String toDo,
        LocalDate toDoDate)
        implements SchoolEvent {}
