package fr.benco11.jlibecoledirecte.lib.grades.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record PeriodDto(
        String idPeriode,
        String codePeriode,
        String periode,
        LocalDate dateDebut,
        LocalDate dateFin,
        boolean annuel,
        boolean cloture,
        boolean examenBlanc,
        LocalDate dateConseil,
        LocalTime heureConseil,
        DisciplineSetDto ensembleMatieres) {}
