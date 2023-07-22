package fr.benco11.jlibecoledirecte.lib;

import static fr.benco11.jlibecoledirecte.lib.utils.HttpService.tokenHeader;

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
import fr.benco11.jlibecoledirecte.lib.session.DefaultSession;
import fr.benco11.jlibecoledirecte.lib.session.DefaultSessionContext;
import fr.benco11.jlibecoledirecte.lib.utils.HttpService;
import fr.benco11.jlibecoledirecte.lib.utils.JsonUtils;
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

    public EcoleDirecteSessionBuilder(String username, String password) {
        this.username = username;
        this.password = password;
        this.httpService = new HttpService();
        this.sessionDuration = DEFAULT_SESSION_DURATION;
        this.accountFactory = AccountFactory.defaultFactory(
                new AccountImplementation(AccountImplementation.ImplementationType.DEFAULT));
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

    @Override
    public Session login() throws URISyntaxException, IOException, InterruptedException, EcoleDirecteLoginException {
        LoginDtoInput loginDtoInput = new LoginDtoInput(username, password);
        JsonObject loginResult = JsonUtils.deserialize(httpService.postPlainText(
                HttpService.Endpoints.LOGIN.asString(),
                JsonUtils.serialize(loginDtoInput),
                new EcoleDirecteLoginException()));
        if (!loginResult.has("token")) {
            throw new EcoleDirecteLoginException(
                    loginResult.get("code").getAsInt(),
                    loginResult.get("message").getAsString());
        }
        String token = loginResult.get("token").getAsString();
        AccountDto accountDto = JsonUtils.deserialize(
                loginResult.getAsJsonObject("data").getAsJsonArray("accounts").get(0), AccountDto.class);
        long idLogin = accountDto.idLogin();

        JsonObject settingsResult = JsonUtils.deserialize(httpService.postPlainText(
                HttpService.Endpoints.SETTINGS.asString(idLogin),
                JsonUtils.serialize(new Object()),
                tokenHeader(token),
                new EcoleDirecteLoginException()));
        SettingsDto settingsDto = JsonUtils.deserialize(settingsResult.get("data"), SettingsDto.class);

        SessionContext context = new DefaultSessionContext(idLogin, token);
        return new DefaultSession(
                context,
                sessionDuration,
                accountDto.lastConnexion(),
                AccountMapper.MAPPER.accountDtoAndSettingsDtoToDefaultAccount(
                        accountDto, settingsDto, password, accountFactory, context, httpService));
    }
}
