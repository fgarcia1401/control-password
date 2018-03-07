package br.com.fernando.control_password.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPassword {

    public static boolean validatePassword(String password) {
        return (containsCharacter(password)
                && containsNumeric(password)
                && containsSpecialCharacter(password)
                && containsUppercase(password));
    }

    public static boolean containsNumeric(String s) {
        Pattern p = Pattern.compile(".*\\d+.*$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static boolean containsCharacter(String s) {
        Pattern p = Pattern.compile("[A-z]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static boolean containsSpecialCharacter(String s) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static boolean containsUppercase(String s) {
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(s);
        return m.find();
    }
}
