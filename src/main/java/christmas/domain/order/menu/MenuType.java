package christmas.domain.order.menu;

public enum MenuType {

    APPETIZER,
    MAIN,
    DESSERT,
    BEVERAGE;

    public boolean isBeverage() {
        return this == BEVERAGE;
    }

}
