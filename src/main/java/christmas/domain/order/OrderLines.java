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

    public long getMenuCountByType(MenuType type) {
        return this.lines.stream()
                .map(OrderLine::getMenu)
                .filter(menu -> menu.matchesType(type))
                .count();
    }

}
