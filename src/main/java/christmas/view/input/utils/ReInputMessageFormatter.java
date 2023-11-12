package christmas.view.input.utils;

import static christmas.view.constant.CharacterSymbol.BLANK;

import christmas.utils.formatter.ErrorMessageFormatter;

public class ReInputMessageFormatter {

    private static final String RE_INPUT = "다시 입력해 주세요.";

    private ReInputMessageFormatter() {
    }

    public static String formatWithErrorPrefix(String message) {
        return ErrorMessageFormatter.addErrorPrefix(message) + BLANK.getLiteral() + RE_INPUT;
    }

}
