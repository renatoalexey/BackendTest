package br.com.renatoalexey.utils;

import br.com.renatoalexey.model.CategoryType;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {

    public static double getValueFromString(String value) {
        double parseDouble = 0;
        try {
            parseDouble = Double.parseDouble(value.replaceAll("\\.", "").replaceAll(",", ".").replaceAll(" ", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return parseDouble;
    }

    public static Date getDateFromString(String textDate, String pattern, Locale locale) throws ParseException {
        textDate = textDate.replaceAll(" ", "");
        if(locale == null ) return new SimpleDateFormat(pattern).parse(textDate);
        return new SimpleDateFormat(pattern, locale).parse(textDate);
    }

    public static CategoryType getCategoryTypeFromString(String category) {
        CategoryType categoryType = null;
        try {
            categoryType = CategoryType.valueOf(removeAccentsAndSetsToUpperCase(category));
        } catch (IllegalArgumentException e) {
            return null;
        }

        return categoryType;
    }

    private static String removeAccentsAndSetsToUpperCase(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").toUpperCase();
    }
}
