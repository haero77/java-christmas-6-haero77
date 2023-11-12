package christmas.domain.event.discount;

import christmas.domain.event.Reservation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscountManager {

    private final Reservation reservation;

    public DiscountManager(Reservation reservation) {
        this.reservation = reservation;
    }

    public DiscountDetails apply() {
        return new DiscountDetails(discount());
    }

    private Map<DiscountType, DiscountAmount> discount() {
        return initializeDiscountPolicies()
                .stream()
                .map(DiscountPolicy::discount)
                .collect(Collectors.toMap(
                        DiscountAmount::getDiscountType,
                        discountAmount -> discountAmount
                ));
    }

    private List<DiscountPolicy> initializeDiscountPolicies() {
        return List.of(
                new XMasDiscountPolicy(reservation),
                new WeekendsDiscountPolicy(reservation),
                new WeekdaysDiscountPolicy(reservation),
                new SpecialDiscountPolicy(reservation)
        );
    }

}
