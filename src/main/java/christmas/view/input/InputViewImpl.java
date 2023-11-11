package christmas.view.input;

import christmas.domain.order.VisitDate;
import christmas.utils.validator.StringValidator;
import christmas.view.input.error.InputErrorMessage;
import christmas.view.print.Printer;
import christmas.view.read.Reader;

public class InputViewImpl implements InputView {

    private final Reader reader;
    private final Printer printer;

    public InputViewImpl(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    @Override
    public VisitDate inputVisitDate() {
        printer.printLine(InputGuideMessage.ORDER_DATE_INPUT_GUIDE.getMessage());
        String rawInput = reader.readLine();

        validateOrderDate(rawInput);

        return VisitDate.from(Integer.parseInt(rawInput));
    }

    private void validateOrderDate(String rawInput) {
        StringValidator.validateInteger(rawInput, InputErrorMessage.ORDER_ERROR);
    }

}
