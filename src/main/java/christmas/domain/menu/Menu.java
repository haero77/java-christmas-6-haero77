package christmas.domain.menu;

import java.util.Objects;

public class Menu {

    private final MenuType type;
    private final MenuName name;
    private final int price;

    public Menu(MenuType type, MenuName name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public MenuName getName() {
        return this.name;
    }

    public boolean equalsByName(MenuName name) {
        return this.name.equals(name);
    }

    public boolean isBeverageType() {
        return this.type.isBeverage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return price == menu.price && type == menu.type && Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, price);
    }

}