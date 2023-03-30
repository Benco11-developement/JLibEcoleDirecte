package fr.benco11.jlibecoledirecte.lib.dto.output.grades;

import java.util.Date;
import java.util.List;

public record DisciplineSetDTO(Date dateCalcul, Double moyenneGenerale, Double moyenneClasse,
                               Double moyenneMin, Double moyenneMax, String nomPP,
                               String appreciationPP,
                               String decisionDuConseil, String appreciationGeneraleClasse,
                               List<DisciplineDTO> disciplines) {
}
