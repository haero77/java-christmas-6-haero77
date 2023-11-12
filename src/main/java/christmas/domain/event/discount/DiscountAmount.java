package christmas.domain.event.discount;

import christmas.domain.common.Amount;

public class DiscountAmount extends Amount {

    private final DiscountType discountType;

    public DiscountAmount(DiscountType discountType, long amount) {
        super(amount);
        this.discountType = discountType;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

}
