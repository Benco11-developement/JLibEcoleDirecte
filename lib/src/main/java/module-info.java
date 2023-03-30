module JLibEcoleDirecte.lib.main {
    requires transitive JLibEcoleDirecte.api.main;
    requires org.mapstruct;
    requires transitive java.net.http;
    requires transitive com.google.gson;


    exports fr.benco11.jlibecoledirecte.lib.dto.output.login;
    exports fr.benco11.jlibecoledirecte.lib;
    exports fr.benco11.jlibecoledirecte.lib.exception.runtime;
    exports fr.benco11.jlibecoledirecte.lib.mapper;
    exports fr.benco11.jlibecoledirecte.lib.utils;
    exports fr.benco11.jlibecoledirecte.lib.account;
    exports fr.benco11.jlibecoledirecte.lib.dto.input;
    exports fr.benco11.jlibecoledirecte.lib.dto.output.grades;
    exports fr.benco11.jlibecoledirecte.lib.session;
    exports fr.benco11.jlibecoledirecte.lib.adapter;
    exports fr.benco11.jlibecoledirecte.lib.factory;
    exports fr.benco11.jlibecoledirecte.lib.grades;
}