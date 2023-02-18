package fr.benco11.jlibecoledirecte.api.session;

import fr.benco11.jlibecoledirecte.api.exception.LoginEcoleDirecteException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface SessionBuilder {
    Session login() throws LoginEcoleDirecteException, URISyntaxException, IOException, InterruptedException;
}
