package christmas.domain.event.gift;

public enum GiftMenuType {

    CHAMPAGNE("샴페인", 25_000),
    NONE("없음", 0);

    private final String description;
    private final int menuPrice;

    GiftMenuType(String description, int menuPrice) {
        this.description = description;
        this.menuPrice = menuPrice;
    }

    public String getDescription() {
        return description;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public boolean isNone() {
        return this == NONE;
    }

}