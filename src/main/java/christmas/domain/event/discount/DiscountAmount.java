package christmas.domain.event.discount;

public class DiscountAmount {

    private static final int MIN_AMOUNT = 0;

    private final int amount;

    private DiscountAmount(int amount) {
        this.amount = amount;
    }

    public static DiscountAmount from(int amount) {
        validateMinAmount(amount);
        return new DiscountAmount(amount);
    }

    private static void validateMinAmount(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("amount %d should be greater or equal than 0.".formatted(amount));
        }
    }

    public int getAmount() {
        return amount;
    }

}
