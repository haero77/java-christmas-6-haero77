package christmas.domain.event.discount;

public class DiscountAmount {

    private static final int MIN_AMOUNT = 0;

    private final long amount;

    private DiscountAmount(long amount) {
        this.amount = amount;
    }

    public static DiscountAmount from(long amount) {
        validateMinAmount(amount);
        return new DiscountAmount(amount);
    }

    private static void validateMinAmount(long amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("amount %d should be greater or equal than 0.".formatted(amount));
        }
    }

    public long getAmount() {
        return amount;
    }

}
