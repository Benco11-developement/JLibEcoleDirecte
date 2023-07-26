package fr.benco11.jlibecoledirecte.lib.exception.runtime;

import fr.benco11.jlibecoledirecte.api.exception.runtime.EcoleDirecteRuntimeException;

public class EcoleDirecteMappingReflectionException extends EcoleDirecteRuntimeException {
    public EcoleDirecteMappingReflectionException() {
        super(
                "Une erreur s'est produite lors d'un mapping d'un DTO obtenu en utilisant l'API de magie noire Reflection");
    }
}
