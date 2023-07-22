package fr.benco11.jlibecoledirecte.lib.grades.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DisciplineSetDto(
        LocalDateTime dateCalcul,
        Double moyenneGenerale,
        Double moyenneClasse,
        Double moyenneMin,
        Double moyenneMax,
        String nomPP,
        String appreciationPP,
        String decisionDuConseil,
        String appreciationGeneraleClasse,
        List<DisciplineDto> disciplines) {}