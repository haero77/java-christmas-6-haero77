package christmas.domain.event.benefit;

import christmas.domain.event.Reservation;
import christmas.domain.event.badge.BadgeRank;
import christmas.domain.order.Order;

public class TotalBenefit {

    private final Reservation reservation;
    private final BenefitDetails details;
    private final BadgeRank badgeRank;

    public TotalBenefit(Reservation reservation, BenefitDetails details, BadgeRank badgeRank) {
        this.reservation = reservation;
        this.details = details;
        this.badgeRank = badgeRank;
    }

    public int getVisitDate() {
        return reservation.getVisitDate();
    }

    public Order getOrder() {
        return reservation.getOrder();
    }

}
