package christmas.domain.order.menu;

public class Menu {

    private final MenuType type;
    private final String name;
    private final int price;

    public Menu(MenuType type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public boolean matchesName(String name) {
        return this.name.equals(name);
    }

}