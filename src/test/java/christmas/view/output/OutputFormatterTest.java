package christmas.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputFormatterTest {

    @DisplayName("혜택 미리 보기 출력 문자열 포맷 검증")
    @Test
    void toTotalBenefitPreviewGuide() {
        // given
        int visitDate = 26;
        OutputFormatter formatter = new OutputFormatter();

        // when
        String result = formatter.toTotalBenefitPreview(visitDate);

        // then
        assertThat(result).isEqualTo("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

}