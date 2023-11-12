package christmas.view.input;


import christmas.domain.error.DomainErrorMessage;
import christmas.domain.order.OrderRequest;
import christmas.view.input.error.InputExceptionHandler;
import christmas.view.input.utils.ReInputMessageFormatter;

public class InputViewProxy implements InputView {

    private final InputExceptionHandler exceptionHandler;
    private final InputViewImpl view;

    public InputViewProxy(InputExceptionHandler exceptionHandler, InputViewImpl view) {
        this.exceptionHandler = exceptionHandler;
        this.view = view;
    }

    @Override
    public int inputVisitDate() {
        return exceptionHandler.reInput(
                view::inputVisitDate,
                ReInputMessageFormatter.formatWithErrorPrefix(DomainErrorMessage.VISIT_DATE_ERROR)
        );
    }

    @Override
    public OrderRequest inputOrderRequest() {
        return exceptionHandler.reInput(
                view::inputOrderRequest,
                ReInputMessageFormatter.formatWithErrorPrefix(DomainErrorMessage.ORDER_ERROR)
        );
    }

}