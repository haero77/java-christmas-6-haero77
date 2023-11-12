package christmas.domain.order.menu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class MenuBoard {

    private final List<Menu> menu;

    public MenuBoard(List<Menu> menu) {
        this.menu = menu;
    }

    public Optional<Menu> getMenuByName(String menuName) {
        return this.menu.stream()
                .filter(menu -> menu.matchesName(menuName))
                .findAny();
    }

    public void validateMenuExistsByName(List<MenuName> menuNames) {
        if (!existsMenuName(menuNames)) {
            throw new IllegalArgumentException(
                    "Some menu of %s are not exist.".formatted(Arrays.toString(menuNames.toArray()))
            );
        }
    }

    private boolean existsMenuName(List<MenuName> menuNames) {
        return new HashSet<>(getExistingMenuNames()).containsAll(menuNames);
    }

    private List<MenuName> getExistingMenuNames() {
        return this.menu.stream()
                .map(Menu::getName)
                .toList();
    }

}
