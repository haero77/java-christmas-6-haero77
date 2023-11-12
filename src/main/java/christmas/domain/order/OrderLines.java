package christmas.domain.order;

import christmas.domain.menu.MenuType;
import java.util.List;
import java.util.Objects;

public class OrderLines {

    private final List<OrderLine> lines;

    public OrderLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public List<OrderLine> getLines() {
        if (Objects.isNull(this.lines)) {
            throw new IllegalStateException("No Lines exists");
        }
        return List.copyOf(this.lines);
    }

    public long getOrderCountByType(MenuType type) {
        return this.lines.stream()
                .filter(line -> line.matchesMenuType(type))
                .mapToInt(OrderLine::getCount)
                .sum();
    }

    public TotalOrderAmount getTotalOrderAmount() {
        return new TotalOrderAmount(calculateTotalOrderAmount());
    }

    private int calculateTotalOrderAmount() {
        return this.lines.stream()
                .mapToInt(OrderLine::getOrderAmount)
                .sum();
    }

}
