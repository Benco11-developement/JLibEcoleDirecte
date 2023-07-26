package fr.benco11.jlibecoledirecte.lib.http;

import com.google.gson.JsonObject;

public record ResponseDto(int code, String message, JsonObject data) {}
