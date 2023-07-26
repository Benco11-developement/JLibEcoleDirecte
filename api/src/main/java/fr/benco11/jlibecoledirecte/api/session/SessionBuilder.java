package fr.benco11.jlibecoledirecte.api.session;

import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteLoginException;

public interface SessionBuilder {
    Session login() throws EcoleDirecteLoginException;
}
