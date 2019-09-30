package br.com.renatoalexey.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectsToJsonAPI {

    public URL connectsToAPI(String apiUrl) throws IOException {
        HttpURLConnection httpURLConnection = null;
        URL url = new URL(apiUrl);

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        String json = readJsonFromUrl(url);

        return url;
    }

    public String readJsonFromUrl(URL url) throws IOException {
        if (url == null)
            throw new RuntimeException("URL é null");

        String html = null;
        StringBuilder sB = new StringBuilder();
        BufferedReader bR = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((html = bR.readLine()) != null)
            sB.append(html);


        return sB.toString();
    }

}
