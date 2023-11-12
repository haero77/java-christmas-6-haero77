package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.Reservation;
import christmas.domain.event.VisitDate;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekendsDiscountPolicyTest {

    private static final Order ORDER = new Order(new OrderLines(List.of(
            new OrderLine(new Menu(MenuType.APPETIZER, new MenuName("m1"), 1000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.MAIN, new MenuName("m2"), 2000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.DESSERT, new MenuName("m3"), 3000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.BEVERAGE, new MenuName("m4"), 4000), OrderCount.from(1))
    )));

    @DisplayName("금, 토요일을 제외한 요일의 할인 금액은 0이다.")
    @ParameterizedTest
    @ValueSource(ints = {
            3, 4, 5, 6, 7,
            17, 18, 19, 28, 31
    })
    void discount_except_weekdays(int visitDate) {
        // given
        Reservation reservation = new Reservation(VisitDate.from(visitDate), ORDER);
        DiscountPolicy discountPolicy = new WeekendsDiscountPolicy(reservation);

        // when
        DiscountAmount result = discountPolicy.discount();

        // then
        assertThat(result.getAmount()).isEqualTo(0);
    }

    @DisplayName("메인 메뉴 1개 당 2023원 할인된다.")
    @Test
    void discount() {
        // given
        Order ORDER = new Order(new OrderLines(List.of(
                new OrderLine(new Menu(MenuType.MAIN, new MenuName("m1"), 1000), OrderCount.from(1)),
                new OrderLine(new Menu(MenuType.MAIN, new MenuName("m2"), 2000), OrderCount.from(1)),
                new OrderLine(new Menu(MenuType.MAIN, new MenuName("m3"), 3000), OrderCount.from(10)),
                new OrderLine(new Menu(MenuType.DESSERT, new MenuName("m4"), 4000), OrderCount.from(10))
        )));
        Reservation reservation = new Reservation(VisitDate.from(1), ORDER);
        DiscountPolicy discountPolicy = new WeekendsDiscountPolicy(reservation);

        // when
        DiscountAmount result = discountPolicy.discount();

        // then
        assertThat(result.getAmount()).isEqualTo(2023 * 12);
    }

}