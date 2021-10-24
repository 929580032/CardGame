package com.xwh.game;

import com.xwh.NetworkInterfaceUtils;
import com.xwh.StartGame;

import javax.swing.*;
import java.util.*;

public class AI extends Thread{
    PvpMain pvpMain;
    boolean flag;
    TurnOverAction turnOverAction = null;

    public AI(PvpMain pvpMain, boolean flag) {
        this.flag = flag;
        this.pvpMain = pvpMain;
        turnOverAction = new TurnOverAction(pvpMain);
    }
    @Override
    public void run() {
        super.run();
        while (true) {
            if (flag) {
                try {
                    Thread.sleep(1);
                    if (!pvpMain.playOrder) {
                        Thread.sleep(1000);
                        aiAction(pvpMain.playerCards, pvpMain.playerCards2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Thread.sleep(1);
//                    if (pvpMain.online)
//                        Thread.sleep(1000);
                    if (pvpMain.playOrder) {
//                        if (!pvpMain.online)
                        Thread.sleep(1000);
//                        aiAction();
                        aiAction(pvpMain.playerCards2, pvpMain.playerCards);

                        pvpMain.playOrder = false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isPlayerCardsSameColor(List<Card> playerCards) {
        List<String> list = new ArrayList<>();
        for (Card playerCard : playerCards) {
           if (!list.contains(playerCard.color)) {
               list.add(playerCard.color);
               if (list.size() > 1)
                   return false;
           }
        }
        return true;
    }

    private void aiAction(List<Card> playerCards, List<Card> playerCards2) throws InterruptedException {
        Color[] colors = new Color[4];
        colors[0] = new Color("黑桃", pvpMain.cardColorCount[0]);
        colors[1] = new Color("草花", pvpMain.cardColorCount[1]);
        colors[2] = new Color("红桃", pvpMain.cardColorCount[2]);
        colors[3] = new Color("方块", pvpMain.cardColorCount[3]);
        Arrays.sort(colors, new Comparator<Color>() {
            @Override
            public int compare(Color o1, Color o2) {
                return o2.count - o1.count;
            }
        });
        Map<String, Integer> map = new HashMap<>();
        map.put("黑桃", pvpMain.cardColorCount[0]);
        map.put("草花", pvpMain.cardColorCount[1]);
        map.put("红桃", pvpMain.cardColorCount[2]);
        map.put("方块", pvpMain.cardColorCount[3]);
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (pvpMain.cardColorCount[i] != 0)
                count++;
        }
        if (pvpMain.cardList.size() == 1 && (playerCards2.size() + pvpMain.cardsPlacement.size() + 1 < playerCards.size() || (playerCards2.size() < playerCards.size() && map.get(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color) == 0))) {
            turnOverAction.turnOver1();
            return;
        }
        //如果牌比对面少且牌堆数 * 2 + 放置区牌数 > 自己手牌数
        if (pvpMain.cardList.size() * 2 + playerCards2.size() + pvpMain.cardsPlacement.size() < playerCards.size()) {
            turnOverAction.turnOver1();
            return;
        }
        if (playerCards.size() != 0 && isPlayerCardsSameColor(playerCards) && count == 1 && map.get(playerCards.get(0).color) != 0) {
            for (Card card : playerCards2) {
                if (card.color.equals(playerCards.get(0).color)) {
                    if (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                        selectCard(card);
                        Thread.sleep(500);
                        place(card);
                        return;
                    }
                }
            }
        }
        //牌比对面少且抽牌后可以保证不会吃牌，就抽牌
        if (playerCards2.size() < playerCards.size() && (pvpMain.cardsPlacement.size() == 0 || map.get(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color) == 0)) {
            turnOverAction.turnOver1();
            return;
        }
        if (pvpMain.cardList.size() <= 5) {
            if (playerCards2.size() + pvpMain.cardsPlacement.size() + 1 < playerCards.size() / 2) {
                turnOverAction.turnOver1();
                return;
            }
            if (playerCards2.size() > playerCards.size()) {
                for (Card card : playerCards2) {
                    if (card.color.equals(colors[0].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                        selectCard(card);
                        Thread.sleep(500);
                        place(card);
                        return;
                    }
                }
                for (Card card : playerCards2) {
                    if (card.color.equals(colors[1].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                        selectCard(card);
                        Thread.sleep(500);
                        place(card);
                        return;
                    }
                }
                for (Card card : playerCards2) {
                    if (card.color.equals(colors[2].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                        selectCard(card);
                        Thread.sleep(500);
                        place(card);
                        return;
                    }
                }
                for (Card card : playerCards2) {
                    if (card.color.equals(colors[3].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                        selectCard(card);
                        Thread.sleep(500);
                        place(card);
                        return;
                    }
                }
            }
        }
        if (playerCards.size() != 0) {
            Color[] playerCardsColors = new Color[4];
            playerCardsColors[0] = new Color("黑桃", 0);
            playerCardsColors[1] = new Color("草花", 0);
            playerCardsColors[2] = new Color("红桃", 0);
            playerCardsColors[3] = new Color("方块", 0);
            for (Card card : playerCards2) {
                playerCardsColors[(Integer.parseInt(card.name) - 1) / 13].count++;
            }
            Arrays.sort(playerCardsColors, new Comparator<Color>() {
                @Override
                public int compare(Color o1, Color o2) {
                    return o2.count - o1.count;
                }
            });
            for (Card card : playerCards2) {
                if (card.color.equals(playerCardsColors[0].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }
            for (Card card : playerCards2) {
                if (card.color.equals(playerCardsColors[1].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }
            for (Card card : playerCards2) {
                if (card.color.equals(playerCardsColors[2].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }
            for (Card card : playerCards2) {
                if (card.color.equals(playerCardsColors[3].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }

//            for (Card card : playerCards2) {
//                if (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
//                    selectCard(card);
//                    Thread.sleep(500);
//                    place(card);
//                    return;
//                }
//            }
            turnOverAction.turnOver1();
        }
        else {
            for (Card card : playerCards2) {
                if (card.color.equals(colors[0].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }
            for (Card card : playerCards2) {
                if (card.color.equals(colors[1].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }
            for (Card card : playerCards2) {
                if (card.color.equals(colors[2].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }
            for (Card card : playerCards2) {
                if (card.color.equals(colors[3].name) && (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color))) {
                    selectCard(card);
                    Thread.sleep(500);
                    place(card);
                    return;
                }
            }
            turnOverAction.turnOver1();
        }
    }


    public void turnOver() {
        TurnOverAction turnOverAction = new TurnOverAction(pvpMain);
        Card card = pvpMain.cardList.get(pvpMain.cardList.size() - 1);
        pvpMain.cardColorCount[(Integer.parseInt(card.name) - 1) / 13]--;
        String text = pvpMain.colorsJLabel[(Integer.parseInt(card.name) - 1) / 13].getText();
        String substring = text.substring(0, 3);
        substring += pvpMain.cardColorCount[(Integer.parseInt(card.name) - 1) / 13];
        pvpMain.colorsJLabel[(Integer.parseInt(card.name) - 1) / 13].setText(substring);
        card.turnFront();
        card.setLocation(710, 250);
        pvpMain.container.setComponentZOrder(card, 0);
        pvpMain.cardList.remove(pvpMain.cardList.size() - 1);
        pvpMain.cardsPlacement.add(card);
        if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color))
            turnOverAction.eatCard();
        pvpMain.jLabel.setText("剩余数量:" + pvpMain.cardList.size());
        pvpMain.jLabel1.setText("剩余数量:" + pvpMain.cardsPlacement.size());
        gameEnd();
        switchPlayer();
    }

    public void switchPlayer() {
        if (!pvpMain.playOrder) {
            pvpMain.playOrder = !pvpMain.playOrder;
            for (Card playerCard : pvpMain.playerCards2) {
                if (playerCard.clicked) {
                    playerCard.clicked = false;
                    playerCard.setLocation(playerCard.getX(), playerCard.getY() - 20);
                }
                playerCard.canClick = false;
            }
            for (Card playerCard : pvpMain.playerCards) {
                playerCard.canClick = true;
            }
            pvpMain.placeButton2.setEnabled(false);
            pvpMain.turnOverButton2.setEnabled(false);
            pvpMain.placeButton1.setEnabled(true);
            pvpMain.turnOverButton1.setEnabled(true);
            pvpMain.jLabel.setText("剩余数量:" + pvpMain.cardList.size());
            pvpMain.jLabel1.setText("剩余数量:" + pvpMain.cardsPlacement.size());
        }
        else {
            pvpMain.playOrder = !pvpMain.playOrder;
            for (Card playerCard : pvpMain.playerCards) {
                if (playerCard.clicked) {
                    playerCard.clicked = false;
                    playerCard.setLocation(playerCard.getX(), playerCard.getY() + 20);
                }
                playerCard.canClick = false;
            }
            for (Card playerCard : pvpMain.playerCards2) {
                playerCard.canClick = true;
            }
            pvpMain.placeButton2.setEnabled(true);
            pvpMain.turnOverButton2.setEnabled(true);
            pvpMain.placeButton1.setEnabled(false);
            pvpMain.turnOverButton1.setEnabled(false);
            pvpMain.jLabel.setText("剩余数量:" + pvpMain.cardList.size());
            pvpMain.jLabel1.setText("剩余数量:" + pvpMain.cardsPlacement.size());
        }
    }


    public void selectCard(Card card) {
        int step;
        if (card.clicked)
            step = 20;
        else
            step = -20;
        card.clicked = !card.clicked;
        if (pvpMain.playOrder)
            card.setLocation(card.getX(), card.getY() + step);
        else
            card.setLocation(card.getX(), card.getY() - step);
    }

    public void place(Card card) {
        pvpMain.cardsPlacement.add(card);

        if (!pvpMain.playOrder) {
            pvpMain.playerCards2.remove(card);

            if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color)) {

            }
            else {
                card.setLocation(710, 250);
                pvpMain.container.setComponentZOrder(card, 0);
                MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards2, 2);
            }
        }
        else {
            pvpMain.playerCards.remove(card);

            if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color)) {

            }
            else {
                card.setLocation(710, 250);
                pvpMain.container.setComponentZOrder(card, 0);
                MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards, 1);
            }
        }

        card.canClick = false;
        card.clicked = false;
        if (!pvpMain.online)
            switchPlayer();
        else {
            NetworkInterfaceUtils.doPlayerOperation(pvpMain.token, pvpMain.uuid, card.transfer());
            pvpMain.jLabel.setText("剩余数量:" + pvpMain.cardList.size());
            pvpMain.jLabel1.setText("剩余数量:" + pvpMain.cardsPlacement.size());
            for (Card card1 : pvpMain.playerCards) {
                card1.canClick = true;
            }
        }
    }

    public void gameEnd() {
        if (pvpMain.cardList.size() == 0) {
            if (pvpMain.playerCards.size() < pvpMain.playerCards2.size()) {
                if (pvpMain.pveflag)
                    JOptionPane.showMessageDialog(pvpMain,"玩家获胜！点击确定重新游戏");
                else
                    JOptionPane.showMessageDialog(pvpMain,"玩家1获胜！点击确定重新游戏");
                new StartGame();
                pvpMain.dispose();
            }
            else if (pvpMain.playerCards.size() > pvpMain.playerCards2.size()) {
                if (pvpMain.pveflag)
                    JOptionPane.showMessageDialog(pvpMain,"电脑获胜！点击确定重新游戏");
                else
                    JOptionPane.showMessageDialog(pvpMain,"玩家2获胜！点击确定重新游戏");
                new StartGame();
                pvpMain.dispose();
            }
            else {
                JOptionPane.showMessageDialog(pvpMain,"平局！点击确定重新游戏");
                new StartGame();
                pvpMain.dispose();
            }
            if (pvpMain.ai != null)
                pvpMain.ai.stop();
            if (pvpMain.ai2 != null)
                pvpMain.ai2.stop();
        }
    }
}

class Color {
    String name;
    int count;

    public Color(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return name + count;
    }
}