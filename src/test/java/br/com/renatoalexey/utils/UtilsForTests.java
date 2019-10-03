package br.com.renatoalexey.utils;

import java.io.*;

public class UtilsForTests {

    public static String readsJsonFromFile(String filePath) throws IOException {

        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file),"UTF-8"));

        String currentLine = "";
        StringBuilder stringBuilder = new StringBuilder();
        while ((currentLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(currentLine);
        }

        return stringBuilder.toString();
    }
}
