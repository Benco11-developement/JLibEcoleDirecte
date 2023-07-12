package fr.benco11.jlibecoledirecte.api.schoollife;

import java.util.List;
import java.util.Map;

public interface SchoolLife {
    List<SchoolEvent> events();

    Map<String, Boolean> parameters();
}
