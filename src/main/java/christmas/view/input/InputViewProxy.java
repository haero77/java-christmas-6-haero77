package christmas.view.input;


import christmas.domain.order.VisitDate;
import christmas.view.input.error.InputExceptionHandler;

public class InputViewProxy implements InputView {

    private final InputExceptionHandler exceptionHandler;
    private final InputViewImpl view;

    public InputViewProxy(InputExceptionHandler exceptionHandler, InputViewImpl view) {
        this.exceptionHandler = exceptionHandler;
        this.view = view;
    }

    @Override
    public VisitDate inputVisitDate() {
        return exceptionHandler.handle(view::inputVisitDate);
    }

}