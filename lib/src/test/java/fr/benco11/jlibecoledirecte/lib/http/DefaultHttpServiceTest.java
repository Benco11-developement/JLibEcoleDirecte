package fr.benco11.jlibecoledirecte.lib.http;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.google.gson.JsonObject;
import fr.benco11.jlibecoledirecte.lib.json.JsonService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

class DefaultHttpServiceTest {
    @RegisterExtension
    static WireMockExtension wiremock = WireMockExtension.newInstance()
            .options(wireMockConfig().port(8080))
            .configureStaticDsl(true)
            .build();

    static JsonService jsonService = mock(JsonService.class);

    @BeforeEach
    void setupWiremock() {
        stubFor(get("/home").willReturn(aResponse().withStatus(200).withBody("<p>Home</p>")));
        stubFor(post("/login").willReturn(aResponse().withStatus(401)));
        stubFor(post("/login")
                .withRequestBody(matching("This is a message"))
                .withHeader("Content-Type", equalTo("text/plain"))
                .willReturn(aResponse().withBody("Logged in !")));
        stubFor(post("/account").willReturn(aResponse().withStatus(401)));
        stubFor(post("/account")
                .withHeader("X-Token", equalTo("VERYSECUREDTOKEN"))
                .willReturn(aResponse().withStatus(200).withBody("Unknown format")));
        stubFor(post("/account")
                .withHeader("X-Token", equalTo("VERYSECUREDTOKEN"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalTo("{}"))
                .willReturn(aResponse().withStatus(200).withBody("Welcome back !")));
        stubFor(post("/disconnect").willReturn(aResponse().withStatus(401)));
        stubFor(post("/disconnect")
                .withHeader("X-Token", equalTo("VERYSECUREDTOKEN"))
                .willReturn(aResponse().withStatus(200).withBody("code=400;message=Unknown format")));
        stubFor(post("/disconnect")
                .withHeader("X-Token", equalTo("VERYSECUREDTOKEN"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalTo("{}"))
                .willReturn(aResponse().withStatus(200).withBody("code=200;message=Bien;data={}")));

        doReturn(new ResponseDto(200, "Bien", new JsonObject()))
                .when(jsonService)
                .deserialize("code=200;message=Bien;data={}", ResponseDto.class);
        doReturn(new ResponseDto(400, "Unknown format", null))
                .when(jsonService)
                .deserialize("code=400;message=Unknown format", ResponseDto.class);
    }

    @Test
    void testUnknownHostname() {
        HttpService httpService = httpServiceWithMockedFilter();

        assertThrows(
                Exception.class,
                () -> httpService.request(
                        HttpProtocol.HTTPS, "localhost:8081/hey", HttpMethod.GET, List.of(), "", null));
    }

    @Test
    void testPageNotFoundGet() {
        HttpService httpService = httpServiceWithMockedFilter();

        assertThrows(
                PageNotFound.class,
                () -> httpService.request(
                        HttpProtocol.HTTP, "localhost:8080/hey", HttpMethod.GET, List.of(), "", null));
    }

    @Test
    void testPageGet() throws IOException, URISyntaxException, InterruptedException {
        HttpService httpService = httpServiceWithMockedFilter();

        assertEquals(
                "<p>Home</p>",
                httpService.request(HttpProtocol.HTTP, "localhost:8080/home", HttpMethod.GET, List.of(), "", null));
    }

    @Test
    void testPageGetWithHeaders() throws IOException, URISyntaxException, InterruptedException {
        HttpService httpService = httpServiceWithMockedFilter();

        assertEquals(
                "<p>Home</p>",
                httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/home",
                        HttpMethod.GET,
                        DefaultHttpService.defaultHeaders(),
                        "",
                        null));
    }

    @Test
    void testHttps() {
        HttpService httpService = httpServiceWithMockedFilter();

        assertDoesNotThrow(
                () -> httpService.request(HttpProtocol.HTTPS, "www.google.com", HttpMethod.GET, List.of(), "", null));
    }

    @Test
    void testPostWithoutBody() {
        HttpService httpService = httpServiceWithMockedFilter();

        var headers = DefaultHttpService.defaultHeaders();

        assertThrows(
                IllegalArgumentException.class,
                () -> httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/login",
                        HttpMethod.POST,
                        headers,
                        null,
                        null));
    }

