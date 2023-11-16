package christmas.domain.event.gift;

import christmas.domain.order.TotalOrderAmount;

public class GiftManager {

    private static final int MIN_ORDER_AMOUNT = 120_000;
    private static final GiftMenu DEFAULT_GIFT_MENU = new GiftMenu(GiftMenuType.CHAMPAGNE, 1);
    private static final GiftMenu NONE_GIFT_MENU = new GiftMenu(GiftMenuType.NONE, 0);

    private final TotalOrderAmount totalOrderAmount;

    public GiftManager(TotalOrderAmount totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public GiftMenu apply() {
        if (meetsMinOrderAmountCriterion()) {
            return DEFAULT_GIFT_MENU;
        }
        return NONE_GIFT_MENU;
    }

    private boolean meetsMinOrderAmountCriterion() {
        return totalOrderAmount.isGreaterOrEqualThan(MIN_ORDER_AMOUNT);
    }

}