package christmas.domain.event.discount;

public enum DiscountType {

    X_MAS("크리스마스 디데이 할인"),
    WEEKDAYS("평일 할인"),
    WEEKENDS("주말 할인"),
    SPECIAL("특별 할인");

    private final String description;

    DiscountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
