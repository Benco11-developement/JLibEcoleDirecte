package fr.benco11.jlibecoledirecte.lib.account;

import static fr.benco11.jlibecoledirecte.lib.http.DefaultHttpService.tokenHeader;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import fr.benco11.jlibecoledirecte.api.account.StudentAccount;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteGradesFetchException;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteSchoolLifeFetchException;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.grades.GradesMapper;
import fr.benco11.jlibecoledirecte.lib.grades.dto.AssignmentDto;
import fr.benco11.jlibecoledirecte.lib.grades.dto.PeriodDto;
import fr.benco11.jlibecoledirecte.lib.http.Endpoints;
import fr.benco11.jlibecoledirecte.lib.schoollife.SchoolLifeMapper;
import fr.benco11.jlibecoledirecte.lib.schoollife.dto.SchoolEventDto;
import fr.benco11.jlibecoledirecte.lib.session.SessionServices;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public sealed class DefaultStudentAccount extends BasicAccount implements StudentAccount permits CachedStudentAccount {
    public static final String PERIOD_PREFIX = "A00";

    public DefaultStudentAccount(AccountData accountData, SessionContext context, SessionServices sessionServices) {
        super(accountData, context, sessionServices);
    }

    @Override
    public List<Period> periods() throws EcoleDirecteGradesFetchException {
        try {
            JsonObject periodsJson = jsonService.dataBlock(httpService.post(
                    Endpoints.GRADE.asString(userProfile.id()),
                    tokenHeader(context.token()),
                    jsonService.serializeEmpty(),
                    EcoleDirecteGradesFetchException::new));
            List<PeriodDto> periods =
                    Arrays.asList(jsonService.deserialize(periodsJson.get("periodes"), PeriodDto[].class));
            List<AssignmentDto> assignments =
                    Arrays.asList(jsonService.deserialize(periodsJson.get("notes"), AssignmentDto[].class));
            return GradesMapper.MAPPER.periodsDtosToPeriods(periods, assignments);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new EcoleDirecteGradesFetchException(e);
        }
    }

    @Override
    public Optional<Period> period(String id) throws EcoleDirecteGradesFetchException {
        return periods().stream().filter(p -> p.id().equals(id)).findAny();
    }

    @Override
    public Optional<Period> period(int num) throws EcoleDirecteGradesFetchException {
        return period(PERIOD_PREFIX + num);
    }

    @Override
    public SchoolLife schoolLife() throws EcoleDirecteSchoolLifeFetchException {
        try {
            JsonObject schoolLifeJson = jsonService.dataBlock(httpService.post(
                    Endpoints.SCHOOL_LIFE.asString(userProfile.id()),
                    tokenHeader(context.token()),
                    jsonService.serializeEmpty(),
                    EcoleDirecteSchoolLifeFetchException::new));
            List<SchoolEventDto> absences = Arrays.asList(
                    jsonService.deserialize(schoolLifeJson.getAsJsonArray("absencesRetards"), SchoolEventDto[].class));
            List<SchoolEventDto> sanctions = Arrays.asList(jsonService.deserialize(
                    schoolLifeJson.getAsJsonArray("sanctionsEncouragements"), SchoolEventDto[].class));
            Map<String, Boolean> parameters = jsonService.deserialize(
                    schoolLifeJson.getAsJsonObject("parametrage"), new TypeToken<Map<String, Boolean>>() {}.getType());
            return SchoolLifeMapper.MAPPER.schoolEventDtosToSchoolLife(sanctions, absences, parameters);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new EcoleDirecteSchoolLifeFetchException(e);
        }
    }
}
