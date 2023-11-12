package christmas.domain.event.gift;

public class GiftMenu {

    private final GiftMenuType type;
    private final int count;

    public GiftMenu(GiftMenuType type, int count) {
        this.type = type;
        this.count = count;
    }

    public GiftMenuType getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

}