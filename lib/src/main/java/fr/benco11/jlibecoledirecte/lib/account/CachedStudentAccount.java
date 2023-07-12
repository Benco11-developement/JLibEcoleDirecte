package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteGradesFetchException;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteSchoolLifeFetchException;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;
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
                                Duration cacheTimeout) throws EcoleDirecteGradesFetchException, URISyntaxException, IOException, InterruptedException, EcoleDirecteSchoolLifeFetchException {
        super(accountType, modules, personalDetails, userProfile, context);
        this.cacheTimeout = cacheTimeout;

        if (preload) {
            periods();
            schoolLife();
        }
    }

    @Override
    public List<Period> periods() throws EcoleDirecteGradesFetchException, URISyntaxException, IOException, InterruptedException {
        Optional<Period[]> periodsCache = loadCache(CacheKey.PERIODS, Period[].class);
        if (periodsCache.isPresent()) return Arrays.asList(periodsCache.get());
        List<Period> periods = super.periods();
        cache.put(CacheKey.PERIODS, new CacheValue(cacheTimeout(), Optional.of(periods.toArray(new Period[0]))));
        return periods;
    }

    @Override
    public SchoolLife schoolLife() throws EcoleDirecteSchoolLifeFetchException, URISyntaxException, IOException, InterruptedException {
        Optional<SchoolLife> periodsCache = loadCache(CacheKey.SCHOOL_LIFE, SchoolLife.class);
        if (periodsCache.isPresent()) return periodsCache.get();
        SchoolLife schoolLife = super.schoolLife();
        cache.put(CacheKey.SCHOOL_LIFE, new CacheValue(cacheTimeout(), Optional.of(schoolLife)));
        return schoolLife;
    }

    private <T> Optional<T> loadCache(CacheKey key, Class<T> tClass) {
        return cache.getOrDefault(key, new CacheValue(cacheTimeout(), Optional.empty())).value(tClass);
    }

    private Instant cacheTimeout() {
        return Instant.now().plus(cacheTimeout);
    }

    private enum CacheKey {
        PERIODS,
        SCHOOL_LIFE
    }
}
