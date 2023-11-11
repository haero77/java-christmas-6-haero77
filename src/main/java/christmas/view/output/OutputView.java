package christmas.view.output;

import christmas.view.print.Printer;

public class OutputView {

    private final Printer printer;
    private final OutputFormatter formatter;

    public OutputView(Printer printer, OutputFormatter formatter) {
        this.printer = printer;
        this.formatter = formatter;
    }

    public void welcomeClient() {
        printer.printLine(OutputViewMessage.WELCOME);
    }

}
