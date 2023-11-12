package christmas.view.input;

import christmas.domain.order.OrderRequest;
import christmas.domain.order.VisitDate;

public interface InputView {

    VisitDate inputVisitDate();

    OrderRequest inputOrderRequest();

}
