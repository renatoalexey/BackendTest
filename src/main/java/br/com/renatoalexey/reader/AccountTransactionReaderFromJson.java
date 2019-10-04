package br.com.renatoalexey.reader;

import br.com.renatoalexey.connect.ConnectsToJsonAPI;
import br.com.renatoalexey.model.AccountTransactionDTO;
import br.com.renatoalexey.reports.AccountTransactionReportsExecutor;
import br.com.renatoalexey.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.Iterator;

public class AccountTransactionReaderFromJson {

    private AccountTransactionReportsExecutor accountTransactionReportsExecutor;

    private ConnectsToJsonAPI connectsToJsonAPI;

    public AccountTransactionReaderFromJson(ConnectsToJsonAPI connectsToJsonAPI,
                                            AccountTransactionReportsExecutor accountTransactionReportsExecutor) {
        this.connectsToJsonAPI = connectsToJsonAPI;
        this.accountTransactionReportsExecutor = accountTransactionReportsExecutor;
    }

    public void generatesReportsFromJSON(String apiUrl) throws ParseException, IOException {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = connectsToJsonAPI.connectsToAPI(apiUrl);
            String json = connectsToJsonAPI.readJsonFromUrl(httpURLConnection);

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(json);

            JsonArray jsonArray = (JsonArray) jsonObject.get("pagamentos");

            Iterator<JsonElement> iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                JsonElement jsonElement = iterator.next();
                AccountTransactionDTO accountTransactionDTO = transformsJsonElementIntoAccountTransactionDTO(jsonElement);
                accountTransactionReportsExecutor.buildsAllReportsData(accountTransactionDTO);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
        }

    }

    private AccountTransactionDTO transformsJsonElementIntoAccountTransactionDTO(JsonElement jsonElement) throws ParseException {
        AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        accountTransactionDTO.setDate(Utils.getDateFromString(transformsJsonElementToString(jsonObject.get("data")), "dd/MMM", null));
        accountTransactionDTO.setDescription(transformsJsonElementToString(jsonObject.get("descricao")));
        accountTransactionDTO.setCategoryType(Utils.getCategoryTypeFromString(transformsJsonElementToString(jsonObject.get("categoria"))));
        accountTransactionDTO.setValue(getValueFromJsonElement(jsonObject.get("valor")));

        return accountTransactionDTO;
    }

    private double getValueFromJsonElement(JsonElement jsonElement) {
        return Utils.getValueFromString(transformsJsonElementToString(jsonElement));
    }

    private String transformsJsonElementToString(JsonElement jsonElement) {
        return String.valueOf(jsonElement).replaceAll("\"", "");
    }


}
