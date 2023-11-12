package christmas.domain.order.menu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MenuBoard {

    private final List<Menu> menu;

    public MenuBoard(List<Menu> menu) {
        this.menu = menu;
    }

    public void validateMenuExistsByName(List<MenuName> menuNames) {
        if (!existsMenuName(menuNames)) {
            throw new IllegalArgumentException(
                    "Some menu of %s are not exist.".formatted(Arrays.toString(menuNames.toArray()))
            );
        }
    }

    public List<Menu> getMenuByName(List<MenuName> menuNames) {
        return menuNames.stream()
                .map(this::getMenuByName)
                .toList();
    }

    private boolean existsMenuName(List<MenuName> menuNames) {
        return new HashSet<>(getExistingMenuNames()).containsAll(menuNames);
    }

    private List<MenuName> getExistingMenuNames() {
        return this.menu.stream()
                .map(Menu::getName)
                .toList();
    }

    public Menu getMenuByName(MenuName menuName) {
        return this.menu.stream()
                .filter(menu -> menu.equalsByName(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No Menu exist for menuName=%s".formatted(menuName)));
    }

}
