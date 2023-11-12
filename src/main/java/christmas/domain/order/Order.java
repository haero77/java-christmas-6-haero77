package christmas.domain.order;

import java.util.List;
import java.util.Objects;

public class Order {

    private final OrderLines orderLines;

    public Order(OrderLines orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderLine> getOrderLines() {
        if (Objects.isNull(this.orderLines)) {
            throw new IllegalStateException("No OrderLines Exists");
        }
        return this.orderLines.getLines();
    }

}
