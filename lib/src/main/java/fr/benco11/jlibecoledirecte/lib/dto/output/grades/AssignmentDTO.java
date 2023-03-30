package fr.benco11.jlibecoledirecte.lib.dto.output.grades;

import java.util.Date;

public record AssignmentDTO(long id, String devoir, String codePeriode, String codeMatiere,
                            String libelleMatiere, String codeSousMatiere, String typeDevoir,
                            String commentaire,

                            Double coef, Double noteSur, Double valeur, boolean nonSignificatif,
                            Date date, Date dateSaisie, boolean valeurisee, Double moyenneClasse,
                            Double minClasse, Double maxClasse) {
}
