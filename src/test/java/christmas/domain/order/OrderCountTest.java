package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderCountTest {

    @DisplayName("주문 수량이 1개 미만일 때 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void count_under_1ea(int count) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderCount.from(count));
    }

}