package fr.benco11.jlibecoledirecte.lib;

import com.google.gson.JsonObject;
import fr.benco11.jlibecoledirecte.api.exception.LoginEcoleDirecteException;
import fr.benco11.jlibecoledirecte.api.session.Session;
import fr.benco11.jlibecoledirecte.api.session.SessionBuilder;
import fr.benco11.jlibecoledirecte.lib.dto.input.LoginDTO;
import fr.benco11.jlibecoledirecte.lib.dto.output.login.AccountDTO;
import fr.benco11.jlibecoledirecte.lib.dto.output.login.SettingsDTO;
import fr.benco11.jlibecoledirecte.lib.mapper.AccountMapper;
import fr.benco11.jlibecoledirecte.lib.user.AccountImplementation;
import fr.benco11.jlibecoledirecte.lib.user.DefaultSession;
import fr.benco11.jlibecoledirecte.lib.utils.HttpUtils;
import fr.benco11.jlibecoledirecte.lib.utils.JsonUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

import static fr.benco11.jlibecoledirecte.lib.utils.HttpUtils.tokenHeader;

public class EcoleDirecteSessionBuilder implements SessionBuilder {
    public static final Duration DEFAULT_SESSION_DURATION = Duration.ofHours(1);
    private final String username;
    private final String password;
    private Duration sessionDuration;
    private Duration cacheDuration;

    public EcoleDirecteSessionBuilder(String username, String password) {
        this.username = username;
        this.password = password;
        this.sessionDuration = DEFAULT_SESSION_DURATION;
    }

    public EcoleDirecteSessionBuilder sessionTimeout(Duration duration) {
        this.sessionDuration = duration;
        return this;
    }

    public EcoleDirecteSessionBuilder useCache(Duration cacheTimeout) {
        this.cacheDuration = cacheTimeout;
        return this;
    }

    public Session login() throws URISyntaxException, IOException, InterruptedException, LoginEcoleDirecteException {
        if(cacheDuration != null) return null;

        LoginDTO loginDTO = new LoginDTO(username, password);
        JsonObject loginResult = JsonUtils.deserialize(HttpUtils.postPlainText(HttpUtils.Endpoints.LOGIN.asString(), JsonUtils.serialize(loginDTO), new LoginEcoleDirecteException()));
        if(!loginResult.has("token")) throw new LoginEcoleDirecteException(loginResult.get("code")
                                                                                      .getAsInt(), loginResult.get("message")
                                                                                                              .getAsString());
        String token = loginResult.get("token")
                                  .getAsString();
        AccountDTO accountDTO = JsonUtils.deserialize(loginResult.getAsJsonObject("data")
                                                                 .getAsJsonArray("accounts")
                                                                 .get(0), AccountDTO.class);
        long idLogin = accountDTO.idLogin();

        JsonObject settingsResult = JsonUtils.deserialize(HttpUtils.postPlainText(HttpUtils.Endpoints.SETTINGS.asString(idLogin), JsonUtils.serialize(new Object()), tokenHeader(token), new LoginEcoleDirecteException()));
        SettingsDTO settingsDTO = JsonUtils.deserialize(settingsResult.get("data"), SettingsDTO.class);

        return new DefaultSession(token, idLogin, sessionDuration, accountDTO.lastConnexion(), AccountMapper.MAPPER.accountDTOAndSettingsDTOToDefaultAccount(accountDTO, idLogin, settingsDTO, password, AccountImplementation.DEFAULT));
    }
}
