package christmas.view.output;

import christmas.domain.order.Order;
import christmas.domain.order.OrderLine;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputFormatter {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String BENEFIT_PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    private static final String MENU_DETAIL_GUIDE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_GUIDE = "<할인 전 총주문 금액>";

    private static final String ORDER_DETAIL_FORMAT = "%s %d개";

    private static final DecimalFormat AMOUNT_DIGIT_FORMAT = new DecimalFormat("###,###");
    private static final String AMOUNT_SUFFIX = "원";

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

    public String toTotalOrderAmount(Order order) {
        return new StringBuilder()
                .append(TOTAL_ORDER_AMOUNT_GUIDE)
                .append(NEW_LINE)
                .append(toAmountFormat(order.getTotalOrderAmount().getAmount()))
                .toString();
    }

    private String toAmountFormat(long amount) {
        return AMOUNT_DIGIT_FORMAT.format(amount) + AMOUNT_SUFFIX;
    }

}
