package christmas.domain.event.benefit;

import christmas.domain.event.Reservation;
import christmas.domain.event.badge.BadgeRank;

public class TotalBenefit {

    private final Reservation reservation;
    private final BenefitDetails details;
    private final BadgeRank badgeRank;

    public TotalBenefit(Reservation reservation, BenefitDetails details, BadgeRank badgeRank) {
        this.reservation = reservation;
        this.details = details;
        this.badgeRank = badgeRank;
    }

}