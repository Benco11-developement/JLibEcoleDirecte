package fr.benco11.jlibecoledirecte.lib.http;

import java.net.MalformedURLException;
import java.net.URL;

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
