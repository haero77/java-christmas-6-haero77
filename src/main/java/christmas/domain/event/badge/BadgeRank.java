package christmas.domain.event.badge;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum BadgeRank {

    NONE("무등급", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String description;
    private final int minTotalDiscountAmount;

    BadgeRank(String description, int minTotalDiscountAmountCriterion) {
        this.description = description;
        this.minTotalDiscountAmount = minTotalDiscountAmountCriterion;
    }

    public static List<BadgeRank> getValuesByMinTotalDiscountAmountDescending() {
        return Arrays.stream(BadgeRank.values())
                .sorted(Comparator.comparing(rank -> rank.minTotalDiscountAmount))
                .sorted(Collections.reverseOrder())
                .toList();
    }

    public String getDescription() {
        return description;
    }

    public int getMinTotalDiscountAmount() {
        return minTotalDiscountAmount;
    }

}
