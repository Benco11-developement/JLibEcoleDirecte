package fr.benco11.jlibecoledirecte.lib.dto.output.login;

import java.util.Date;
import java.util.List;

public record AccountDTO(long id, long idLogin, String identifiant, String typeCompte, String email,
                         String prenom, String nom, String anneeScolaireCourante,
                         List<ModuleDTO> modules, ProfileDTO profile, String nomEtablissement,
                         Date lastConnexion
) {
}
