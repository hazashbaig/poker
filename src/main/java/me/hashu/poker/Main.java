package me.hashu.poker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Main implements CommandLineRunner {
    @Override
    public void run(String... args) {
        Hand hand = new Hand(Arrays.asList(
                new Card(Rank.FOUR, Suit.DIAMOND),
                new Card(Rank.FIVE, Suit.DIAMOND),
                new Card(Rank.SIX, Suit.DIAMOND),
                new Card(Rank.SEVEN, Suit.DIAMOND),
                new Card(Rank.EIGHT, Suit.DIAMOND)
        ));

        System.out.println(hand.getHandName());
    }
}
