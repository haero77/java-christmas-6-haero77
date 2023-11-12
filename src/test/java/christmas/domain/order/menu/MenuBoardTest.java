package christmas.domain.order.menu;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuBoardTest {

    @DisplayName("메뉴 이름이 존재하는지 확인할 수 있다.")
    @Test
    void validateMenuExistsByName_succeess() {
        // given
        MenuBoard menuBoard = new MenuBoard(
                List.of(new Menu(MenuType.APPETIZER, new MenuName("app1"), 1000))
        );
        List<MenuName> invalidMenuNames = List.of(new MenuName("app1"));

        // when & then
        assertThatCode(() -> menuBoard.validateMenuExistsByName(invalidMenuNames))
                .doesNotThrowAnyException();
    }

    @DisplayName("존재하지 않는 메뉴 이름에 대한 예외처리")
    @Test
    void validateMenuExistsByName() {
        // given
        MenuBoard menuBoard = new MenuBoard(
                List.of(new Menu(MenuType.APPETIZER, new MenuName("app1"), 1000))
        );
        List<MenuName> invalidMenuNames = List.of(new MenuName("invalidMenuName"));

        // when & then
        assertThatThrownBy(() -> menuBoard.validateMenuExistsByName(invalidMenuNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

}