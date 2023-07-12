package fr.benco11.jlibecoledirecte.lib.schoollife.dto;

import java.time.LocalDate;

public record SchoolEventDTO(long id, String nomEleve, String typeElement, LocalDate date, String displayDate,
                             String libelle, String motif, boolean justifie, String par, String commentaire,
                             String typeJustification, String aFaire, LocalDate dateDeroulement) {
}
