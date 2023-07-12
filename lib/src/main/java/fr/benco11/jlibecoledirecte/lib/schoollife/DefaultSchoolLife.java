package fr.benco11.jlibecoledirecte.lib.schoollife;

import fr.benco11.jlibecoledirecte.api.schoollife.SchoolEvent;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;

import java.util.List;
import java.util.Map;

public record DefaultSchoolLife(List<SchoolEvent> events, Map<String, Boolean> parameters) implements SchoolLife {
}
