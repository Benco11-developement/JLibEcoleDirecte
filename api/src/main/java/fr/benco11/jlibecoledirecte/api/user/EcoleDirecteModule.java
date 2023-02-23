package fr.benco11.jlibecoledirecte.api.user;

import java.util.Map;

public interface EcoleDirecteModule {
    String code();

    boolean enabled();

    int order();

    Map<String, Object> params();

}