    @Test
    void testUnauthorizedRequest() {
        HttpService httpService = httpServiceWithMockedFilter();

        assertThrows(
                Unauthorized.class,
                () -> httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/login",
                        HttpMethod.POST,
                        DefaultHttpService.defaultHeaders(),
                        "",
                        null));
        assertThrows(
                Unauthorized.class,
                () -> httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/account",
                        HttpMethod.POST,
                        DefaultHttpService.defaultHeaders(),
                        "",
                        null));
        assertThrows(
                Unauthorized.class,
                () -> httpService.request(
                        HttpProtocol.HTTP, "localhost:8080/disconnect", HttpMethod.POST, List.of(), "", null));
    }

    @Test
    void testPostRequest() throws IOException, URISyntaxException, InterruptedException {
        HttpService httpService = httpServiceWithMockedFilter();

        assertEquals(
                "Logged in !",
                httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/login",
                        HttpMethod.POST,
                        DefaultHttpService.defaultHeaders(),
                        "This is a message",
                        null));
        assertEquals(
                "Unknown format",
                httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/account",
                        HttpMethod.POST,
                        DefaultHttpService.tokenHeader("VERYSECUREDTOKEN"),
                        "",
                        null));
        var requestHeadersWithJsonFormat = DefaultHttpService.tokenHeader("VERYSECUREDTOKEN");
        requestHeadersWithJsonFormat.replaceAll(s -> s.replaceAll("text/plain", "application/json"));

        assertEquals(
                "Welcome back !",
                httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/account",
                        HttpMethod.POST,
                        requestHeadersWithJsonFormat,
                        "{}",
                        null));

        assertEquals(
                "code=400;message=Unknown format",
                httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/disconnect",
                        HttpMethod.POST,
                        DefaultHttpService.tokenHeader("VERYSECUREDTOKEN"),
                        "{}",
                        null));

        assertEquals(
                "code=200;message=Bien;data={}",
                httpService.request(
                        HttpProtocol.HTTP,
                        "localhost:8080/disconnect",
                        HttpMethod.POST,
                        requestHeadersWithJsonFormat,
                        "{}",
                        null));
    }

    @Test
    void testUnauthorizedResponseFilter() {
        HttpService httpService = new DefaultHttpService(jsonService);

        assertThrows(
                Unauthorized.class,
                () -> httpService.successfulResponseFilter(401, "", (a, b, c) -> new Unauthorized()));
    }

    @Test
    void testBadRequestResponseFilter() {
        HttpService httpService = new DefaultHttpService(jsonService);

        assertThrows(
                BadRequest.class,
                () -> httpService.successfulResponseFilter(
                        200, "code=400;message=Unknown format", (a, b, c) -> new BadRequest()));
    }

    @Test
    void testSuccessfulResponseFilter() throws PageNotFound, Unauthorized {
        HttpService httpService = new DefaultHttpService(jsonService);

        assertEquals(
                "<p>Home</p>",
                httpService.successfulResponseFilter(200, "<p>Home</p>", (a, b, c) -> new PageNotFound()));
        assertEquals(
                "code=200;message=Bien;data={}",
                httpService.successfulResponseFilter(
                        200, "code=200;message=Bien;data={}", (a, b, c) -> new Unauthorized()));
    }

    HttpService httpServiceWithMockedFilter() {
        HttpService httpService = Mockito.spy(new DefaultHttpService(jsonService));

        doThrow(Exception.class).when(httpService).successfulResponseFilter(anyInt(), anyString(), Mockito.any());
        doThrow(PageNotFound.class).when(httpService).successfulResponseFilter(eq(404), anyString(), Mockito.any());
        doThrow(Unauthorized.class).when(httpService).successfulResponseFilter(eq(401), anyString(), Mockito.any());
        doAnswer(AdditionalAnswers.returnsSecondArg())
                .when(httpService)
                .successfulResponseFilter(eq(200), anyString(), Mockito.any());
        return httpService;
    }

    public static class PageNotFound extends Exception {}

    public static class Unauthorized extends Exception {}

    public static class BadRequest extends Exception {}
}
