package christmas.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfWeekParser {

    private DayOfWeekParser() {
    }

    public static DayOfWeek parse2023DecemberByDate(int date) {
        return LocalDate.of(2023, 12, date).getDayOfWeek();
    }

}
