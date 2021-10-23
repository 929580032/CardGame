package com.gxa.edu.test;

import com.xwh.game.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFunction {
    @Test
    public void testShuffleCards() {
        List<Card> cardList = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            String color = "";
            switch ((i - 1) / 13) {
                case 0: color = "黑桃";break;
                case 1: color = "草花";break;
                case 2: color = "红桃";break;
                case 3: color = "方块";break;
            }
            Card card = new Card(null, Integer.toString(i), false, color, (i % 13 == 0) ? 13 : i % 13, null);
            cardList.add(card);
        }

        Collections.shuffle(cardList);
        for (Card card : cardList) {
            System.out.println(card);
        }
    }
}
