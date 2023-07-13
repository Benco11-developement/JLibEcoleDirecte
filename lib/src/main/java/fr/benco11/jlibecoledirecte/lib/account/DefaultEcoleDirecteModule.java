package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import java.util.Map;

public record DefaultEcoleDirecteModule(String code, boolean enabled, int order, Map<String, Object> params)
        implements EcoleDirecteModule {}
