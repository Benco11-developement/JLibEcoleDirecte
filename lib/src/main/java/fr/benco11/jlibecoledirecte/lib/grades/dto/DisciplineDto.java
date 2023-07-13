package fr.benco11.jlibecoledirecte.lib.grades.dto;

import java.util.List;

public record DisciplineDto(
        int id,
        String codeMatiere,
        String codeSousMatiere,
        String discipline,
        Double moyenne,
        Double moyenneClasse,
        Double moyenneMin,
        Double moyenneMax,
        Double coef,
        int effectif,
        boolean groupeMatiere,
        boolean sousMatiere,
        int idGroupeMatiere,
        int option,
        int rang,
        String appreciationClasse,
        List<TeacherDto> professeurs,
        List<String> appreciations) {}
