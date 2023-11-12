package christmas.domain.order;

import christmas.domain.order.menu.Menu;
import christmas.domain.order.menu.MenuBoard;
import christmas.domain.order.menu.MenuName;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class OrderValidator {

    private OrderValidator() {
    }

    public static void validateMenuExistsByName(List<MenuName> menuNames, MenuBoard menuBoard) {
        menuBoard.validateMenuExistsByName(menuNames);
    }

    public static void validateMenuNameNotDuplicated(List<MenuName> menuNames) {
        if (duplicatedMenuNameExists(menuNames)) {
            throw new IllegalArgumentException(
                    "Some menu names of %s are duplicated".formatted(Arrays.toString(menuNames.toArray()))
            );
        }
    }

    private static boolean duplicatedMenuNameExists(List<MenuName> menuNames) {
        return Set.copyOf(menuNames).size() != menuNames.size();
    }

    public static void validateNotConsistOfOnlyBeverages(OrderRequest request, MenuBoard menuBoard) {
        if (hasOnlyBeverages(request, menuBoard)) {
            throw new IllegalArgumentException("OrderRequest has only Beverages.");
        }
    }

    private static boolean hasOnlyBeverages(OrderRequest request, MenuBoard menuBoard) {
        return menuBoard.getMenuByName(request.getMenuNames())
                .stream()
                .allMatch(Menu::isBeverageType);
    }

    public static void validateOrderCountCriterion(int totalOrderCount, int minOrderCount, int maxOrderCount) {
        if (!meetsTotalOrderCountCriterion(totalOrderCount, minOrderCount, maxOrderCount)) {
            throw new IllegalArgumentException("OrderCount does not meet criterion");
        }
    }

    private static boolean meetsTotalOrderCountCriterion(int totalOrderCount, int minOrderCount, int maxOrderCount) {
        return totalOrderCount >= minOrderCount && totalOrderCount <= maxOrderCount;
    }

}
