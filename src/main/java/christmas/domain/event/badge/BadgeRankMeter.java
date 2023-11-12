package christmas.domain.event.badge;

import christmas.domain.event.benefit.TotalBenefitAmount;

public class BadgeRankMeter {

    private final TotalBenefitAmount totalBenefitAmount;

    public BadgeRankMeter(TotalBenefitAmount totalBenefitAmount) {
        this.totalBenefitAmount = totalBenefitAmount;
    }

    public BadgeRank meter() {
        return BadgeRank.getValuesByMinTotalDiscountAmountDescending()
                .stream()
                .filter(rank -> totalBenefitAmount.isGreaterOrEqualThan(rank.getMinTotalDiscountAmount()))
                .findAny()
                .orElse(BadgeRank.NONE);
    }

}