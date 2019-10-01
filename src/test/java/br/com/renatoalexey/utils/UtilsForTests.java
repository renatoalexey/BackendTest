package br.com.renatoalexey.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UtilsForTests {

    public static String readsJsonFromFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine = "";
        StringBuilder stringBuilder = new StringBuilder();
        while ((currentLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(currentLine);
        }

        return stringBuilder.toString();
    }
}
