package fr.benco11.jlibecoledirecte.api.user;

import java.util.Map;

public interface Module {
    String code();

    boolean enabled();

    int order();

    Map<String, Boolean> params();

}
