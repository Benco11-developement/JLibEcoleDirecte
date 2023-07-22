package fr.benco11.jlibecoledirecte.lib.account;

import static fr.benco11.jlibecoledirecte.lib.utils.HttpService.tokenHeader;
import static fr.benco11.jlibecoledirecte.lib.utils.JsonUtils.data;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import fr.benco11.jlibecoledirecte.api.account.*;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteGradesFetchException;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteSchoolLifeFetchException;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.grades.GradesMapper;
import fr.benco11.jlibecoledirecte.lib.grades.dto.AssignmentDto;
import fr.benco11.jlibecoledirecte.lib.grades.dto.PeriodDto;
import fr.benco11.jlibecoledirecte.lib.schoollife.SchoolLifeMapper;
import fr.benco11.jlibecoledirecte.lib.schoollife.dto.SchoolEventDto;
import fr.benco11.jlibecoledirecte.lib.utils.HttpService;
import fr.benco11.jlibecoledirecte.lib.utils.JsonUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public sealed class DefaultStudentAccount extends BasicAccount implements StudentAccount permits CachedStudentAccount {
    public static final String PERIOD_PREFIX = "A00";

    public DefaultStudentAccount(AccountData accountData, SessionContext context, HttpService httpService) {
        super(accountData, context, httpService);
    }

    @Override
    public List<Period> periods()
            throws EcoleDirecteGradesFetchException, URISyntaxException, IOException, InterruptedException {
        JsonObject periodsJson = data(httpService.postPlainText(
                HttpService.Endpoints.GRADE.asString(userProfile.id()),
                JsonUtils.serialize(new Object()),
                tokenHeader(context.token()),
                new EcoleDirecteGradesFetchException()));
        List<PeriodDto> periodsDto = JsonUtils.deserializeList(periodsJson.get("periodes"), PeriodDto[].class);
        List<AssignmentDto> assignmentsDto = JsonUtils.deserializeList(periodsJson.get("notes"), AssignmentDto[].class);
        return GradesMapper.MAPPER.periodsDtosToPeriods(periodsDto, assignmentsDto);
    }

    @Override
    public Optional<Period> period(String id)
            throws EcoleDirecteGradesFetchException, URISyntaxException, IOException, InterruptedException {
        return periods().stream().filter(p -> p.id().equals(id)).findAny();
    }

    @Override
    public Optional<Period> period(int num)
            throws EcoleDirecteGradesFetchException, URISyntaxException, IOException, InterruptedException {
        return period(PERIOD_PREFIX + num);
    }

    @Override
    public SchoolLife schoolLife()
            throws EcoleDirecteSchoolLifeFetchException, URISyntaxException, IOException, InterruptedException {
        JsonObject schoolLifeJson = data(httpService.postPlainText(
                HttpService.Endpoints.SCHOOL_LIFE.asString(userProfile.id()),
                JsonUtils.serialize(new Object()),
                tokenHeader(context.token()),
                new EcoleDirecteSchoolLifeFetchException()));
        List<SchoolEventDto> absences =
                JsonUtils.deserializeList(schoolLifeJson.getAsJsonArray("absencesRetards"), SchoolEventDto[].class);
        List<SchoolEventDto> sanctions = JsonUtils.deserializeList(
                schoolLifeJson.getAsJsonArray("sanctionsEncouragements"), SchoolEventDto[].class);
        Map<String, Boolean> parameters = JsonUtils.deserialize(
                schoolLifeJson.getAsJsonObject("parametrage"), new TypeToken<Map<String, Boolean>>() {}.getType());
        return SchoolLifeMapper.MAPPER.schoolEventDtosToSchoolLife(sanctions, absences, parameters);
    }
}
