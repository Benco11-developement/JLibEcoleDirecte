package fr.benco11.jlibecoledirecte.lib.http;

import fr.benco11.jlibecoledirecte.lib.utils.TriFunction;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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

    <T extends Exception> String successfulResponseFilter(
            int responseCode, String responseBody, TriFunction<Integer, Integer, String, T> toThrow) throws T;
}
