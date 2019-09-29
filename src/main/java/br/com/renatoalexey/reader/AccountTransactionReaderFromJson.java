package br.com.renatoalexey.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AccountTransactionReaderFromJson {

    public void connectsToAPI(String apiUrl) throws IOException {
        HttpURLConnection httpURLConnection = null;
        URL url = new URL("");

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        String json = readJsonFromUrl(url);



    }

    public  void transformsJSONIntoDTO (String json) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(json);

        JsonArray jsonArray = (JsonArray)jsonObject.get("pagamentos");

        Iterator<JsonElement> iterator = jsonArray.iterator();

        while(iterator.hasNext()) {
            JsonElement jsonElement = iterator.next();
            transformsJsonElementIntoAccountTransactionDTO(jsonElement);
        }


    }

    public void transformsJsonElementIntoAccountTransactionDTO(JsonElement jsonElement) {
        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();

        for (Map.Entry<String, JsonElement> entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }
    }


    public static String readJsonFromUrl(URL url) throws IOException {
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
