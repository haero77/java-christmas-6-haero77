package christmas.domain.event.benefit;

import christmas.domain.event.discount.TotalDiscountAmount;
import christmas.domain.event.gift.GiftMenu;

public class TotalBenefitCalculator {

    private final TotalDiscountAmount totalDiscountAmount;
    private final GiftMenu giftMenu;

    public TotalBenefitCalculator(TotalDiscountAmount totalDiscountAmount, GiftMenu giftMenu) {
        this.totalDiscountAmount = totalDiscountAmount;
        this.giftMenu = giftMenu;
    }

    public TotalBenefitAmount calculate() {
        return new TotalBenefitAmount(totalDiscountAmount.getAmount() + giftMenu.getMenuPrice());
    }

}
