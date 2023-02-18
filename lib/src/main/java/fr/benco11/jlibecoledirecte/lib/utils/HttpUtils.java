package fr.benco11.jlibecoledirecte.lib.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static fr.benco11.jlibecoledirecte.lib.utils.HttpUtils.HttpProtocol.HTTPS;

public class HttpUtils {
    public enum Endpoints {
        API("api.ecoledirecte.com/v3/"),
        LOGIN(API + "login.awp"),
        STUDENT(API + "eleves/%s/"),
        SETTINGS(API + "logins/%s.awp?verbe=get"),

        GRADE(STUDENT + "notes.awp?verbe=get"),
        SCHOOL_LIFE(STUDENT + "viescolaire.awp?verbe=get");

        private final String url;

        Endpoints(String url) {
            this.url = url;
        }

        public String asString(Object... args) {
            return url.formatted(args);
        }

    }

    public enum HttpProtocol {
        HTTP("http://"), HTTPS("https://");

        private final String protocolURI;

        HttpProtocol(String protocolURI) {
            this.protocolURI = protocolURI;
        }

        public URI getUri(String address) throws URISyntaxException {
            return new URI(protocolURI + address);
        }
    }

    public enum HttpMethod {
        GET, POST
    }

    public static String postPlainText(String address,
            String text) throws URISyntaxException, IOException, InterruptedException {
        return httpRequest(address, HttpMethod.POST, Optional.of(HttpRequest.BodyPublishers.ofString(text)));
    }

    public static String postPlainText(String address, String text,
            List<String> headers) throws URISyntaxException, IOException, InterruptedException {
        return httpRequest(HTTPS, address, HttpMethod.POST, headers, Optional.of(HttpRequest.BodyPublishers.ofString(text)));
    }

    public static String httpRequest(String address, HttpMethod method,
            Optional<HttpRequest.BodyPublisher> body) throws URISyntaxException, IOException, InterruptedException {
        return httpRequest(HTTPS, address, method, new ArrayList<>(), body);
    }

    public static String httpRequest(HttpProtocol protocol, String address,
            HttpMethod method, List<String> headers,
            Optional<HttpRequest.BodyPublisher> body) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                                                        .uri(protocol.getUri(address))
                                                        .headers(headers.toArray(String[]::new));
        HttpRequest request = (switch(method) {
            case GET -> requestBuilder.GET();
            case POST ->
                    requestBuilder.POST(body.orElseThrow(() -> new IllegalArgumentException("Une requête POST nécessite un BodyPublisher")));
        }).build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                                                  .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static List<String> defaultHeaders() {
        return new ArrayList<>(Arrays.asList("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/110.0"));
    }

    public static List<String> tokenHeader(String token) {
        List<String> r = defaultHeaders();
        defaultHeaders().addAll(Arrays.asList("X-Token", token));
        return r;
    }
}
