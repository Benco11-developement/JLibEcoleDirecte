package fr.benco11.jlibecoledirecte.lib.account.dto;

import java.util.Map;

public record ModuleDto(String code, boolean enable, int ordre, int badge, Map<String, Object> params) {}