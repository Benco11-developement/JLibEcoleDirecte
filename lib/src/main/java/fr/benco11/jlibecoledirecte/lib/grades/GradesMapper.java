package fr.benco11.jlibecoledirecte.lib.grades;

import fr.benco11.jlibecoledirecte.api.grades.Assignment;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.lib.grades.dto.*;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradesMapper {
    GradesMapper MAPPER = Mappers.getMapper(GradesMapper.class);

    default List<Period> periodsDtosToPeriods(List<PeriodDto> periods, List<AssignmentDto> assignments) {
        Map<String, List<AssignmentDto>> assignmentsPerPeriod = assignments.stream()
                .collect(Collectors.toMap(
                        AssignmentDto::codePeriode, List::of, (a, b) -> Stream.concat(a.stream(), b.stream())
                                .toList()));
        return periods.stream()
                .map(p -> (Period) periodDtoToPeriod(p, assignmentsPerPeriod.getOrDefault(p.codePeriode(), List.of())))
                .toList();
    }

    @Mapping(target = "id", source = "period.idPeriode")
    @Mapping(target = "code", source = "period.codePeriode")
    @Mapping(target = "name", source = "period.periode")
    @Mapping(target = "annual", source = "period.annuel")
    @Mapping(
            target = "date",
            expression =
                    "java(fr.benco11.jlibecoledirecte.lib.utils.DateUtils.period(period.dateDebut(), period.dateFin"
                            + "()))")
    @Mapping(target = "mockExam", source = "period.examenBlanc")
    @Mapping(target = "ended", source = "period.cloture")
    @Mapping(target = "average", source = "period.ensembleMatieres")
    @Mapping(target = "formTeacher", source = "period.ensembleMatieres")
    @Mapping(target = "formTeacherComment", source = "period.ensembleMatieres.appreciationPP")
    @Mapping(target = "schoolClassComment", source = "period.ensembleMatieres.appreciationGeneraleClasse")
    @Mapping(target = "conferenceDecision", source = "period.ensembleMatieres.decisionDuConseil")
    @Mapping(
            target = "conferenceDate",
            expression = "java(Optional.ofNullable(period.dateConseil()).map(a -> a.atTime(period.heureConseil())))")
    @Mapping(
            target = "disciplines",
            expression = "java(period.ensembleMatieres().disciplines().stream().map(d -> (Discipline) "
                    + "disciplineDtoToDiscipline(d, assignments)).toList())")
    DefaultPeriod periodDtoToPeriod(PeriodDto period, List<AssignmentDto> assignments);

    @Mapping(target = "coef", constant = "1")
    @Mapping(target = "outOf", constant = "20")
    @Mapping(
            target = "entryDate",
            expression = "java(Optional.ofNullable(disciplineSet.dateCalcul()).map(d -> d.toLocalDate()))")
    @Mapping(target = "user", source = "moyenneGenerale")
    @Mapping(target = "schoolClass", source = "moyenneClasse")
    @Mapping(target = "schoolClassMin", source = "moyenneMin")
    @Mapping(target = "schoolClassMax", source = "moyenneMax")
    @Mapping(target = "isValueBased", expression = "java(isAverageValueBased(disciplineSet))")
    DefaultMark disciplineSetDtoToAverageMark(DisciplineSetDto disciplineSet);

    @Mapping(target = "id", constant = "-1")
    @Mapping(target = "name", source = "nomPP")
    DefaultTeacher disciplineSetDtoToTeacher(DisciplineSetDto disciplineSet);

    @Mapping(target = "groupId", source = "discipline.idGroupeMatiere")
    @Mapping(target = "row", source = "discipline.rang")
    @Mapping(target = "headcount", source = "discipline.effectif")
    @Mapping(target = "code", source = "discipline.codeMatiere")
    @Mapping(target = "subCode", source = "discipline.codeSousMatiere")
    @Mapping(target = "name", source = "discipline.discipline")
    @Mapping(target = "average", source = "discipline")
    @Mapping(target = "comments", expression = "java(commentsMapping(discipline.appreciations()))")
    @Mapping(target = "isSubDiscipline", source = "discipline.sousMatiere")
    @Mapping(target = "isGroupDiscipline", source = "discipline.groupeMatiere")
    @Mapping(target = "isOptional", expression = "java(discipline.option() != 0)")
    @Mapping(target = "teachers", source = "discipline.professeurs")
    @Mapping(target = "assignments", expression = "java(assignmentsOfDiscipline(discipline, assignments))")
    DefaultDiscipline disciplineDtoToDiscipline(DisciplineDto discipline, List<AssignmentDto> assignments);

    @Mapping(target = "outOf", constant = "20")
    @Mapping(target = "entryDate", expression = "java(Optional.empty())")
    @Mapping(target = "user", source = "moyenne")
    @Mapping(target = "schoolClass", source = "moyenneClasse")
    @Mapping(target = "schoolClassMin", source = "moyenneMin")
    @Mapping(target = "schoolClassMax", source = "moyenneMax")
    @Mapping(target = "isValueBased", expression = "java(isAverageValueBased(discipline))")
    DefaultMark disciplineDtoToAverageMark(DisciplineDto discipline);

    default List<String> commentsMapping(List<String> comments) {
        return comments.stream()
                .map(c -> new String(Base64.getMimeDecoder().decode(c)))
                .toList();
    }

    @Mapping(target = "name", source = "nom")
    DefaultTeacher teacherDtoToTeacher(TeacherDto teacher);

    @Mapping(target = "name", source = "devoir")
    @Mapping(target = "periodCode", source = "codePeriode")
    @Mapping(target = "disciplineCode", source = "codeMatiere")
    @Mapping(target = "disciplineLabel", source = "libelleMatiere")
    @Mapping(target = "subDisciplineCode", source = "codeSousMatiere")
    @Mapping(target = "assignmentType", source = "typeDevoir")
    @Mapping(target = "comment", source = "commentaire")
    @Mapping(target = "mark", source = "assignment")
    DefaultAssignment assignmentDtoToAssignment(AssignmentDto assignment);

    @Mapping(target = "outOf", source = "noteSur")
    @Mapping(target = "entryDate", expression = "java(Optional.of(assignment.dateSaisie()))")
    @Mapping(target = "user", source = "valeur")
    @Mapping(target = "schoolClass", source = "moyenneClasse")
    @Mapping(target = "schoolClassMin", source = "minClasse")
    @Mapping(target = "schoolClassMax", source = "maxClasse")
    @Mapping(target = "isValueBased", source = "valeurisee")
    DefaultMark assignmentDtoToMark(AssignmentDto assignment);

    default List<Assignment> assignmentsOfDiscipline(DisciplineDto discipline, List<AssignmentDto> assignments) {
        return assignments.stream()
                .filter(a -> a.codeMatiere().equals(discipline.codeMatiere())
                        && (!discipline.sousMatiere()
                                || discipline.codeMatiere().equals(a.codeSousMatiere())))
                .map(a -> (Assignment) assignmentDtoToAssignment(a))
                .toList();
    }

    default boolean isAverageValueBased(DisciplineDto discipline) {
        return discipline.coef() == 0
                && discipline.moyenne() == 0
                && discipline.moyenneClasse() == 0
                && discipline.moyenneMax() == 0
                && discipline.moyenneMin() == 0;
    }

    default boolean isAverageValueBased(DisciplineSetDto disciplineSet) {
        return disciplineSet.moyenneGenerale() == 0
                && disciplineSet.moyenneClasse() == 0
                && disciplineSet.moyenneMax() == 0
                && disciplineSet.moyenneMin() == 0;
    }
}
