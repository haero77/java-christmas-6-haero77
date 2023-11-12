package christmas.domain.event.discount;

import christmas.domain.common.Amount;
import java.util.Map;

public class DiscountDetails {

    private final Map<DiscountType, DiscountAmount> discountAmounts;

    public DiscountDetails(Map<DiscountType, DiscountAmount> discountAmounts) {
        this.discountAmounts = discountAmounts;
    }

    public long calculateTotalDiscountAmount() {
        return discountAmounts.values()
                .stream()
                .mapToLong(Amount::getAmount)
                .sum();
    }

}
