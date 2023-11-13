package christmas.view.output;

import christmas.domain.order.Order;
import christmas.domain.order.OrderLine;
import java.util.List;
import java.util.stream.Collectors;

public class OutputFormatter {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String BENEFIT_PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    private static final String MENU_DETAIL_GUIDE = "<주문 메뉴>";

    private static final String ORDER_DETAIL_FORMAT = "%s %d개";

    public String toTotalBenefitPreview(int visitDate) {
        return BENEFIT_PREVIEW_FORMAT.formatted(visitDate);
    }

    public String toOrderDetail(Order order) {
        return new StringBuilder()
                .append(MENU_DETAIL_GUIDE)
                .append(NEW_LINE)
                .append(toOrderDetailFormat(order.getOrderLines()))
                .toString();
    }

    private String toOrderDetailFormat(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(orderLine -> ORDER_DETAIL_FORMAT.formatted(
                        orderLine.getMenu().getName().getName(),
                        orderLine.getCount())
                )
                .collect(Collectors.joining(NEW_LINE));
    }

}
