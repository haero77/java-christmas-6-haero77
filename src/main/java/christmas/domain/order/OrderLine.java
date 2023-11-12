package christmas.domain.order;

import christmas.domain.menu.Menu;

public class OrderLine {

    private final Menu menu;
    private final OrderCount count;

    public OrderLine(Menu menu, OrderCount count) {
        this.menu = menu;
        this.count = count;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getOrderAmount() {
        return menu.getPrice() * count.getCount();
    }

}
