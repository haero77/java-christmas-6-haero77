package christmas.domain.event;

import christmas.domain.event.badge.BadgeRank;
import christmas.domain.event.badge.BadgeRankMeter;
import christmas.domain.event.benefit.BenefitDetails;
import christmas.domain.event.benefit.TotalBenefit;
import christmas.domain.event.benefit.TotalBenefitAmount;
import christmas.domain.event.discount.DiscountDetails;
import christmas.domain.event.discount.DiscountManager;
import christmas.domain.event.gift.GiftManager;
import christmas.domain.event.gift.GiftMenu;

public class EventBenefitManager {

    private final Reservation reservation;

    public EventBenefitManager(Reservation reservation) {
        this.reservation = reservation;
    }

    public TotalBenefit calculateTotalBenefit() {
        BenefitDetails benefitDetails = applyBenefit();
        return new TotalBenefit(this.reservation, benefitDetails,
                applyBadgeRank(benefitDetails.calculateTotalBenefitAmount()));
    }

    private BenefitDetails applyBenefit() {
        return new BenefitDetails(applyDiscount(), applyGiftMenu());
    }

    private DiscountDetails applyDiscount() {
        return new DiscountManager(reservation).apply();
    }

    private GiftMenu applyGiftMenu() {
        return new GiftManager(reservation.getTotalOrderAmount()).apply();
    }

    private BadgeRank applyBadgeRank(TotalBenefitAmount totalBenefitAmount) {
        return new BadgeRankMeter(totalBenefitAmount).meter();
    }

}
