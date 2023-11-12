package christmas.domain.order;

public class OrderCount {

    private static final int MIN_COUNT = 1;

    private final int count;

    public OrderCount(int count) {
        this.count = count;
    }

    public static OrderCount from(int count) {
        validateMinCount(count);
        return new OrderCount(count);
    }

    private static void validateMinCount(int count) {
        if (!meetsMinCountCriterion(count)) {
            throw new IllegalArgumentException("count %d should greater or equal than %d".formatted(count, MIN_COUNT));
        }
    }

    private static boolean meetsMinCountCriterion(int count) {
        return count >= MIN_COUNT;
    }

}
