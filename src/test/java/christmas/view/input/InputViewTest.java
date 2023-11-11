package christmas.view.input;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.mock.FakePrinter;
import christmas.mock.FakeReader;
import christmas.view.read.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    @DisplayName("방문 날짜 입력 시 숫자외 입력 에러 처리")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", ",", "@@", "31avsd"})
    void visitDate_not_number(String rawInput) {
        // given
        Reader reader = new FakeReader(rawInput);
        InputView inputView = new InputViewImpl(reader, new FakePrinter());

        // when // then
        assertThatIllegalArgumentException()
                .isThrownBy(inputView::inputVisitDate);
    }

}