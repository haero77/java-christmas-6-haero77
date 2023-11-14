package christmas.domain.event.benefit;

import christmas.domain.event.Reservation;
import christmas.domain.event.badge.BadgeRank;
import christmas.domain.order.Order;

public class TotalBenefit {

    private final Reservation reservation;
    private final BenefitDetails benefitDetails;
    private final BadgeRank badgeRank;

    public TotalBenefit(Reservation reservation, BenefitDetails benefitDetails, BadgeRank badgeRank) {
        this.reservation = reservation;
        this.benefitDetails = benefitDetails;
        this.badgeRank = badgeRank;
    }

    public int getVisitDate() {
        return reservation.getVisitDate();
    }

    public Order getOrder() {
        return reservation.getOrder();
    }

    public BenefitDetails getBenefitDetails() {
        return benefitDetails;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public BadgeRank getBadgeRank() {
        return badgeRank;
    }

}
