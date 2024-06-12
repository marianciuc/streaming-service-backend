package zut.wi.streamingservice.utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class URLValidator {

    public static boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            return "http".equals(url.getProtocol()) || "https".equals(url.getProtocol());
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
