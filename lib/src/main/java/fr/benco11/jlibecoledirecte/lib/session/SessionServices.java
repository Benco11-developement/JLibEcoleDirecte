package fr.benco11.jlibecoledirecte.lib.session;

import fr.benco11.jlibecoledirecte.lib.http.HttpService;
import fr.benco11.jlibecoledirecte.lib.json.JsonService;

public record SessionServices(JsonService jsonService, HttpService httpService) {}
