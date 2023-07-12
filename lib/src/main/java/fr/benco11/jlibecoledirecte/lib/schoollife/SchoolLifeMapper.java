package fr.benco11.jlibecoledirecte.lib.schoollife;

import fr.benco11.jlibecoledirecte.api.schoollife.SchoolEvent;
import fr.benco11.jlibecoledirecte.api.schoollife.SchoolLife;
import fr.benco11.jlibecoledirecte.lib.schoollife.dto.SchoolEventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Mapper
public interface SchoolLifeMapper {
    SchoolLifeMapper MAPPER = Mappers.getMapper(SchoolLifeMapper.class);

    default SchoolLife schoolEventDTOsToSchoolLife(List<SchoolEventDTO> sanctions, List<SchoolEventDTO> absences, Map<String, Boolean> parameters) {
        return new DefaultSchoolLife(Stream.concat(sanctions.stream().map(e -> schoolEventDTOToSchoolEvent(e, true)), absences.stream().map(e -> (SchoolEvent) schoolEventDTOToSchoolEvent(e, false))).toList(), parameters);
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
    DefaultSchoolEvent schoolEventDTOToSchoolEvent(SchoolEventDTO event, boolean isSanction);
}
