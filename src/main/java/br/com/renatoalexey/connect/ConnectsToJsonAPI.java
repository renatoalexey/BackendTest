package br.com.renatoalexey.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class ConnectsToJsonAPI {

    public HttpURLConnection connectsToAPI(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");

        if (httpURLConnection.getResponseCode() != 200) {
            throw new RuntimeException("Falha ao conectar na API " + apiUrl
            + " : HTTP error code : "
                    + httpURLConnection.getResponseCode());
        }

        return httpURLConnection;

    }

    public String readJsonFromUrl(HttpURLConnection url) throws IOException {
        String html = "";
        StringBuilder sB = new StringBuilder();
        BufferedReader bR = new BufferedReader(new InputStreamReader(url.getInputStream(), Charset.forName("UTF-8")));
        while ((html = bR.readLine()) != null)
            sB.append(html);

        return sB.toString();
    }

}
