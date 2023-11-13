package christmas.view.output;

import christmas.domain.event.benefit.TotalBenefit;
import christmas.view.input.utils.ReInputMessageFormatter;
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

    public void showErrorMessageWithReInput(String message) {
        printer.printLine(ReInputMessageFormatter.formatWithErrorPrefix(message));
    }

    public void showTotalBenefit(TotalBenefit totalBenefit) {
        printer.printLine(formatter.formatTotalBenefitPreview(totalBenefit.getVisitDate()));
        printer.printEmptyLine();

        printer.printLine(formatter.formatOrderDetail(totalBenefit.getOrder()));
        printer.printEmptyLine();

        printer.printLine(formatter.formatTotalOrderAmount(totalBenefit.getOrder()));
        printer.printEmptyLine();
    }

}
