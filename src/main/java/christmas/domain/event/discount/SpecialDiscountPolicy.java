package christmas.domain.event.discount;

import christmas.domain.event.Reservation;
import java.time.LocalDate;
import java.util.List;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final List<LocalDate> DISCOUNT_DATES = List.of(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31)
    );

    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final DiscountType TYPE = DiscountType.SPECIAL;

    private final Reservation reservation;

    public SpecialDiscountPolicy(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public DiscountAmount discount() {
        if (meetsDateRangeCriterion()) {
            return new DiscountAmount(TYPE, DEFAULT_DISCOUNT_AMOUNT);
        }
        return new DiscountAmount(TYPE, 0);
    }

    private boolean meetsDateRangeCriterion() {
        return DISCOUNT_DATES.stream()
                .map(LocalDate::getDayOfMonth)
                .anyMatch(date -> date == this.reservation.getVisitDate());
    }

}
