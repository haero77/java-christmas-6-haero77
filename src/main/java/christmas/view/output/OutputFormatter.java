package christmas.view.output;

import static christmas.view.constant.CharacterSymbol.BLANK;
import static christmas.view.constant.CharacterSymbol.HYPHEN;

import christmas.domain.event.benefit.BenefitDetails;
import christmas.domain.event.discount.DiscountAmount;
import christmas.domain.event.discount.DiscountType;
import christmas.domain.event.gift.GiftMenu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderLine;
import christmas.view.constant.CharacterSymbol;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputFormatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String NONE = "없음";

    private static final String BENEFIT_PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    private static final String MENU_DETAIL_GUIDE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_GUIDE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_GUIDE = "<증정 메뉴>";
    public static final String BENEFIT_DETAIL_GUIDE = "<혜택 내역>";
    public static final String TOTAL_BENEFIT_AMOUNT_GUIDE = "<총혜택 금액>";

    private static final String GIFT_EVENT = "증정 이벤트";

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

    private String toBenefitAmountFormat(long amount) {
        if (amount == 0) {
            return toAmountFormat(amount);
        }
        return HYPHEN.getLiteral() + toAmountFormat(amount);
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

    public String formatBenefitDetails(BenefitDetails benefitDetails) {
        return new StringBuilder()
                .append(BENEFIT_DETAIL_GUIDE)
                .append(NEW_LINE)
                .append(toBenefitTypeAmountFormat(benefitDetails))
                .toString();
    }

    private String toBenefitTypeAmountFormat(BenefitDetails benefitDetails) {
        return addGiftEventSuffix(toDiscountDetailFormat(benefitDetails), benefitDetails.getGiftMenu());
    }

    private String addGiftEventSuffix(String discountDetailFormat, GiftMenu giftMenu) {
        if (giftMenu.hasGift()) {
            return discountDetailFormat
                    + NEW_LINE
                    + toBenefitDescriptionAmountFormat(GIFT_EVENT, giftMenu.getMenuPrice());
        }
        return discountDetailFormat + NONE;
    }

    private String toDiscountDetailFormat(BenefitDetails benefitDetails) {
        Map<DiscountType, DiscountAmount> discountAmounts = new EnumMap<>(
                benefitDetails.getDiscountDetails().getDiscountAmounts()
        );
        return discountAmounts.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isAmountZero())
                .map(entry -> toBenefitDescriptionAmountFormat(entry.getKey().getDescription(),
                        entry.getValue().getAmount())
                )
                .collect(Collectors.joining(NEW_LINE));
    }

    private String toBenefitDescriptionAmountFormat(String description, long amount) {
        return description
                + CharacterSymbol.COLON.getLiteral() + BLANK.getLiteral()
                + toBenefitAmountFormat(amount);
    }

    public String formatTotalBenefitAmount(BenefitDetails benefitDetails) {
        return new StringBuilder()
                .append(TOTAL_BENEFIT_AMOUNT_GUIDE)
                .append(NEW_LINE)
                .append(toBenefitAmountFormat(benefitDetails.getTotalBenefitAmount().getAmount()))
                .toString();
    }

}
