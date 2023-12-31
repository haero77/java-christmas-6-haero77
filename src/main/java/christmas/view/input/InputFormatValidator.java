package christmas.view.input;

import static christmas.view.constant.CharacterSymbol.COMMA;
import static christmas.view.constant.CharacterSymbol.HYPHEN;

import christmas.utils.convert.StringConverter;
import christmas.utils.validator.StringValidator;
import java.util.List;

public class InputFormatValidator {

    private InputFormatValidator() {
    }

    public static void validateVisitDateFormat(String rawInput) {
        StringValidator.validateNotEmpty(rawInput);
        StringValidator.validateInteger(rawInput);
    }

    public static void validateOrderRequestInputFormat(String rawInput) {
        StringValidator.validateNotEmpty(rawInput);
        validateNotStartOrEndsWithComma(rawInput);
        validateCommaFormat(rawInput);
        validateHyphenFormat(rawInput);
    }

    private static void validateCommaFormat(String rawInput) {
        validateNotStartOrEndsWithComma(rawInput);

        List<String> splitByComma = StringConverter.spiltWith(rawInput, COMMA.getLiteral());

        if (splitByComma.isEmpty()) {
            throw new IllegalArgumentException("String %s is invalid Order Request Format");
        }

        splitByComma.forEach(StringValidator::validateNotEmpty);
    }

    private static void validateNotStartOrEndsWithComma(String rawInput) {
        if (rawInput.startsWith(COMMA.getLiteral()) || rawInput.endsWith(COMMA.getLiteral())) {
            throw new IllegalArgumentException("String %s is start or end with COMMA".formatted(rawInput));
        }
    }

    private static void validateHyphenFormat(String rawInput) {
        List<String[]> splitByHyphen = toOrderRequestInputFormat(rawInput);

        int menuNameIndex = 0;
        int orderCountIndex = 1;

        splitByHyphen.forEach(literals -> {
            validateLiteralArraySize(literals, 2);
            StringValidator.validateNotEmpty(literals[menuNameIndex]);
            StringValidator.validateNotEmpty(literals[orderCountIndex]);
            StringValidator.validateInteger(literals[orderCountIndex]);
        });
    }

    private static List<String[]> toOrderRequestInputFormat(String rawInput) {
        return StringConverter.spiltWith(rawInput, COMMA.getLiteral())
                .stream()
                .map(literal -> literal.split(HYPHEN.getLiteral()))
                .toList();
    }

    private static void validateLiteralArraySize(String[] literals, int size) {
        if (literals.length != size) {
            throw new IllegalArgumentException("size not match.");
        }
    }

}
