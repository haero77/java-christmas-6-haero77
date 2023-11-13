package christmas.view.output;

import christmas.domain.event.benefit.BenefitDetails;
import christmas.domain.event.gift.GiftMenu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderLine;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputFormatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String NONE = "없음";

    private static final String BENEFIT_PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    private static final String MENU_DETAIL_GUIDE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_GUIDE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_GUIDE = "<증정 메뉴>";

    private static final String MENU_NAME_COUNT_FORMAT = "%s %d개";

    private static final DecimalFormat AMOUNT_DIGIT_FORMAT = new DecimalFormat("###,###");
    private static final String AMOUNT_SUFFIX = "원";

    public String formatTotalBenefitPreview(int visitDate) {
        return BENEFIT_PREVIEW_FORMAT.formatted(visitDate);
    }

    public String formatOrderDetail(Order order) {
        return new StringBuilder()
                .append(MENU_DETAIL_GUIDE)
                .append(NEW_LINE)
                .append(toOrderDetailFormat(order.getOrderLines()))
                .toString();
    }

    private String toOrderDetailFormat(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(orderLine -> MENU_NAME_COUNT_FORMAT.formatted(
                        orderLine.getMenu().getName().getName(),
                        orderLine.getCount())
                )
                .collect(Collectors.joining(NEW_LINE));
    }

    public String formatTotalOrderAmount(Order order) {
        return new StringBuilder()
                .append(TOTAL_ORDER_AMOUNT_GUIDE)
                .append(NEW_LINE)
                .append(toAmountFormat(order.getTotalOrderAmount().getAmount()))
                .toString();
    }

    private String toAmountFormat(long amount) {
        return AMOUNT_DIGIT_FORMAT.format(amount) + AMOUNT_SUFFIX;
    }

    public String formatGiftMenu(BenefitDetails benefitDetails) {
        return new StringBuilder()
                .append(GIFT_MENU_GUIDE)
                .append(NEW_LINE)
                .append(toGiftMenuNameCountFormat(benefitDetails.getGiftMenu()))
                .toString();
    }

    private String toGiftMenuNameCountFormat(GiftMenu giftMenu) {
        if (giftMenu.hasGift()) {
            return MENU_NAME_COUNT_FORMAT.formatted(giftMenu.getMenuName(), giftMenu.getCount());
        }
        return NONE;
    }

}
