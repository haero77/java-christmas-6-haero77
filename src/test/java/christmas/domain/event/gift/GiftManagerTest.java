package christmas.domain.event.gift;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.TotalOrderAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GiftManagerTest {

    @DisplayName("할인 전 총 주문 금액이 12만원 이상일 때, 샴페인을 1개 증정한다.")
    @Test
    void applyBenefit() {
        // given
        GiftManager manager = new GiftManager(new TotalOrderAmount(120_000));

        // when
        GiftMenu giftMenu = manager.apply();

        // then
        assertThat(giftMenu.getType()).isEqualTo(GiftMenuType.CHAMPAGNE);
        assertThat(giftMenu.getCount()).isEqualTo(1);
    }

    @DisplayName("할인 전 총 주문 금액이 12만원 미만일 때, 증정은 없다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 1_000, 119_999})
    void applyBenefit_no_fit(int amount) {
        // given
        GiftManager manager = new GiftManager(new TotalOrderAmount(amount));

        // when
        GiftMenu giftMenu = manager.apply();

        // then
        assertThat(giftMenu.getType()).isEqualTo(GiftMenuType.NONE);
        assertThat(giftMenu.getCount()).isEqualTo(0);
    }

}