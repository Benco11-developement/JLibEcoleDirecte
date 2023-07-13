package fr.benco11.jlibecoledirecte.lib.account.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AccountDto(
        long id,
        long idLogin,
        String identifiant,
        String typeCompte,
        String email,
        String prenom,
        String nom,
        String anneeScolaireCourante,
        List<ModuleDto> modules,
        ProfileDto profile,
        String nomEtablissement,
        LocalDateTime lastConnexion) {}
