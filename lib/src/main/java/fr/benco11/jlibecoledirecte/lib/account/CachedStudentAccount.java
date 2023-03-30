package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import fr.benco11.jlibecoledirecte.api.exception.GradesFetchEcoleDirecteException;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.utils.CacheValue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public final class CachedStudentAccount extends DefaultStudentAccount {
    private final Duration cacheTimeout;
    private final Map<CacheKey, CacheValue> cache = Collections.synchronizedMap(new EnumMap<>(CacheKey.class));

    public CachedStudentAccount(AccountType accountType,
                                List<EcoleDirecteModule> modules,
                                PersonalDetails personalDetails,
                                UserProfile userProfile,
                                SessionContext context,
                                boolean preload,
                                Duration cacheTimeout) throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException {
        super(accountType, modules, personalDetails, userProfile, context);
        this.cacheTimeout = cacheTimeout;

        if (preload) {
            periods();
        }
    }

    @Override
    public List<Period> periods() throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException {
        Optional<Period[]> periodsCache = loadCache(CacheKey.PERIODS, Period[].class);
        if (periodsCache.isPresent()) return Arrays.asList(periodsCache.get());
        List<Period> periods = super.periods();
        cache.put(CacheKey.PERIODS, new CacheValue(cacheTimeout(), Optional.of(periods.toArray(new Period[0]))));
        return periods;
    }

    private <T> Optional<T> loadCache(CacheKey key, Class<T> tClass) {
        return cache.getOrDefault(key, new CacheValue(cacheTimeout(), Optional.empty())).value(tClass);
    }

    private Instant cacheTimeout() {
        return Instant.now().plus(cacheTimeout);
    }

    private enum CacheKey {
        PERIODS
    }
}
