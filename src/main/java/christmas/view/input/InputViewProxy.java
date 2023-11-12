package christmas.view.input;


import static christmas.view.constant.CharacterSymbol.BLANK;

import christmas.domain.order.VisitDate;
import christmas.utils.formatter.ErrorMessageFormatter;
import christmas.view.input.error.InputErrorMessage;
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
        return exceptionHandler.reInput(
                view::inputVisitDate,
                toReInputMessageFormat(InputErrorMessage.VISIT_DATE_ERROR)
        );
    }

    private String toReInputMessageFormat(String message) {
        return ErrorMessageFormatter.addErrorPrefix(message) + BLANK.getLiteral() + InputErrorMessage.RE_INPUT;
    }

}