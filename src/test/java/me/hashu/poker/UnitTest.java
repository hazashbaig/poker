package me.hashu.poker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class UnitTest {
    @Test
    @DisplayName("check straight flush")
    void checkStraightFlush() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.FOUR, Suit.DIAMOND),
                new Card(Rank.FIVE, Suit.DIAMOND),
                new Card(Rank.SIX, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.EIGHT, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.STRAIGHT_FLUSH);
    }

    @Test
    @DisplayName("check four kind")
    void checkFourKind() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.FOUR_KIND);
    }

    @Test
    @DisplayName("check full house")
    void checkFullHouse() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.FULL_HOUSE);
    }

    @Test
    @DisplayName("check flush")
    void checkFlush() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.EIGHT, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.FLUSH);
    }

    @Test
    @DisplayName("check straight")
    void checkStraight() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.CLUB),
                new Card(Rank.EIGHT, Suit.SPADE),
                new Card(Rank.SIX, Suit.DIAMOND),
                new Card(Rank.FIVE, Suit.DIAMOND),
                new Card(Rank.FOUR, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.STRAIGHT);
    }

    @Test
    @DisplayName("check three kind")
    void checkThreeKind() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.EIGHT, Suit.HEART),
                new Card(Rank.TWO, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.THREE_KIND);
    }

    @Test
    @DisplayName("check two pair")
    void checkTwoPair() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.HEART),
                new Card(Rank.THREE, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.TWO_PAIR);
    }

    @Test
    @DisplayName("check one pair")
    void checkOnePair() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.EIGHT, Suit.HEART),
                new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.ONE_PAIR);
    }

    @Test
    @DisplayName("check nothing (hand name)")
    void checkNothing() {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.EIGHT, Suit.HEART),
                new Card(Rank.TWO, Suit.DIAMOND),
                new Card(Rank.JACK, Suit.DIAMOND),
                new Card(Rank.KING, Suit.DIAMOND)
        ));

        assertThat(hand.getHandName()).isEqualTo(HandName.NOTHING);
    }

    @Test
    @DisplayName("should throw when hand size is less than five")
    void shouldThrowWhenHandSizeIsLessThanFive() {
        List<Card> cardList = Collections.singletonList(
                new Card(Rank.SEVEN, Suit.DIAMOND)
        );

        assertThatThrownBy(() -> new Hand(cardList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(Hand.INVALID_HAND_ERROR_FORMAT, cardList.size()));
    }

    @Test
    @DisplayName("should throw when hand size is more than five")
    void shouldThrowWhenHandSizeIsMoreThanFive() {
        List<Card> cardList = Arrays.asList(
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND)
        );

        assertThatThrownBy(() -> new Hand(cardList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(Hand.INVALID_HAND_ERROR_FORMAT, cardList.size()));
    }
}
