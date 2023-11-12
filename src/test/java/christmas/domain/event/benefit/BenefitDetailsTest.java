package christmas.domain.event.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.discount.DiscountAmount;
import christmas.domain.event.discount.DiscountDetails;
import christmas.domain.event.discount.DiscountType;
import christmas.domain.event.gift.GiftMenu;
import christmas.domain.event.gift.GiftMenuType;
import christmas.domain.order.TotalOrderAmount;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitDetailsTest {

    private static final Map<DiscountType, DiscountAmount> DISCOUNT_AMOUNTS = Map.of(
            DiscountType.SPECIAL, new DiscountAmount(DiscountType.SPECIAL, 1000L),
            DiscountType.X_MAS, new DiscountAmount(DiscountType.X_MAS, 2000L),
            DiscountType.WEEKDAYS, new DiscountAmount(DiscountType.WEEKDAYS, 3000L),
            DiscountType.WEEKENDS, new DiscountAmount(DiscountType.WEEKENDS, 4000L)
    );

    @DisplayName("총 혜택 금액을 계산할 수 있다.")
    @Test
    void calculateTotalBenefitAmount() {
        // given
        DiscountDetails discountDetails = new DiscountDetails(DISCOUNT_AMOUNTS);
        GiftMenu giftMenu = new GiftMenu(GiftMenuType.CHAMPAGNE, 1);

        BenefitDetails benefitDetails = new BenefitDetails(discountDetails, giftMenu);

        // when
        TotalBenefitAmount totalBenefitAmount = benefitDetails.calculateTotalBenefitAmount();

        // then
        assertThat(totalBenefitAmount.getAmount()).isEqualTo(10_000L + 25_000L);
    }

    @DisplayName("총 주문 금액과 총 할인 금액을 통해 예상 결제 금액을 계산할 수 있다.")
    @Test
    void calculateExpectedPayment() {
        // given
        DiscountDetails discountDetails = new DiscountDetails(DISCOUNT_AMOUNTS);
        GiftMenu giftMenu = new GiftMenu(GiftMenuType.NONE, 0);
        BenefitDetails benefitDetails = new BenefitDetails(discountDetails, giftMenu);

        TotalOrderAmount totalOrderAmount = new TotalOrderAmount(100_000);

        // when
        ExpectedPayment expectedPayment = benefitDetails.calculateExpectedPayment(totalOrderAmount);

        // then
        assertThat(expectedPayment.getAmount()).isEqualTo(90_000L);
    }

    @DisplayName("총 할인 금액이 총 주문 금액을 넘으면 예상 결제 금액은 0원이다..")
    @Test
    void calculateExpectedPayment_0() {
        // given
        DiscountDetails discountDetails = new DiscountDetails(DISCOUNT_AMOUNTS);
        GiftMenu giftMenu = new GiftMenu(GiftMenuType.CHAMPAGNE, 1);
        BenefitDetails benefitDetails = new BenefitDetails(discountDetails, giftMenu);

        TotalOrderAmount totalOrderAmount = new TotalOrderAmount(100_000);

        // when
        ExpectedPayment expectedPayment = benefitDetails.calculateExpectedPayment(totalOrderAmount);

        // then
        assertThat(expectedPayment.getAmount()).isEqualTo(90_000L);
    }

}