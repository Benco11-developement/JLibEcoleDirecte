package fr.benco11.jlibecoledirecte.lib.exception.runtime;

import fr.benco11.jlibecoledirecte.api.exception.runtime.EcoleDirecteRuntimeException;

public class EcoleDirecteAccountImplementationException extends EcoleDirecteRuntimeException {
    public EcoleDirecteAccountImplementationException() {
        super(-1, "Une erreur s'est produite lors de la création de l'Account !");
    }
}
