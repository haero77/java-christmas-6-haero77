package christmas.domain.event.benefit;

import christmas.domain.common.Amount;

public class ExpectedPayment extends Amount {

    private ExpectedPayment(long amount) {
        super(amount);
    }

    public static ExpectedPayment ofAmount(long amount) {
        if (amount > 0) {
            return new ExpectedPayment(amount);
        }
        return new ExpectedPayment(0);
    }

}
