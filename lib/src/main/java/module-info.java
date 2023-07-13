module JLibEcoleDirecte.lib {
    requires transitive JLibEcoleDirecte.api;
    requires org.mapstruct;
    requires transitive java.net.http;
    requires transitive com.google.gson;

    exports fr.benco11.jlibecoledirecte.lib;
    exports fr.benco11.jlibecoledirecte.lib.schoollife;
    exports fr.benco11.jlibecoledirecte.lib.schoollife.dto;
    exports fr.benco11.jlibecoledirecte.lib.exception.runtime;
    exports fr.benco11.jlibecoledirecte.lib.utils;
    exports fr.benco11.jlibecoledirecte.lib.account;
    exports fr.benco11.jlibecoledirecte.lib.session;
    exports fr.benco11.jlibecoledirecte.lib.adapter;
    exports fr.benco11.jlibecoledirecte.lib.account.factory;
    exports fr.benco11.jlibecoledirecte.lib.grades;
    exports fr.benco11.jlibecoledirecte.lib.grades.dto;
    exports fr.benco11.jlibecoledirecte.lib.account.dto;
}
