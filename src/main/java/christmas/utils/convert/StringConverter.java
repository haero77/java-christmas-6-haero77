package christmas.utils.convert;

import java.util.Arrays;
import java.util.List;

public class StringConverter {

    private StringConverter() {
    }

    public static List<String> spiltWith(String splitTarget, String regex) {
        return Arrays.stream(splitTarget.split(regex)).toList();
    }

}
