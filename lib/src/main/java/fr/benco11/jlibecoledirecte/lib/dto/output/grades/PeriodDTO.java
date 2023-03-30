package fr.benco11.jlibecoledirecte.lib.dto.output.grades;

import java.util.Date;

public record PeriodDTO(String idPeriode, String codePeriode, String periode, Date dateDebut,
                        Date dateFin, boolean annuel, boolean cloture, boolean examenBlanc,
                        Date dateConseil, String heureConseil,
                        DisciplineSetDTO ensembleMatieres) {
}
