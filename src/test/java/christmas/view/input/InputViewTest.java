package christmas.view.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

import christmas.domain.order.OrderRequest;
import christmas.mock.FakePrinter;
import christmas.mock.FakeReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    private InputView getInputView(String rawInput) {
        return new InputViewImpl(new FakeReader(rawInput), new FakePrinter());
    }

    @DisplayName("방문 날짜 입력 시 숫자외 입력 에러 처리")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", ",", "@@", "31avsd"})
    void visitDate_not_number(String rawInput) {
        // given
        InputView inputView = getInputView(rawInput);

        // when // then
        assertThatIllegalArgumentException()
                .isThrownBy(inputView::inputVisitDate);
    }

    @DisplayName("메뉴와 수량을 통해 주문 입력")
    @Test
    void inputOrderRequest() {
        // given
        InputView inputView = getInputView("티본스테이크-1,바비큐립-7,초코케이크-2,제로콜라-31");

        // when
        OrderRequest result = inputView.inputOrderRequest();

        // then
        assertThat(result.getForms())
                .hasSize(4)
                .extracting("menuName", "orderCount")
                .containsExactlyInAnyOrder(
                        tuple("티본스테이크", 1),
                        tuple("바비큐립", 7),
                        tuple("초코케이크", 2),
                        tuple("제로콜라", 31)
                );
    }

    @DisplayName("메뉴 주문 요청 시 문자열 포맷 예외 처리")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ", ",", ",,", ", ,",
            ",레드와인1", ",,레드와인1", "레드와인1,", ",레드와인1,",
            "레드와인1", "-1", "레드와인-", "레드와인", "1", "레드와인-1a",
            "레드와인-1, ,초코케이크1"
    })
    void validateOrderRequestInputFormat_null_empty(String rawInput) {
        // given
        InputView inputView = getInputView(rawInput);

        // when & then
        assertThatThrownBy(inputView::inputOrderRequest)
                .isInstanceOf(IllegalArgumentException.class);
    }

}