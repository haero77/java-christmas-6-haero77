package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("유효하지 않은 날짜 에러 처리")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 1000})
    void invalid_date(int date) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> VisitDate.from(date));
    }

}