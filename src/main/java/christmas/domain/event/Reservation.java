package christmas.domain.event;

import christmas.domain.menu.MenuType;
import christmas.domain.order.Order;
import christmas.domain.order.TotalOrderAmount;
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

    public long getOrderCountByType(MenuType type) {
        return this.order.getOrderCountByType(type);
    }

    public DayOfWeek getVisitDayOfWeek() {
        return this.date.getDayOfWeek();
    }

    public TotalOrderAmount getTotalOrderAmount() {
        return this.order.getTotalOrderAmount();
    }

}
