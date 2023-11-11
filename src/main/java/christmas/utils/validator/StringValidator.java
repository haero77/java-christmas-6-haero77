package christmas.utils.validator;

import christmas.utils.formatter.ErrorMessageFormatter;

public class StringValidator {

    public static void validateInteger(String literal) {
        validateInteger(literal, ErrorMessageFormatter.format("literal %s is not an integer.".formatted(literal)));
    }

    public static void validateInteger(String literal, String message) {
        if (!isInteger(literal)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean isInteger(String literal) {
        try {
            Integer.parseInt(literal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
