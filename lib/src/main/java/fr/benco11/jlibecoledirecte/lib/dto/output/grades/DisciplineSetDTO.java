package fr.benco11.jlibecoledirecte.lib.dto.output.grades;

import java.time.LocalDateTime;
import java.util.List;

public record DisciplineSetDTO(LocalDateTime dateCalcul, Double moyenneGenerale, Double moyenneClasse,
                               Double moyenneMin, Double moyenneMax, String nomPP,
                               String appreciationPP,
                               String decisionDuConseil, String appreciationGeneraleClasse,
                               List<DisciplineDTO> disciplines) {
}
