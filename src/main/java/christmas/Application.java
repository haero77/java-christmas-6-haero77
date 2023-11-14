package christmas;

import christmas.controller.ChristmasEventController;
import christmas.view.input.error.InputExceptionHandler;
import christmas.view.input.InputView;
import christmas.view.input.InputViewImpl;
import christmas.view.input.InputViewProxy;
import christmas.view.output.OutputFormatter;
import christmas.view.output.OutputView;
import christmas.view.print.ConsolePrinter;
import christmas.view.print.Printer;
import christmas.view.read.ConsoleReader;
import christmas.view.read.Reader;

public class Application {

    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();

        InputView inputView = setUpInputView(printer, reader);
        OutputView outputView = setUpOutputView(printer);

        run(inputView, outputView);
    }

    private static void run(InputView inputView, OutputView outputView) {
        new ChristmasEventController(inputView, outputView).run();
    }

    private static InputView setUpInputView(Printer printer, Reader reader) {
        InputExceptionHandler inputExceptionHandler = new InputExceptionHandler(printer);
        return new InputViewProxy(inputExceptionHandler, new InputViewImpl(reader, printer));
    }

    private static OutputView setUpOutputView(Printer printer) {
        OutputFormatter outputFormatter = new OutputFormatter();
        return new OutputView(printer, outputFormatter);
    }

}
