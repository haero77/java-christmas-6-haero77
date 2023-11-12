package christmas.domain.event;

public class VisitDate {

    private static final int MIN_ORDER_DATE = 1;
    private static final int MAX_ORDER_DATE = 31;

    private final int date;

    private VisitDate(int date) {
        this.date = date;
    }

    public static VisitDate from(int date) {
        validateDateRange(date);
        return new VisitDate(date);
    }

    private static void validateDateRange(int date) {
        if (!meetsDateRangeCriterion(date)) {
            throw new IllegalArgumentException("date %d is in invalidate date range.".formatted(date));
        }
    }

    private static boolean meetsDateRangeCriterion(int date) {
        return date >= MIN_ORDER_DATE && date <= MAX_ORDER_DATE;
    }

    public int getDate() {
        return date;
    }

}
