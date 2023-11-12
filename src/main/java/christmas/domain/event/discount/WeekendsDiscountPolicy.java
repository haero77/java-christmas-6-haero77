package christmas.domain.event.discount;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import christmas.domain.event.Reservation;
import christmas.domain.menu.MenuType;
import java.time.DayOfWeek;
import java.util.List;

public class WeekendsDiscountPolicy implements DiscountPolicy {

    private static final List<DayOfWeek> DISCOUNT_DAY = List.of(FRIDAY, SATURDAY);
    private static final int DISCOUNT_AMOUNT_PER_MENU_COUNT = 2023;
    private static final MenuType DISCOUNT_MENU_TYPE = MenuType.MAIN;
    private static final DiscountType TYPE = DiscountType.WEEKENDS;

    private final Reservation reservation;

    public WeekendsDiscountPolicy(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public DiscountAmount discount() {
        if (meetsDayRangeCriterion()) {
            return new DiscountAmount(TYPE, calculateDiscountAmount());
        }
        return new DiscountAmount(TYPE, 0);
    }

    private boolean meetsDayRangeCriterion() {
        return DISCOUNT_DAY.contains(this.reservation.getVisitDayOfWeek());
    }

    private long calculateDiscountAmount() {
        return this.reservation.getOrderCountByType(DISCOUNT_MENU_TYPE) * DISCOUNT_AMOUNT_PER_MENU_COUNT;
    }

}
