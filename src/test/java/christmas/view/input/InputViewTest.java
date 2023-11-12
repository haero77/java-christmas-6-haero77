package christmas.view.input;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.mock.FakePrinter;
import christmas.mock.FakeReader;
import christmas.view.read.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    private InputView getInputView(String rawInput) {
        Reader reader = new FakeReader(rawInput);
        return new InputViewImpl(reader, new FakePrinter());
    }

    @DisplayName("방문 날짜 입력 시 숫자외 입력 에러 처리")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", ",", "@@", "31avsd"})
    void visitDate_not_number(String rawInput) {
        // given
        InputView inputView = getInputView(rawInput);
        System.out.println("rawInput = " + rawInput);

        // when // then
        assertThatIllegalArgumentException()
                .isThrownBy(inputView::inputVisitDate);
    }

}