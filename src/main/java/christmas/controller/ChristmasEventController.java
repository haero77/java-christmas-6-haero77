package christmas.controller;

import christmas.domain.error.DomainErrorMessage;
import christmas.domain.event.EventBenefitManager;
import christmas.domain.event.Reservation;
import christmas.domain.event.VisitDate;
import christmas.domain.event.benefit.TotalBenefit;
import christmas.domain.menu.MenuBoardInitializer;
import christmas.domain.order.Order;
import christmas.domain.order.OrderManager;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.function.Supplier;

public class ChristmasEventController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasEventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        welcomeClient();

        VisitDate visitDate = inputVisitDate();
        Order order = inputOrder();

        outputTotalBenefit(computeTotalBenefit(visitDate, order));
    }

    private <T> T process(Supplier<T> supplier, ErrorConsumer consumer) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            consumer.accept();
            return process(supplier, consumer);
        }
    }

    private VisitDate inputVisitDate() {
        return process(
                () -> VisitDate.from(inputView.inputVisitDate()),
                () -> outputView.showErrorMessageWithReInput(DomainErrorMessage.VISIT_DATE_ERROR)
        );
    }

    private Order inputOrder() {
        return process(
                this::processOrder,
                () -> outputView.showErrorMessageWithReInput(DomainErrorMessage.ORDER_ERROR)
        );
    }

    private Order processOrder() {
        OrderManager orderManager = new OrderManager(MenuBoardInitializer.initialize());
        return orderManager.order(inputView.inputOrderRequest());
    }

    private void welcomeClient() {
        outputView.welcomeClient();
    }

    private void outputTotalBenefit(TotalBenefit totalBenefit) {
        outputView.showTotalBenefit(totalBenefit);
    }

    private TotalBenefit computeTotalBenefit(VisitDate visitDate, Order order) {
        EventBenefitManager manager = new EventBenefitManager(new Reservation(visitDate, order));
        return manager.calculateTotalBenefit();
    }

}
