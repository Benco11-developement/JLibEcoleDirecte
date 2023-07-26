package fr.benco11.jlibecoledirecte.api.account;

import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteGradesFetchException;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteSchoolLifeFetchException;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;
import java.util.List;
import java.util.Optional;

public interface StudentAccount extends Account {
    List<Period> periods() throws EcoleDirecteGradesFetchException;

    Optional<Period> period(String id) throws EcoleDirecteGradesFetchException;

    Optional<Period> period(int num) throws EcoleDirecteGradesFetchException;

    SchoolLife schoolLife() throws EcoleDirecteSchoolLifeFetchException;
}
