package fr.benco11.jlibecoledirecte.lib.http;

import static fr.benco11.jlibecoledirecte.lib.http.HttpProtocol.HTTPS;

import fr.benco11.jlibecoledirecte.lib.json.JsonService;
import fr.benco11.jlibecoledirecte.lib.utils.TriFunction;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DefaultHttpService implements HttpService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final JsonService jsonService;

    public DefaultHttpService(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public <T extends Exception> String request(
            HttpProtocol protocol,
            String address,
            HttpMethod method,
            List<String> headers,
            String body,
            TriFunction<Integer, Integer, String, T> toThrow)
            throws IOException, URISyntaxException, InterruptedException, T {
        if (headers.size() % 2 != 0)
            throw new IllegalArgumentException("Le format de headers HTTP clé/valeur n'est pas respecté !");
        HttpRequest.Builder requestBuilder =
                HttpRequest.newBuilder().uri(protocol.getUrl(address).toURI());
        if (!headers.isEmpty()) {
            requestBuilder.headers(headers.toArray(String[]::new));
        }
        HttpRequest request;
        if (method == HttpMethod.POST) {
            if (body == null) {
                throw new IllegalArgumentException("Une requête POST nécessite un body !");
            }
            request = requestBuilder
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
        } else {
            request = requestBuilder.GET().build();
        }
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int responseCode = response.statusCode();
        String responseBody = response.body();

        return successfulResponseFilter(responseCode, responseBody, toThrow);
    }

    @Override
    public <T extends Exception> String postHttps(
            String address, List<String> headers, String body, TriFunction<Integer, Integer, String, T> toThrow)
            throws T, IOException, URISyntaxException, InterruptedException {
        return request(HTTPS, address, HttpMethod.POST, headers, body, toThrow);
    }

    @Override
    public <T extends Exception> String successfulResponseFilter(
            int responseCode, String responseBody, TriFunction<Integer, Integer, String, T> toThrow) throws T {
        boolean isSuccessful = responseCode == 200 && !responseBody.isEmpty();
        Optional<ResponseDto> responseDto = Optional.empty();
        if (isSuccessful) {
            responseDto = Optional.ofNullable(jsonService.deserialize(responseBody, ResponseDto.class));
            isSuccessful = responseDto.isEmpty() || responseDto.get().code() == 200;
        }
        if (!isSuccessful && toThrow != null) {
            throw toThrow.apply(
                    responseCode,
                    responseDto.map(ResponseDto::code).orElse(-1),
                    responseDto.map(ResponseDto::message).orElse(null));
        }
        return responseBody;
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
}
