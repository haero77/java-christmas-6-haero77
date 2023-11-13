package christmas.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuName;
import christmas.domain.menu.MenuType;
import christmas.domain.order.Order;
import christmas.domain.order.OrderCount;
import christmas.domain.order.OrderLine;
import christmas.domain.order.OrderLines;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputFormatterTest {

    private static final Order ORDER = new Order(new OrderLines(List.of(
            new OrderLine(new Menu(MenuType.MAIN, new MenuName("티본스테이크"), 55000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.MAIN, new MenuName("바비큐립"), 54000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.APPETIZER, new MenuName("초코케이크"), 15000), OrderCount.from(2)),
            new OrderLine(new Menu(MenuType.BEVERAGE, new MenuName("제로콜라"), 3000), OrderCount.from(1)))
    ));

    private final OutputFormatter formatter = new OutputFormatter();

    @DisplayName("혜택 미리 보기 출력 문자열 포맷 검증")
    @Test
    void toTotalBenefitPreviewGuide() {
        // given
        int visitDate = 26;

        // when
        String result = formatter.toTotalBenefitPreview(visitDate);

        // then
        assertThat(result).isEqualTo("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @DisplayName("주문 메뉴 상세 출력 포맷 검증")
    @Test
    void toOrderMenuDetails() {
        // given
        Order order = new Order(new OrderLines(List.of(
                new OrderLine(new Menu(MenuType.APPETIZER, new MenuName("타파스"), 1000), OrderCount.from(1)),
                new OrderLine(new Menu(MenuType.MAIN, new MenuName("제로콜라"), 2000), OrderCount.from(1)))
        ));

        // when
        String result = formatter.toOrderDetail(order);

        // then
        assertThat(result).isEqualTo(
                "<주문 메뉴>" + System.lineSeparator()
                        + "타파스 1개" + System.lineSeparator()
                        + "제로콜라 1개"
        );
    }

    @DisplayName("할인 전 총주문 금액 출력 포맷 검증")
    @Test
    void toTotalOrderAmount() {
        // given
        // when
        String result = formatter.toTotalOrderAmount(ORDER);

        // then
        assertThat(result).isEqualTo("""
                <할인 전 총주문 금액>
                142,000원"""
        );
    }

}