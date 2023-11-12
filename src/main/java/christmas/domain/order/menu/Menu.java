package christmas.domain.order.menu;

public class Menu {

    private final MenuType type;
    private final MenuName name;
    private final int price;

    public Menu(MenuType type, MenuName name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public boolean matchesName(String name) {
        return this.name.equals(name);
    }

    public MenuName getName() {
        return this.name;
    }

}