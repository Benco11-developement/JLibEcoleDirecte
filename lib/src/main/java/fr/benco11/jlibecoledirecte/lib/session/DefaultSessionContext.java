package fr.benco11.jlibecoledirecte.lib.session;

import fr.benco11.jlibecoledirecte.api.session.SessionContext;

public record DefaultSessionContext(long idLogin, String token) implements SessionContext {
}
