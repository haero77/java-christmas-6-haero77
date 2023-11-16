package christmas.domain.order;

import christmas.domain.menu.MenuBoard;

public class OrderManager {

    private static final int MIN_ORDER_COUNT = 1;
    private static final int MAX_ORDER_COUNT = 20;

    private final MenuBoard menuBoard;

    public OrderManager(MenuBoard menuBoard) {
        this.menuBoard = menuBoard;
    }

    public Order order(OrderRequest request) {
        validateCriteria(request);
        return new Order(new OrderLines(request.toOrderLines(this.menuBoard)));
    }

    private void validateCriteria(OrderRequest request) {
        OrderValidator.validateMenuExistsByName(request.getMenuNames(), this.menuBoard);
        OrderValidator.validateMenuNameNotDuplicated(request.getMenuNames());
        OrderValidator.validateNotConsistOfOnlyBeverages(request, this.menuBoard);
        OrderValidator.validateOrderCountCriterion(request.getTotalOrderCount(), MIN_ORDER_COUNT, MAX_ORDER_COUNT);
    }

}
