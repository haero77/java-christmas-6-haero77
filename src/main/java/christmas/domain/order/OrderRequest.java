package christmas.domain.order;

import christmas.domain.menu.MenuBoard;
import christmas.domain.menu.MenuName;
import java.util.List;

public class OrderRequest {

    private final List<Form> forms;

    public OrderRequest(List<Form> forms) {
        this.forms = forms;
    }

    public List<OrderLine> toOrderLines(MenuBoard menuBoard) {
        return this.forms.stream()
                .map(form -> form.toOrderLine(menuBoard))
                .toList();
    }

    public List<MenuName> getMenuNames() {
        return this.forms.stream()
                .map(form -> new MenuName(form.getMenuName()))
                .toList();
    }

    public int getTotalOrderCount() {
        return this.forms.stream()
                .mapToInt(Form::getOrderCount)
                .sum();
    }

    public static class Form {

        private final String menuName;
        private final int orderCount;

        public Form(String menuName, int orderCount) {
            this.menuName = menuName;
            this.orderCount = orderCount;
        }

        public OrderLine toOrderLine(MenuBoard menuBoard) {
            return new OrderLine(
                    menuBoard.getMenuByName(new MenuName(this.menuName)),
                    OrderCount.from(this.orderCount)
            );
        }

        public String getMenuName() {
            return this.menuName;
        }

        public int getOrderCount() {
            return orderCount;
        }

    }

}
