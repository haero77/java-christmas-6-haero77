package christmas.domain.order.menu;

import java.util.List;
import java.util.Optional;

public class MenuBoard {

    private final List<Menu> menu;

    private MenuBoard(List<Menu> menu) {
        this.menu = menu;
    }

    public static MenuBoard createWithInitialized() {
        return new MenuBoard(MenuBoardInitializer.initializeMenu());
    }

    public Optional<Menu> findMenuByName(String menuName) {
        return this.menu.stream()
                .filter(menu -> menu.matchesName(menuName))
                .findAny();
    }

}
