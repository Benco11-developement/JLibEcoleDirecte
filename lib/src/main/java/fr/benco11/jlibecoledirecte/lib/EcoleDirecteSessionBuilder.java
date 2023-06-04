package fr.benco11.jlibecoledirecte.lib;

import com.google.gson.JsonObject;
import fr.benco11.jlibecoledirecte.api.exception.LoginEcoleDirecteException;
import fr.benco11.jlibecoledirecte.api.session.Session;
import fr.benco11.jlibecoledirecte.api.session.SessionBuilder;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.AccountImplementation;
import fr.benco11.jlibecoledirecte.lib.account.dto.LoginDTOInput;
import fr.benco11.jlibecoledirecte.lib.account.dto.AccountDTO;
import fr.benco11.jlibecoledirecte.lib.account.dto.SettingsDTO;
import fr.benco11.jlibecoledirecte.lib.account.factory.AccountFactory;
import fr.benco11.jlibecoledirecte.lib.account.AccountMapper;
import fr.benco11.jlibecoledirecte.lib.session.DefaultSession;
import fr.benco11.jlibecoledirecte.lib.session.DefaultSessionContext;
import fr.benco11.jlibecoledirecte.lib.utils.HttpUtils;
import fr.benco11.jlibecoledirecte.lib.utils.JsonUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

import static fr.benco11.jlibecoledirecte.lib.utils.HttpUtils.tokenHeader;

public class EcoleDirecteSessionBuilder implements SessionBuilder {
    public static final Duration DEFAULT_SESSION_DURATION = Duration.ofHours(2);
    private final String username;
    private final String password;
    private Duration sessionDuration;
    private AccountFactory accountFactory;

    public EcoleDirecteSessionBuilder(String username, String password) {
        this.username = username;
        this.password = password;
        this.sessionDuration = DEFAULT_SESSION_DURATION;
        this.accountFactory = AccountFactory.defaultFactory(new AccountImplementation(AccountImplementation.ImplementationType.DEFAULT));
    }

    public EcoleDirecteSessionBuilder sessionTimeout(Duration duration) {
        this.sessionDuration = duration;
        return this;
    }

    public EcoleDirecteSessionBuilder useCache(Duration cacheTimeout, boolean preload) {
        this.accountFactory = AccountFactory.defaultFactory(new AccountImplementation((preload) ? AccountImplementation.ImplementationType.PRE_LOAD_CACHE : AccountImplementation.ImplementationType.CACHED, cacheTimeout));
        return this;
    }

    public EcoleDirecteSessionBuilder customFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        return this;
    }

    @Override
    public Session login() throws URISyntaxException, IOException, InterruptedException, LoginEcoleDirecteException {
        LoginDTOInput loginDTOInput = new LoginDTOInput(username, password);
        JsonObject loginResult = JsonUtils.deserialize(HttpUtils.postPlainText(HttpUtils.Endpoints.LOGIN.asString(), JsonUtils.serialize(loginDTOInput), new LoginEcoleDirecteException()));
        if (!loginResult.has("token")) throw new LoginEcoleDirecteException(loginResult.get("code")
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

        SessionContext context = new DefaultSessionContext(idLogin, token);
        return new DefaultSession(context, sessionDuration, accountDTO.lastConnexion(), AccountMapper.MAPPER.accountDTOAndSettingsDTOToDefaultAccount(accountDTO, settingsDTO, password, accountFactory, context));
    }
}
