package fr.benco11.jlibecoledirecte.lib.http;

import fr.benco11.jlibecoledirecte.lib.utils.Pair;
import fr.benco11.jlibecoledirecte.lib.utils.TriFunction;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface HttpService {
    <T extends Exception> String request(
            HttpProtocol protocol,
            String address,
            HttpMethod method,
            List<String> headers,
            String body,
            TriFunction<Integer, Integer, String, T> toThrow)
            throws IOException, URISyntaxException, InterruptedException, T;

    <T extends Exception> String post(
            String address, List<String> headers, String body, TriFunction<Integer, Integer, String, T> toThrow)
            throws T, IOException, URISyntaxException, InterruptedException;

    Pair<Boolean, Optional<ResponseDto>> isRequestSuccessful(int responseCode, String responseBody);
}
