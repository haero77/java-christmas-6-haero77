package christmas.domain.order;

public class TotalOrderAmount {

    private final int amount;

    public TotalOrderAmount(int amount) {
        this.amount = amount;
    }

    public boolean isGreaterOrEqualThan(int amount) {
        return this.amount >= amount;
    }

}
