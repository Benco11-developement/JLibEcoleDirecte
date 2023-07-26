package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteGradesFetchException;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteSchoolLifeFetchException;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.session.SessionServices;
import fr.benco11.jlibecoledirecte.lib.utils.CacheValue;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public final class CachedStudentAccount extends DefaultStudentAccount {
    private final Duration cacheTimeout;
    private final Map<CacheKey, CacheValue> cache = Collections.synchronizedMap(new EnumMap<>(CacheKey.class));

    public CachedStudentAccount(
            AccountData accountData,
            SessionContext context,
            boolean preload,
            Duration cacheTimeout,
            SessionServices sessionServices)
            throws EcoleDirecteGradesFetchException, EcoleDirecteSchoolLifeFetchException {
        super(accountData, context, sessionServices);
        this.cacheTimeout = cacheTimeout;

        if (preload) {
            periods();
            schoolLife();
        }
    }

    @Override
    public List<Period> periods() throws EcoleDirecteGradesFetchException {
        Optional<Period[]> periodsCache = loadCache(CacheKey.PERIODS, Period[].class);
        if (periodsCache.isPresent()) {
            return Arrays.asList(periodsCache.get());
        }
        List<Period> periods = super.periods();
        cache.put(CacheKey.PERIODS, new CacheValue(cacheTimeout(), Optional.of(periods.toArray(new Period[0]))));
        return periods;
    }

    @Override
    public SchoolLife schoolLife() throws EcoleDirecteSchoolLifeFetchException {
        Optional<SchoolLife> periodsCache = loadCache(CacheKey.SCHOOL_LIFE, SchoolLife.class);
        if (periodsCache.isPresent()) {
            return periodsCache.get();
        }
        SchoolLife schoolLife = super.schoolLife();
        cache.put(CacheKey.SCHOOL_LIFE, new CacheValue(cacheTimeout(), Optional.of(schoolLife)));
        return schoolLife;
    }

    private <T> Optional<T> loadCache(CacheKey key, Class<T> tClass) {
        return Optional.ofNullable(cache.get(key))
                .filter(cacheValue -> cacheValue.expires().isAfter(Instant.now()))
                .flatMap(cacheValue -> cacheValue.value(tClass));
    }

    private Instant cacheTimeout() {
        return Instant.now().plus(cacheTimeout);
    }

    private enum CacheKey {
        PERIODS,
        SCHOOL_LIFE
    }
}
