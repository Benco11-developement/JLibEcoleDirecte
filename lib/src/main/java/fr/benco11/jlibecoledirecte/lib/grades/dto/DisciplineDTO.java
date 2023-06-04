package fr.benco11.jlibecoledirecte.lib.grades.dto;

import java.util.List;

public record DisciplineDTO(int id, String codeMatiere, String codeSousMatiere, String discipline,
                            Double moyenne, Double moyenneClasse, Double moyenneMin,
                            Double moyenneMax, Double coef, int effectif, boolean groupeMatiere,
                            boolean sousMatiere, int idGroupeMatiere, int option, int rang,
                            String appreciationClasse, List<TeacherDTO> professeurs,
                            List<String> appreciations) {
}
