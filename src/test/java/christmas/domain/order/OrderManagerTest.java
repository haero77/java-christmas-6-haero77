package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;
import christmas.domain.menu.MenuName;
import christmas.domain.menu.MenuType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderManagerTest {

    @DisplayName("메뉴 이름과 주문 수량을 통해 주문할 수 있다.")
    @Test
    void order() {
        // given
        MenuBoard menuBoard = new MenuBoard(List.of(
                new Menu(MenuType.APPETIZER, new MenuName("m1"), 1000),
                new Menu(MenuType.MAIN, new MenuName("m2"), 1000),
                new Menu(MenuType.DESSERT, new MenuName("m3"), 1000),
                new Menu(MenuType.BEVERAGE, new MenuName("m4"), 1000)
        ));
        OrderManager orderManager = new OrderManager(menuBoard);
        OrderRequest request = new OrderRequest(List.of(
                new OrderRequest.Form("m1", 1),
                new OrderRequest.Form("m2", 1)
        ));

        // when
        Order order = orderManager.order(request);

        // then
        assertThat(order.getOrderLines())
                .extracting("menu", "count")
                .containsExactlyInAnyOrder(
                        tuple(new Menu(MenuType.APPETIZER, new MenuName("m1"), 1000), 1),
                        tuple(new Menu(MenuType.MAIN, new MenuName("m2"), 1000), 1)
                );
    }

    @DisplayName("존재하지 않는 메뉴 이름 주문 시 예외 처리")
    @Test
    void validateMenuExistsByName() {
        // given
        MenuBoard menuBoard = new MenuBoard(
                List.of(new Menu(MenuType.APPETIZER, new MenuName("app1"), 1000))
        );
        OrderManager orderManager = new OrderManager(menuBoard);
        OrderRequest request = new OrderRequest(List.of(new OrderRequest.Form("invalidMenuName", 1)));

        // when & then
        assertThatThrownBy(() -> orderManager.order(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 메뉴 이름으로 주문 시 예외처리")
    @Test
    void validateMenuNameNotDuplicated() {
        // given
        MenuBoard menuBoard = new MenuBoard(
                List.of(new Menu(MenuType.APPETIZER, new MenuName("app1"), 1000))
        );
        OrderManager orderManager = new OrderManager(menuBoard);
        OrderRequest request = new OrderRequest(List.of(
                new OrderRequest.Form("app1", 1),
                new OrderRequest.Form("app1", 1)
        ));

        // when & then
        assertThatThrownBy(() -> orderManager.order(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문 시 예외 처리")
    @Test
    void validateNotConsistOfOnlyBeverages() {
        // given
        MenuBoard menuBoard = new MenuBoard(List.of(
                new Menu(MenuType.BEVERAGE, new MenuName("menu1"), 1000),
                new Menu(MenuType.BEVERAGE, new MenuName("menu2"), 1000)
        ));
        OrderManager orderManager = new OrderManager(menuBoard);
        OrderRequest request = new OrderRequest(List.of(
                new OrderRequest.Form("menu1", 1),
                new OrderRequest.Form("menu2", 1)
        ));

        // when & then
        assertThatThrownBy(() -> orderManager.order(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("OrderRequest has only Beverages.");
    }

    @DisplayName("메뉴 주문 개수 최대 20개 제한 예외 처리")
    @Test
    void validateOrderCountCriterion_max() {
        // given
        MenuBoard menuBoard = new MenuBoard(List.of(
                new Menu(MenuType.BEVERAGE, new MenuName("menu1"), 1000),
                new Menu(MenuType.MAIN, new MenuName("menu2"), 1000)
        ));
        OrderManager orderManager = new OrderManager(menuBoard);
        OrderRequest request = new OrderRequest(List.of(
                new OrderRequest.Form("menu1", 10),
                new OrderRequest.Form("menu2", 11)
        ));

        // when
        assertThatThrownBy(() -> orderManager.order(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("OrderCount does not meet criterion");
    }

    @DisplayName("메뉴 주문 개수 최소 1개 제한 예외 처리")
    @Test
    void validateOrderCountCriterion_min() {
        // given
        MenuBoard menuBoard = new MenuBoard(List.of(
                new Menu(MenuType.BEVERAGE, new MenuName("menu1"), 1000),
                new Menu(MenuType.MAIN, new MenuName("menu2"), 1000)
        ));
        OrderManager orderManager = new OrderManager(menuBoard);
        OrderRequest request = new OrderRequest(List.of(
                new OrderRequest.Form("menu1", 0),
                new OrderRequest.Form("menu2", 0)
        ));

        // when
        assertThatThrownBy(() -> orderManager.order(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("OrderCount does not meet criterion");
    }

}