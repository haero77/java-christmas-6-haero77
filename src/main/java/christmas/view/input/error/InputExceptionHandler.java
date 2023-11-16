package christmas.view.input.error;

import christmas.view.print.Printer;
import java.util.function.Supplier;

public class InputExceptionHandler {

    private final Printer printer;

    public InputExceptionHandler(Printer printer) {
        this.printer = printer;
    }

    public <T> T reInput(Supplier<T> supplier, String errorMessage) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            printer.printLine(errorMessage);
            return reInput(supplier, errorMessage);
        }
    }

}
