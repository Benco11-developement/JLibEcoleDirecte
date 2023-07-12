package fr.benco11.jlibecoledirecte.api.session;

import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteLoginException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface SessionBuilder {
    Session login() throws EcoleDirecteLoginException, URISyntaxException, IOException, InterruptedException;
}
