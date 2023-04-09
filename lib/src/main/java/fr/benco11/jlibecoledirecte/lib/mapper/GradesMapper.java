package fr.benco11.jlibecoledirecte.lib.mapper;

import fr.benco11.jlibecoledirecte.api.grades.Assignment;
import fr.benco11.jlibecoledirecte.api.grades.Period;
import fr.benco11.jlibecoledirecte.lib.dto.output.grades.*;
import fr.benco11.jlibecoledirecte.lib.grades.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper
public interface GradesMapper {
    GradesMapper MAPPER = Mappers.getMapper(GradesMapper.class);

    default List<Period> periodsDTOtoPeriods(List<PeriodDTO> periodsDTO,
                                             List<AssignmentDTO> assignmentsDTO) {
        Map<String, List<AssignmentDTO>> assignmentsPerPeriod = assignmentsDTO.stream()
                .collect(Collectors.toMap(AssignmentDTO::codePeriode, List::of, (a, b) -> Stream.concat(a.stream(), b.stream())
                        .toList()));
        return periodsDTO.stream().map(p -> (Period) periodDTOToPeriod(p, assignmentsPerPeriod.getOrDefault(p.codePeriode(), List.of()))).toList();
    }

    @Mapping(target = "id", source = "periodDTO.idPeriode")
    @Mapping(target = "code", source = "periodDTO.codePeriode")
    @Mapping(target = "name", source = "periodDTO.periode")
    @Mapping(target = "annual", source = "periodDTO.annuel")
    @Mapping(target = "date", expression = "java(fr.benco11.jlibecoledirecte.lib.utils.DateUtils.period(periodDTO.dateDebut(), periodDTO.dateFin()))")
    @Mapping(target = "mockExam", source = "periodDTO.examenBlanc")
    @Mapping(target = "ended", source = "periodDTO.cloture")
    @Mapping(target = "average", source = "periodDTO.ensembleMatieres")
    @Mapping(target = "formTeacher", source = "periodDTO.ensembleMatieres")
    @Mapping(target = "formTeacherComment", source = "periodDTO.ensembleMatieres.appreciationPP")
    @Mapping(target = "schoolClassComment", source = "periodDTO.ensembleMatieres.appreciationGeneraleClasse")
    @Mapping(target = "conferenceDecision", source = "periodDTO.ensembleMatieres.decisionDuConseil")
    @Mapping(target = "conferenceDate", expression = "java(Optional.ofNullable(periodDTO.dateConseil()).map(a -> a.atTime(periodDTO.heureConseil())))")
    @Mapping(target = "disciplines", expression = "java(periodDTO.ensembleMatieres().disciplines().stream().map(d -> (Discipline) disciplineDTOToDiscipline(d, assignmentsDTO)).toList())")
    DefaultPeriod periodDTOToPeriod(PeriodDTO periodDTO, List<AssignmentDTO> assignmentsDTO);

    @Mapping(target = "coef", constant = "1")
    @Mapping(target = "outOf", constant = "20")
    @Mapping(target = "entryDate", expression = "java(Optional.ofNullable(disciplineSetDTO.dateCalcul()).map(d -> d.toLocalDate()))")
    @Mapping(target = "user", source = "moyenneGenerale")
    @Mapping(target = "schoolClass", source = "moyenneClasse")
    @Mapping(target = "schoolClassMin", source = "moyenneMin")
    @Mapping(target = "schoolClassMax", source = "moyenneMax")
    @Mapping(target = "isValueBased", constant = "true")
    DefaultMark disciplineSetDTOToAverageMark(DisciplineSetDTO disciplineSetDTO);


    @Mapping(target = "id", constant = "-1")
    @Mapping(target = "name", source = "nomPP")
    DefaultTeacher disciplineSetDTOToTeacher(DisciplineSetDTO disciplineSetDTO);

    @Mapping(target = "groupId", source = "disciplineDTO.idGroupeMatiere")
    @Mapping(target = "row", source = "disciplineDTO.rang")
    @Mapping(target = "headcount", source = "disciplineDTO.effectif")
    @Mapping(target = "code", source = "disciplineDTO.codeMatiere")
    @Mapping(target = "subCode", source = "disciplineDTO.codeSousMatiere")
    @Mapping(target = "name", source = "disciplineDTO.discipline")
    @Mapping(target = "average", source = "disciplineDTO")
    @Mapping(target = "comments", expression = "java(commentsMapping(disciplineDTO.appreciations()))")
    @Mapping(target = "isSubDiscipline", source = "disciplineDTO.sousMatiere")
    @Mapping(target = "isGroupDiscipline", source = "disciplineDTO.groupeMatiere")
    @Mapping(target = "isOptional", expression = "java(disciplineDTO.option() != 0)")
    @Mapping(target = "teachers", source = "disciplineDTO.professeurs")
    @Mapping(target = "assignments", expression = "java(assignmentsOfDiscipline(disciplineDTO, assignmentsDTO))")
    DefaultDiscipline disciplineDTOToDiscipline(DisciplineDTO disciplineDTO,
                                                List<AssignmentDTO> assignmentsDTO);

    @Mapping(target = "outOf", constant = "20")
    @Mapping(target = "entryDate", expression = "java(Optional.empty())")
    @Mapping(target = "user", source = "moyenne")
    @Mapping(target = "schoolClass", source = "moyenneClasse")
    @Mapping(target = "schoolClassMin", source = "moyenneMin")
    @Mapping(target = "schoolClassMax", source = "moyenneMax")
    @Mapping(target = "isValueBased", constant = "true")
    DefaultMark disciplineDTOToMark(DisciplineDTO disciplineDTO);

    default List<String> commentsMapping(List<String> comments) {
        return comments.stream().map(c -> new String(Base64.getMimeDecoder().decode(c))).toList();
    }

    @Mapping(target = "name", source = "nom")
    DefaultTeacher teacherDTOToTeacher(TeacherDTO teacherDTO);

    @Mapping(target = "name", source = "devoir")
    @Mapping(target = "periodCode", source = "codePeriode")
    @Mapping(target = "disciplineCode", source = "codeMatiere")
    @Mapping(target = "disciplineLabel", source = "libelleMatiere")
    @Mapping(target = "subDisciplineCode", source = "codeSousMatiere")
    @Mapping(target = "assignmentType", source = "typeDevoir")
    @Mapping(target = "comment", source = "commentaire")
    @Mapping(target = "mark", source = "assignmentDTO")
    DefaultAssignment assignmentDTOToAssignment(AssignmentDTO assignmentDTO);

    @Mapping(target = "outOf", source = "noteSur")
    @Mapping(target = "entryDate", expression = "java(Optional.of(assignmentDTO.dateSaisie()))")
    @Mapping(target = "user", source = "valeur")
    @Mapping(target = "schoolClass", source = "moyenneClasse")
    @Mapping(target = "schoolClassMin", source = "minClasse")
    @Mapping(target = "schoolClassMax", source = "maxClasse")
    @Mapping(target = "isValueBased", source = "valeurisee")
    DefaultMark assignmentDTOToMark(AssignmentDTO assignmentDTO);

    default List<Assignment> assignmentsOfDiscipline(DisciplineDTO disciplineDTO,
                                                     List<AssignmentDTO> assignmentsDTO) {
        return assignmentsDTO.stream()
                .filter(a -> a.codeMatiere()
                        .equals(disciplineDTO.codeMatiere()) && (!disciplineDTO.sousMatiere() || disciplineDTO.codeMatiere()
                        .equals(a.codeSousMatiere())))
                .map(a -> (Assignment) assignmentDTOToAssignment(a))
                .toList();
    }
}
