package Helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHelper {
    public static Object makeRequestGetResponse(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            StringBuilder resp = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                resp.append(inputLine);
            } reader.close();

            return(resp);
        } else {
            return(responseCode);
        }
    }
}
