package christmas.view.output;

import static christmas.view.constant.CharacterSymbol.NEW_LINE;

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
        printer.printLine(addNewLineSuffix(formatter.formatTotalBenefitPreview(totalBenefit.getVisitDate())));
        printer.printLine(addNewLineSuffix(formatter.formatOrderDetail(totalBenefit.getOrder())));
        printer.printLine(addNewLineSuffix(formatter.formatTotalOrderAmount(totalBenefit.getOrder())));
        printer.printLine(addNewLineSuffix(formatter.formatGiftMenu(totalBenefit.getBenefitDetails())));
        printer.printLine(addNewLineSuffix(formatter.formatBenefitDetails(totalBenefit.getBenefitDetails())));
        printer.printLine(addNewLineSuffix(formatter.formatTotalBenefitAmount(totalBenefit.getBenefitDetails())));
        printer.printLine(addNewLineSuffix(
                formatter.formatExpectedPayment(totalBenefit.getBenefitDetails(), totalBenefit.getReservation()))
        );
        printer.printLine(formatter.formatBadgeRank(totalBenefit.getBadgeRank()));
    }

    private String addNewLineSuffix(String literal) {
        return literal + NEW_LINE.getLiteral();
    }

}
