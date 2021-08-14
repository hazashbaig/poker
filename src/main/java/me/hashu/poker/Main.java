package me.hashu.poker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Main implements CommandLineRunner {
    @Override
    public void run(String... args) {
        List<Hand> hands = new ArrayList<>(Arrays.asList(
                new Hand(Arrays.asList(
                        new Card(Rank.FOUR, Suit.DIAMOND),
                        new Card(Rank.FIVE, Suit.DIAMOND),
                        new Card(Rank.SIX, Suit.DIAMOND),
                        new Card(Rank.SEVEN, Suit.DIAMOND),
                        new Card(Rank.EIGHT, Suit.DIAMOND)
                )),
                new Hand(Arrays.asList(
                        new Card(Rank.SEVEN, Suit.DIAMOND),
                        new Card(Rank.SEVEN, Suit.DIAMOND),
                        new Card(Rank.SEVEN, Suit.DIAMOND),
                        new Card(Rank.TWO, Suit.DIAMOND),
                        new Card(Rank.TWO, Suit.DIAMOND)
                ))
        ));

        Hand winner = hands.stream()
                .max(Comparator.comparing((hand) -> hand.getHandName().ordinal()))
                .orElseThrow(NoSuchElementException::new);

        System.out.println(winner);

    }
}
