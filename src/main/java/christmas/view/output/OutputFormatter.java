package christmas.view.output;

public class OutputFormatter {

    private static final String BENEFIT_PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public String toTotalBenefitPreview(int visitDate) {
        return BENEFIT_PREVIEW_FORMAT.formatted(visitDate);
    }

}
