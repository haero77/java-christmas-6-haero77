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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class XMasDiscountPolicyTest {

    private static final Order ORDER = new Order(new OrderLines(List.of(
            new OrderLine(new Menu(MenuType.APPETIZER, new MenuName("m1"), 1000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.MAIN, new MenuName("m2"), 2000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.DESSERT, new MenuName("m3"), 3000), OrderCount.from(1)),
            new OrderLine(new Menu(MenuType.BEVERAGE, new MenuName("m4"), 4000), OrderCount.from(1))
    )));

    @DisplayName("방문 날짜가 2023.12.1 ~ 2023.12.25에 해당하지 않는 경우 할인 금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void dateCriterion(int date) {
        // given
        Reservation reservation = new Reservation(VisitDate.from(date), ORDER);
        DiscountPolicy discountPolicy = new XMasDiscountPolicy(reservation);

        // when
        DiscountAmount result = discountPolicy.discount();

        // then
        assertThat(result.getAmount()).isEqualTo(0);
    }
    
    @DisplayName("할인 금액은 1,000원으로 시작하여 25일 다가올수록 날마다 100원씩 증가한다.")
    @ParameterizedTest
    @CsvSource({"1,1000", "2,1100", "25,3400"})
    void discount(int visitDate, int discountAmount) {
        // given
        Reservation reservation = new Reservation(VisitDate.from(visitDate), ORDER);
        DiscountPolicy discountPolicy = new XMasDiscountPolicy(reservation);

        // when
        DiscountAmount result = discountPolicy.discount();

        // then
        assertThat(result.getAmount()).isEqualTo(discountAmount);
    }

}