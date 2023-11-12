package christmas.domain.common;

public abstract class Amount {

    private static final int MIN_AMOUNT = 0;

    private final long amount;

    protected Amount(long amount) {
        validateMinAmount(amount);
        this.amount = amount;
    }

    private static void validateMinAmount(long amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("amount %d should be greater or equal than 0.".formatted(amount));
        }
    }

    public boolean isGreaterOrEqualThan(long amount) {
        return this.amount >= amount;
    }

    public long getAmount() {
        return amount;
    }

}
