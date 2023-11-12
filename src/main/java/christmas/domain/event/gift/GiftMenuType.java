package christmas.domain.event.gift;

public enum GiftMenuType {

    CHAMPAGNE("샴페인", 120_000),
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

}