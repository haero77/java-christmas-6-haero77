package christmas.domain.event.badge;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.benefit.TotalBenefitAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeRankMeterTest {

    @DisplayName("총 혜택 금액이 5000원 미만이면 등급은 NONE")
    @ParameterizedTest
    @ValueSource(ints = {1, 4999})
    void none_rank_under_5000(int amount) {
        // given
        BadgeRankMeter badgeRankMeter = new BadgeRankMeter(new TotalBenefitAmount(amount));

        // when
        BadgeRank result = badgeRankMeter.meter();

        // then
        assertThat(result).isEqualTo(BadgeRank.NONE);
    }

    @DisplayName("총 혜택 금액이 20000원 이상인 경우 등급은 SANTA")
    @Test
    void santa_rank_20000() {
        // given
        BadgeRankMeter badgeRankMeter = new BadgeRankMeter(new TotalBenefitAmount(20000));

        // when
        BadgeRank result = badgeRankMeter.meter();

        // then
        assertThat(result).isEqualTo(BadgeRank.SANTA);
    }

    @DisplayName("총 혜택 금액이 10,000원 이상 20000미만 경우 등급은 TREE")
    @ParameterizedTest
    @ValueSource(ints = {10_000, 19_999})
    void tree_rank_10000(int amount) {
        // given
        BadgeRankMeter badgeRankMeter = new BadgeRankMeter(new TotalBenefitAmount(amount));

        // when
        BadgeRank result = badgeRankMeter.meter();

        // then
        assertThat(result).isEqualTo(BadgeRank.TREE);
    }

    @DisplayName("총 혜택 금액이 5,000원 이상 10,000 미만인 경우 등급은 STAR")
    @ParameterizedTest
    @ValueSource(ints = {5000, 9999})
    void star_rank_5000(int amount) {
        // given
        BadgeRankMeter badgeRankMeter = new BadgeRankMeter(new TotalBenefitAmount(amount));

        // when
        BadgeRank result = badgeRankMeter.meter();

        // then
        assertThat(result).isEqualTo(BadgeRank.STAR);
    }

}