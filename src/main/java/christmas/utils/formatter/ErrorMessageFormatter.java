package christmas.utils.formatter;

import static christmas.view.constant.CharacterSymbol.BLANK;

public class ErrorMessageFormatter {

    private static final String ERROR_PREFIX = "[ERROR]";

    private ErrorMessageFormatter() {
    }

    public static String addErrorPrefix(String message) {
        return ERROR_PREFIX + BLANK.getLiteral() + message;
    }

}
