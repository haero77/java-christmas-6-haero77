package christmas.domain.event.benefit;

import christmas.domain.event.discount.DiscountDetails;
import christmas.domain.event.gift.GiftMenu;
import christmas.domain.order.TotalOrderAmount;

public class BenefitDetails {

    private final DiscountDetails discountDetails;
    private final GiftMenu giftMenu;

    public BenefitDetails(DiscountDetails discountDetails, GiftMenu giftMenu) {
        this.discountDetails = discountDetails;
        this.giftMenu = giftMenu;
    }

    public TotalBenefitAmount calculateTotalBenefitAmount() {
        return new TotalBenefitAmount(discountDetails.calculateTotalDiscountAmount() + giftMenu.getMenuPrice());
    }

    public ExpectedPayment calculateExpectedPayment(TotalOrderAmount totalOrderAmount) {
        return ExpectedPayment.ofAmount(totalOrderAmount.getAmount() - discountDetails.calculateTotalDiscountAmount());
    }

}
