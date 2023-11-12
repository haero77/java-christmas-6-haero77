package christmas.domain.event.gift;

public enum GiftMenuType {

    CHAMPAGNE("샴페인"),
    NONE("없음");

    private final String description;

    GiftMenuType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}