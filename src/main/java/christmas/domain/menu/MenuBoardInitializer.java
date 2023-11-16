package christmas.domain.menu;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class MenuBoardInitializer {

    private MenuBoardInitializer() {
    }

    public static MenuBoard initialize() {
        return new MenuBoard(initializeMenu());
    }

    private static List<Menu> initializeMenu() {
        return Stream.of(
                        initializeAppetizer(),
                        initializeMain(),
                        initializeDessert(),
                        initializeBeverage()
                )
                .flatMap(Collection::stream)
                .toList();
    }

    private static List<Menu> initializeAppetizer() {
        return List.of(
                new Menu(MenuType.APPETIZER, new MenuName("양송이수프"), 6000),
                new Menu(MenuType.APPETIZER, new MenuName("타파스"), 5500),
                new Menu(MenuType.APPETIZER, new MenuName("시저샐러드"), 8000)
        );
    }

    private static List<Menu> initializeMain() {
        return List.of(
                new Menu(MenuType.MAIN, new MenuName("티본스테이크"), 55000),
                new Menu(MenuType.MAIN, new MenuName("바비큐립"), 54000),
                new Menu(MenuType.MAIN, new MenuName("해산물파스타"), 35000),
                new Menu(MenuType.MAIN, new MenuName("크리스마스파스타"), 25000)
        );
    }

    private static List<Menu> initializeDessert() {
        return List.of(
                new Menu(MenuType.DESSERT, new MenuName("초코케이크"), 15000),
                new Menu(MenuType.DESSERT, new MenuName("아이스크림"), 5000)
        );
    }

    private static List<Menu> initializeBeverage() {
        return List.of(
                new Menu(MenuType.BEVERAGE, new MenuName("제로콜라"), 3000),
                new Menu(MenuType.BEVERAGE, new MenuName("레드와인"), 60000),
                new Menu(MenuType.BEVERAGE, new MenuName("샴페인"), 25000)
        );
    }

}
