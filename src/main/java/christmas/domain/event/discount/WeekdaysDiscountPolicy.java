package christmas.domain.event.discount;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import christmas.domain.event.Reservation;
import christmas.domain.menu.MenuType;
import java.time.DayOfWeek;
import java.util.List;

public class WeekdaysDiscountPolicy implements DiscountPolicy {

    private static final List<DayOfWeek> DISCOUNT_DAY = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
    private static final int DISCOUNT_AMOUNT_PER_MENU_COUNT = 2023;
    private static final MenuType DISCOUNT_MENU_TYPE = MenuType.DESSERT;
    private static final DiscountType TYPE = DiscountType.WEEKDAYS;

    private final Reservation reservation;

    public WeekdaysDiscountPolicy(Reservation reservation) {
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
        return this.reservation.getMenuCountByType(DISCOUNT_MENU_TYPE) * DISCOUNT_AMOUNT_PER_MENU_COUNT;
    }

}
