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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountPolicyTest {

    private static final Order ORDER = new Order(new OrderLines(List.of(
            new OrderLine(new Menu(MenuType.APPETIZER, new MenuName("m1"), 1000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.MAIN, new MenuName("m2"), 2000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.DESSERT, new MenuName("m3"), 3000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.BEVERAGE, new MenuName("m4"), 4000), OrderCount.from(1))
    )));

    @DisplayName("이벤트 달력에 별이 있는 날(3, 10, 17, 24, 31, 25)을 제외한 할인 금액은 0이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 13, 26, 30})
    void discount_except_weekdays(int visitDate) {
        // given
        Reservation reservation = new Reservation(VisitDate.from(visitDate), ORDER);
        DiscountPolicy discountPolicy = new SpecialDiscountPolicy(reservation);

        // when
        DiscountAmount result = discountPolicy.discount();

        // then
        assertThat(result.getAmount()).isEqualTo(0);
    }

    @DisplayName("이벤트 달력에 별이 있는 날(3, 10, 17, 24, 31, 25)의 할인 금액은 1000원이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    void discount(int visitDate) {
        // given
        Reservation reservation = new Reservation(VisitDate.from(visitDate), ORDER);
        DiscountPolicy discountPolicy = new SpecialDiscountPolicy(reservation);

        // when
        DiscountAmount result = discountPolicy.discount();

        // then
        assertThat(result.getAmount()).isEqualTo(1000);
    }

}