package fr.benco11.jlibecoledirecte.lib.exception.runtime;

import fr.benco11.jlibecoledirecte.api.exception.runtime.EcoleDirecteRuntimeException;

public class EcoleDirecteMappingException extends EcoleDirecteRuntimeException {
    public EcoleDirecteMappingException() {
        super("Une erreur s'est produite lors d'un mapping d'un DTO obtenu !");
    }
}
