package me.hashu.poker;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand {
    public static final String INVALID_HAND_ERROR_FORMAT = "Hand must have exactly 5 cards instead of %s";

    private final List<Card> cards;
    private final HandName handName;

    public Hand(List<Card> cards) {
        throwIfInvalidHand(cards);
        this.cards = cards;
        this.handName = determineHandName();
    }

    public List<Card> getCards() {
        return cards;
    }

    public HandName getHandName() {
        return handName;
    }

    private void throwIfInvalidHand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException(String.format(INVALID_HAND_ERROR_FORMAT, cards.size()));
        }
    }

    private HandName determineHandName() {
        if (isStraight() && isFlush())
            return HandName.STRAIGHT_FLUSH;
        else if (isFourKind())
            return HandName.FOUR_KIND;
        else if (isFullHouse())
            return HandName.FULL_HOUSE;
        else if (isFlush())
            return HandName.FLUSH;
        else if (isStraight())
            return HandName.STRAIGHT;
        else if (isThreeKind())
            return HandName.THREE_KIND;
        else if (isTwoPair())
            return HandName.TWO_PAIR;
        else if (isOnePair())
            return HandName.ONE_PAIR;
        else
            return HandName.NOTHING;
    }

    private boolean isStraight() {
        int maxRank = this.cards.stream()
                .map(Card::getRank)
                .max(Comparator.comparing(Rank::getValue))
                .map(Rank::getValue)
                .orElseThrow(NoSuchElementException::new);

        int minRank = this.cards.stream()
                .map(Card::getRank)
                .min(Comparator.comparing(Rank::getValue))
                .map(Rank::getValue)
                .orElseThrow(NoSuchElementException::new);

        long numUnique = this.cards.stream()
                .distinct()
                .count();

        return ((maxRank - minRank) == 4) && (numUnique == 5);
    }

    private boolean isFlush() {
        return this.cards.stream()
                .map(Card::getSuit)
                .distinct()
                .count() == 1;
    }

    private boolean isFourKind() {
        return getSortedRankFrequencies().equals(Arrays.asList(1L, 4L));
    }

    private boolean isFullHouse() {
        return getSortedRankFrequencies().equals(Arrays.asList(2L, 3L));
    }

    private boolean isThreeKind() {
        return getSortedRankFrequencies().equals(Arrays.asList(1L, 1L, 3L));
    }

    private boolean isTwoPair() {
        return getSortedRankFrequencies().equals(Arrays.asList(1L, 2L, 2L));
    }

    private boolean isOnePair() {
        return getSortedRankFrequencies().equals(Arrays.asList(1L, 1L, 1L, 2L));
    }

    private List<Long> getSortedRankFrequencies() {
        Map<Rank, Long> ranksFrequency = this.cards.stream()
                .map(Card::getRank)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        return ranksFrequency.values().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Hand{" + cards + '}';
    }
}
