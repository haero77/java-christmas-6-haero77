package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountDetailsTest {

    @DisplayName("총 할인 금액을 계산할 수 있다.")
    @Test
    void calculateTotalDiscountAmount() {
        // given
        Map<DiscountType, DiscountAmount> discountAmounts = Map.of(
                DiscountType.SPECIAL, new DiscountAmount(DiscountType.SPECIAL, 1000L),
                DiscountType.X_MAS, new DiscountAmount(DiscountType.X_MAS, 2000L),
                DiscountType.WEEKDAYS, new DiscountAmount(DiscountType.WEEKDAYS, 3000L),
                DiscountType.WEEKENDS, new DiscountAmount(DiscountType.WEEKENDS, 4000L)
        );

        DiscountDetails discountDetails = new DiscountDetails(discountAmounts);

        // when
        long result = discountDetails.calculateTotalDiscountAmount();

        // then
        assertThat(result).isEqualTo(10_000L);
    }

}