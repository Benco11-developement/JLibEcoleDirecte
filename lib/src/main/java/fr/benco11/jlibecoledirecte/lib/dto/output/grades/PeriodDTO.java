package fr.benco11.jlibecoledirecte.lib.dto.output.grades;

import java.time.LocalDate;
import java.time.LocalTime;

public record PeriodDTO(String idPeriode, String codePeriode, String periode, LocalDate dateDebut,
                        LocalDate dateFin, boolean annuel, boolean cloture, boolean examenBlanc,
                        LocalDate dateConseil, LocalTime heureConseil,
                        DisciplineSetDTO ensembleMatieres) {
}
