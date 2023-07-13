package fr.benco11.jlibecoledirecte.lib.utils;

import static fr.benco11.jlibecoledirecte.lib.utils.HttpUtils.HttpProtocol.HTTPS;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpUtils {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static <T extends Exception> String postPlainText(String address, String text, T toThrow)
            throws T, URISyntaxException, IOException, InterruptedException {
        return httpRequest(address, HttpMethod.POST, HttpRequest.BodyPublishers.ofString(text), toThrow);
    }

    public static <T extends Exception> String postPlainText(
            String address, String text, List<String> headers, T toThrow)
            throws T, URISyntaxException, IOException, InterruptedException {
        return httpRequest(
                HTTPS, address, HttpMethod.POST, headers, HttpRequest.BodyPublishers.ofString(text), toThrow);
    }

    public static <T extends Exception> String httpRequest(
            String address, HttpMethod method, HttpRequest.BodyPublisher body, T toThrow)
            throws T, URISyntaxException, IOException, InterruptedException {
        return httpRequest(HTTPS, address, method, defaultHeaders(), body, toThrow);
    }

    public static HttpResponse<String> httpRequest(
            HttpProtocol protocol,
            String address,
            HttpMethod method,
            List<String> headers,
            HttpRequest.BodyPublisher body)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder requestBuilder =
                HttpRequest.newBuilder().uri(protocol.getUrl(address).toURI()).headers(headers.toArray(String[]::new));
        HttpRequest request;
        if (method == HttpMethod.POST) {
            if (body == null) {
                throw new IllegalArgumentException("Une requête POST nécessite un BodyPublisher !");
            }
            request = requestBuilder.POST(body).build();
        } else {
            request = requestBuilder.GET().build();
        }
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static <T extends Exception> String httpRequest(
            HttpProtocol protocol,
            String address,
            HttpMethod method,
            List<String> headers,
            HttpRequest.BodyPublisher body,
            T toThrow)
            throws T, URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response = httpRequest(protocol, address, method, headers, body);
        if (response.statusCode() != 200 && toThrow != null) {
            throw toThrow;
        }
        return response.body();
    }

    public static List<String> defaultHeaders() {
        return new ArrayList<>(Arrays.asList(
                "Content-Type",
                "text/plain",
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/110.0"));
    }

    public static List<String> tokenHeader(String token) {
        List<String> r = defaultHeaders();
        r.addAll(Arrays.asList("X-Token", token));
        return r;
    }

    public enum Endpoints {
        API("api.ecoledirecte.com/v3/"),
        LOGIN(API.url + "login.awp"),
        STUDENT(API.url + "eleves/%s/"),
        SETTINGS(API.url + "logins/%s.awp?verbe=get"),

        GRADE(STUDENT.url + "notes.awp?verbe=get"),
        SCHOOL_LIFE(STUDENT.url + "viescolaire.awp?verbe=get");

        private final String url;

        Endpoints(String url) {
            this.url = url;
        }

        public String asString(Object... args) {
            return url.formatted(args);
        }
    }

    public enum HttpProtocol {
        HTTP("http://"),
        HTTPS("https://");

        private final String protocolUri;

        HttpProtocol(String protocolUri) {
            this.protocolUri = protocolUri;
        }

        public URL getUrl(String address) throws MalformedURLException {
            return new URL(protocolUri + address);
        }
    }

    public enum HttpMethod {
        GET,
        POST
    }
}
