package fr.benco11.jlibecoledirecte.lib.schoollife;

import fr.benco11.jlibecoledirecte.api.schoollife.SchoolEvent;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;
import fr.benco11.jlibecoledirecte.lib.schoollife.dto.SchoolEventDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchoolLifeMapper {
    SchoolLifeMapper MAPPER = Mappers.getMapper(SchoolLifeMapper.class);

    default SchoolLife schoolEventDtosToSchoolLife(
            List<SchoolEventDto> sanctions, List<SchoolEventDto> absences, Map<String, Boolean> parameters) {
        return new DefaultSchoolLife(
                Stream.concat(
                                sanctions.stream().map(e -> schoolEventDtoToSchoolEvent(e, true)),
                                absences.stream().map(e -> (SchoolEvent) schoolEventDtoToSchoolEvent(e, false)))
                        .toList(),
                parameters);
    }

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "studentName", source = "event.nomEleve")
    @Mapping(target = "type", source = "event.typeElement")
    @Mapping(target = "date", source = "event.date")
    @Mapping(target = "displayDate", source = "event.displayDate")
    @Mapping(target = "description", source = "event.libelle")
    @Mapping(target = "reason", source = "event.motif")
    @Mapping(target = "justified", source = "event.justifie")
    @Mapping(target = "by", source = "event.par")
    @Mapping(target = "comment", source = "event.commentaire")
    @Mapping(target = "justificationType", source = "event.typeJustification")
    @Mapping(target = "toDo", source = "event.aFaire")
    @Mapping(target = "toDoDate", source = "event.dateDeroulement")
    DefaultSchoolEvent schoolEventDtoToSchoolEvent(SchoolEventDto event, boolean isSanction);
}
