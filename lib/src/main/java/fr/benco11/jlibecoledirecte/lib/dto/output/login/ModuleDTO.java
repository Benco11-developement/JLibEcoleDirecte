package fr.benco11.jlibecoledirecte.lib.dto.output.login;

import java.util.Map;

public record ModuleDTO(String code, boolean enable, int ordre, int badge,
                        Map<String, Object> params) {
}
