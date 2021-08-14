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
        List<Rank> sortedRanks = this.cards.stream()
                .map(Card::getRank)
                .sorted(Comparator.comparing(Rank::getValue))
                .collect(Collectors.toList());

        Rank minRank = sortedRanks.get(0);
        Rank maxRank = sortedRanks.get(sortedRanks.size() - 1);

        long numUnique = this.cards.stream()
                .distinct()
                .count();

        // Ace can be 1 or 14
        if (minRank == Rank.ACE && maxRank == Rank.KING) {
            // treat Ace as 14 in this case
            return sortedRanks.equals(Arrays.asList(Rank.ACE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING));
        }

        return ((maxRank.getValue() - minRank.getValue()) == 4) && (numUnique == 5);
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
        return String.format("Hand{name=%s, cards=%s}", handName, cards);
    }
}
