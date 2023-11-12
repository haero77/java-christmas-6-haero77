package christmas.view.input;

import static christmas.view.constant.CharacterSymbol.COMMA;
import static christmas.view.constant.CharacterSymbol.HYPHEN;

import christmas.utils.convert.StringConverter;
import christmas.utils.validator.StringValidator;
import christmas.view.input.error.InputErrorMessage;
import java.util.List;

public class InputFormatValidator {

    private InputFormatValidator() {
    }

    public static void validateVisitDateFormat(String rawInput) {
        StringValidator.validateNotEmpty(rawInput, InputErrorMessage.VISIT_DATE_ERROR);
        StringValidator.validateInteger(rawInput, InputErrorMessage.VISIT_DATE_ERROR);
    }

    public static void validateOrderRequestInputFormat(String rawInput) {
        StringValidator.validateNotEmpty(rawInput, InputErrorMessage.ORDER_ERROR);
        validateNotStartOrEndsWithComma(rawInput);
        validateCommaFormat(rawInput);
        validateHyphenFormat(rawInput);
    }

    private static void validateCommaFormat(String rawInput) {
        validateNotStartOrEndsWithComma(rawInput);

        List<String> splitByComma = StringConverter.spiltWith(rawInput, COMMA.getLiteral());

        if (splitByComma.isEmpty()) {
            throw new IllegalArgumentException(InputErrorMessage.ORDER_ERROR);
        }

        splitByComma.forEach(literal -> StringValidator.validateNotEmpty(literal, InputErrorMessage.ORDER_ERROR));
    }

    private static void validateNotStartOrEndsWithComma(String rawInput) {
        if (rawInput.startsWith(COMMA.getLiteral()) || rawInput.endsWith(COMMA.getLiteral())) {
            throw new IllegalArgumentException(InputErrorMessage.ORDER_ERROR);
        }
    }

    private static void validateHyphenFormat(String rawInput) {
        List<String[]> splitByHyphen = StringConverter.spiltWith(rawInput, COMMA.getLiteral())
                .stream()
                .map(literal -> literal.split(HYPHEN.getLiteral()))
                .toList();

        splitByHyphen.forEach(literals -> {
            validateLiteralArraySize(literals, 2);
            StringValidator.validateNotEmpty(literals[0]);
            StringValidator.validateNotEmpty(literals[1]);
            StringValidator.validateInteger(literals[1]);
        });
    }

    private static void validateLiteralArraySize(String[] literals, int size) {
        if (literals.length != size) {
            throw new IllegalArgumentException();
        }
    }

}
