module JLibEcoleDirecte.lib.main {
    requires transitive JLibEcoleDirecte.api.main;
    requires transitive java.net.http;
    requires transitive com.google.gson;


    exports fr.benco11.jlibecoledirecte.lib.dto.output.login;
    exports fr.benco11.jlibecoledirecte.lib;
    exports fr.benco11.jlibecoledirecte.lib.utils;
    exports fr.benco11.jlibecoledirecte.lib.user;
    exports fr.benco11.jlibecoledirecte.lib.dto.input;
}