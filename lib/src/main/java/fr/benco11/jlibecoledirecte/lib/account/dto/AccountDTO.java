package fr.benco11.jlibecoledirecte.lib.account.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AccountDTO(long id, long idLogin, String identifiant, String typeCompte, String email,
                         String prenom, String nom, String anneeScolaireCourante,
                         List<ModuleDTO> modules, ProfileDTO profile, String nomEtablissement,
                         LocalDateTime lastConnexion
) {
}
