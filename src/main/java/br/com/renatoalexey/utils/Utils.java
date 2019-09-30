package br.com.renatoalexey.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utils {

    public static String removeAccentsAndSetsToUpperCase(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").toUpperCase();
    }
}
