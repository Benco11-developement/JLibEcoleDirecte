package fr.benco11.jlibecoledirecte.lib.exception.runtime;

import fr.benco11.jlibecoledirecte.api.exception.runtime.EcoleDirecteRuntimeException;

public class EcoleDirecteDateParsingException extends EcoleDirecteRuntimeException {
    public EcoleDirecteDateParsingException() {
        super("Une erreur s'est produite lors de la désérialisation d'une date !");
    }
}
