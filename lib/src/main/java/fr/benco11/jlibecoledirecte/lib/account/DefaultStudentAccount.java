package fr.benco11.jlibecoledirecte.lib.account;

import com.google.gson.JsonObject;
import fr.benco11.jlibecoledirecte.api.account.*;
import fr.benco11.jlibecoledirecte.api.exception.GradesFetchEcoleDirecteException;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.dto.output.grades.AssignmentDTO;
import fr.benco11.jlibecoledirecte.lib.dto.output.grades.PeriodDTO;
import fr.benco11.jlibecoledirecte.lib.mapper.GradesMapper;
import fr.benco11.jlibecoledirecte.lib.utils.HttpUtils;
import fr.benco11.jlibecoledirecte.lib.utils.JsonUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static fr.benco11.jlibecoledirecte.lib.utils.HttpUtils.tokenHeader;

public sealed class DefaultStudentAccount extends BasicAccount implements StudentAccount permits CachedStudentAccount {
    public static final String PERIOD_PREFIX = "A00";

    public DefaultStudentAccount(AccountType accountType,
                                 List<EcoleDirecteModule> modules,
                                 PersonalDetails personalDetails,
                                 UserProfile userProfile, SessionContext context) {
        super(accountType, modules, personalDetails, userProfile, context);
    }

    @Override
    public List<Period> periods() throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException {
        JsonObject json = JsonUtils.deserialize(HttpUtils.postPlainText(HttpUtils.Endpoints.GRADE.asString(userProfile.id()), JsonUtils.serialize(new Object()), tokenHeader(context.token()), new GradesFetchEcoleDirecteException()), JsonObject.class);
        JsonObject data = json.getAsJsonObject("data");
        List<PeriodDTO> periodsDTO = Arrays.asList(JsonUtils.deserialize(data.get("periodes"), PeriodDTO[].class));
        List<AssignmentDTO> assignmentsDTO = Arrays.asList(JsonUtils.deserialize(data.get("notes"), AssignmentDTO[].class));
        return GradesMapper.MAPPER.periodsDTOtoPeriods(periodsDTO, assignmentsDTO);
    }

    @Override
    public Optional<Period> period(String id) throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException {
        return periods().stream().filter(p -> p.id().equals(id)).findAny();
    }

    @Override
    public Optional<Period> period(int num) throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException {
        return period(PERIOD_PREFIX + num);
    }
}
