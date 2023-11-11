package christmas.view.input.error;

import christmas.utils.formatter.ErrorMessageFormatter;
import christmas.view.print.Printer;
import java.util.function.Supplier;

public class InputExceptionHandler {

    private final Printer printer;

    public InputExceptionHandler(Printer printer) {
        this.printer = printer;
    }

    public <T> T handle(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            printer.printLine(ErrorMessageFormatter.format(e.getMessage()));
            return handle(supplier);
        }
    }

}
