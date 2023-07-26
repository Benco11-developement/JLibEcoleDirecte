package fr.benco11.jlibecoledirecte.lib.session;

import static fr.benco11.jlibecoledirecte.lib.http.DefaultHttpService.defaultHeaders;
import static fr.benco11.jlibecoledirecte.lib.http.DefaultHttpService.tokenHeader;

import com.google.gson.JsonObject;
import fr.benco11.jlibecoledirecte.api.exception.EcoleDirecteLoginException;
import fr.benco11.jlibecoledirecte.api.session.Session;
import fr.benco11.jlibecoledirecte.api.session.SessionBuilder;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.AccountImplementation;
import fr.benco11.jlibecoledirecte.lib.account.AccountMapper;
import fr.benco11.jlibecoledirecte.lib.account.dto.AccountDto;
import fr.benco11.jlibecoledirecte.lib.account.dto.LoginDtoInput;
import fr.benco11.jlibecoledirecte.lib.account.dto.SettingsDto;
import fr.benco11.jlibecoledirecte.lib.account.factory.AccountFactory;
import fr.benco11.jlibecoledirecte.lib.http.DefaultHttpService;
import fr.benco11.jlibecoledirecte.lib.http.Endpoints;
import fr.benco11.jlibecoledirecte.lib.http.HttpService;
import fr.benco11.jlibecoledirecte.lib.json.DefaultJsonService;
import fr.benco11.jlibecoledirecte.lib.json.JsonService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

public class EcoleDirecteSessionBuilder implements SessionBuilder {
    public static final Duration DEFAULT_SESSION_DURATION = Duration.ofHours(2);
    private final String username;
    private final String password;
    private Duration sessionDuration;
    private HttpService httpService;
    private AccountFactory accountFactory;
    private JsonService jsonService;

    public EcoleDirecteSessionBuilder(String username, String password) {
        this.username = username;
        this.password = password;
        this.sessionDuration = DEFAULT_SESSION_DURATION;
        this.accountFactory = AccountFactory.defaultFactory(
                new AccountImplementation(AccountImplementation.ImplementationType.DEFAULT));
        this.jsonService = new DefaultJsonService();
        this.httpService = new DefaultHttpService(jsonService);
    }

    public EcoleDirecteSessionBuilder sessionTimeout(Duration duration) {
        this.sessionDuration = duration;
        return this;
    }

    public EcoleDirecteSessionBuilder useCache(Duration cacheTimeout, boolean preload) {
        this.accountFactory = AccountFactory.defaultFactory(new AccountImplementation(
                (preload)
                        ? AccountImplementation.ImplementationType.PRE_LOAD_CACHE
                        : AccountImplementation.ImplementationType.CACHED,
                cacheTimeout));
        return this;
    }

    public EcoleDirecteSessionBuilder customFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        return this;
    }

    public EcoleDirecteSessionBuilder httpService(HttpService httpService) {
        this.httpService = httpService;
        return this;
    }

    public EcoleDirecteSessionBuilder jsonService(JsonService jsonService) {
        this.jsonService = jsonService;
        return this;
    }

    @Override
    public Session login() throws EcoleDirecteLoginException {
        try {
            LoginDtoInput loginDtoInput = new LoginDtoInput(username, password);
            JsonObject loginResult = jsonService.deserialize(httpService.post(
                    Endpoints.LOGIN.asString(),
                    defaultHeaders(),
                    jsonService.serialize(loginDtoInput),
                    EcoleDirecteLoginException::new));
            if (!loginResult.has("token")) {
                throw new EcoleDirecteLoginException(
                        200,
                        loginResult.get("code").getAsInt(),
                        loginResult.get("message").getAsString());
            }
            String token = loginResult.get("token").getAsString();
            AccountDto accountDto = jsonService.deserialize(
                    loginResult
                            .getAsJsonObject("data")
                            .getAsJsonArray("accounts")
                            .get(0),
                    AccountDto.class);
            long idLogin = accountDto.idLogin();

            JsonObject settingsResult = jsonService.deserialize(httpService.post(
                    Endpoints.SETTINGS.asString(idLogin),
                    tokenHeader(token),
                    jsonService.serialize(new Object()),
                    EcoleDirecteLoginException::new));
            SettingsDto settingsDto = jsonService.deserialize(settingsResult.get("data"), SettingsDto.class);

            SessionContext context = new DefaultSessionContext(idLogin, token);
            return new DefaultSession(
                    context,
                    sessionDuration,
                    accountDto.lastConnexion(),
                    AccountMapper.MAPPER.accountDtoAndSettingsDtoToDefaultAccount(
                            accountDto,
                            settingsDto,
                            password,
                            accountFactory,
                            context,
                            new SessionServices(jsonService, httpService)));
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new EcoleDirecteLoginException(e);
        }
    }
}
