package br.com.renatoalexey.utils;

import br.com.renatoalexey.model.CategoryType;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utils {

    public static double getValueFromString(String value) {
        double parseDouble = Double.parseDouble(value.replaceAll("\\.", "").replaceAll(",", ".").replaceAll(" ", ""));
        return parseDouble;
    }

    public static Date getDateFromString(String textDate, String pattern, Locale locale) throws ParseException {
        textDate = textDate.replaceAll(" ", "");
        if(locale == null ) return new SimpleDateFormat(pattern).parse(textDate);
        return new SimpleDateFormat(pattern, locale).parse(textDate);
    }

    public static CategoryType getCategoryTypeFromString(String category) {
        if("null".equals(category) || "".equals(category)) return CategoryType.OUTRAS;
        String textCategory = removeAccentsAndSetsToUpperCase(category);
        CategoryType categoryType = CategoryType.valueOf(removeWhiteSpaceFromBeginningAndEnd(textCategory));
        return categoryType;
    }

    public static String removeWhiteSpaceFromBeginningAndEnd(String input) {
        return input.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
    }

    private static String removeAccentsAndSetsToUpperCase(String str)  {

        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        nfdNormalizedString = nfdNormalizedString.replaceAll("[^\\p{ASCII}]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").toUpperCase();
    }
}
