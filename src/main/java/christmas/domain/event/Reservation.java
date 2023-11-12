package christmas.domain.event;

import christmas.domain.menu.MenuType;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.util.Objects;

public class Reservation {

    private final VisitDate date;
    private final Order order;

    public Reservation(VisitDate date, Order order) {
        this.date = date;
        this.order = order;
    }

    public int getVisitDate() {
        if (Objects.isNull(this.date)) {
            throw new IllegalStateException("No VisitDate Exists");
        }
        return this.date.getDate();
    }

    public long getMenuCountByType(MenuType type) {
        return this.order.getMenuCountByType(type);
    }

    public DayOfWeek getVisitDayOfWeek() {
        return this.date.getDayOfWeek();
    }

}
