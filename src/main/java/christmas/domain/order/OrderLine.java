package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;

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

    public boolean matchesMenuType(MenuType type) {
        return this.menu.matchesType(type);
    }

    public int getCount() {
        return this.count.getCount();
    }

}
