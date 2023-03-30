package fr.benco11.jlibecoledirecte.api.account;

import fr.benco11.jlibecoledirecte.api.exception.GradesFetchEcoleDirecteException;
import fr.benco11.jlibecoledirecte.api.grades.Period;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface StudentAccount extends Account {
    List<Period> periods() throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException;

    Optional<Period> period(String id) throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException;

    Optional<Period> period(int num) throws GradesFetchEcoleDirecteException, URISyntaxException, IOException, InterruptedException;
}
