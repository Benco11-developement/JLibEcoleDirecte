package fr.benco11.jlibecoledirecte.lib.exception.runtime;

import fr.benco11.jlibecoledirecte.api.exception.runtime.EcoleDirecteRuntimeException;

public class EcoleDirecteDoubleParsingException extends EcoleDirecteRuntimeException {
    public EcoleDirecteDoubleParsingException() {
        super(-1, "Une erreur s'est produite lors de la désérialisation d'un double !");
    }
}
