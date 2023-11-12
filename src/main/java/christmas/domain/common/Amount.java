package christmas.domain.common;

public abstract class Amount {

    private final int amount;

    protected Amount(int amount) {
        this.amount = amount;
    }

    public boolean isGreaterOrEqualThan(int amount) {
        return this.amount >= amount;
    }

}
