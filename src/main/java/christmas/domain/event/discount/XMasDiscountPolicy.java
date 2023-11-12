package christmas.domain.event.discount;

import christmas.domain.event.Reservation;
import java.time.LocalDateTime;

public class XMasDiscountPolicy implements DiscountPolicy {

    private static final LocalDateTime START_DATE_INCLUSIVE = LocalDateTime.of(2023, 12, 1, 0, 0 ,0);
    private static final LocalDateTime END_DATE_EXCLUSIVE = LocalDateTime.of(2023, 12, 26, 0, 0 ,0);

    private static final int DISCOUNT_INITIAL_AMOUNT = 1000;
    private static final int DAILY_INCREMENT_AMOUNT = 100;

    private final DiscountType type = DiscountType.X_MAS;
    private final Reservation reservation;

    public XMasDiscountPolicy(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public DiscountAmount discount() {
        if (!meetsDateRange(reservation.getVisitDate())) {
            return DiscountAmount.from(0);
        }
        return DiscountAmount.from(calculateDiscountAmount());
    }

    private int calculateDiscountAmount() {
        return DISCOUNT_INITIAL_AMOUNT +
                (reservation.getVisitDate() - START_DATE_INCLUSIVE.getDayOfMonth()) * DAILY_INCREMENT_AMOUNT;
    }

    private boolean meetsDateRange(int visitDate) {
        return visitDate >= START_DATE_INCLUSIVE.getDayOfMonth()
                && visitDate < END_DATE_EXCLUSIVE.getDayOfMonth();
    }

}
