package com.xwh.game;

import com.xwh.StartGame;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;

public class AI extends Thread{
    PvpMain pvpMain;
    boolean flag;

    public AI(PvpMain pvpMain, boolean flag) {
        this.flag = flag;
        this.pvpMain = pvpMain;
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
                        if (pvpMain.playerCards.size() != 0) {
                            int flag = 0;
                            for (Card card : pvpMain.playerCards2) {
                                if (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag == 0) {
                                turnOver();
                            }
                        }
                        else {
                            int flag = 0;
                            Color[] colors = new Color[4];
                            colors[0] = new Color("黑桃", pvpMain.cardColorCount[0]);
                            colors[1] = new Color("梅花", pvpMain.cardColorCount[1]);
                            colors[2] = new Color("红桃", pvpMain.cardColorCount[2]);
                            colors[3] = new Color("方块", pvpMain.cardColorCount[3]);
                            Arrays.sort(colors, new Comparator<Color>() {
                                @Override
                                public int compare(Color o1, Color o2) {
                                    return o2.count - o1.count;
                                }
                            });

                            for (Card card : pvpMain.playerCards2) {
                                if (card.color.equals(colors[0].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }

                            if (flag == 1)
                                continue;

                            for (Card card : pvpMain.playerCards2) {
                                if (card.color.equals(colors[1].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }

                            if (flag == 1)
                                continue;

                            for (Card card : pvpMain.playerCards2) {
                                if (card.color.equals(colors[2].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }

                            if (flag == 1)
                                continue;

                            for (Card card : pvpMain.playerCards2) {
                                if (card.color.equals(colors[3].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag == 0) {
                                turnOver();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Thread.sleep(1);
                    if (pvpMain.playOrder) {
                        Thread.sleep(1000);
                        if (pvpMain.playerCards2.size() != 0) {
                            int flag = 0;
                            for (Card card : pvpMain.playerCards) {
                                if (pvpMain.cardsPlacement.size() == 0 || !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag == 0) {
                                turnOver();
                            }
                        }
                        else {
                            int flag = 0;
                            Color[] colors = new Color[4];
                            colors[0] = new Color("黑桃", pvpMain.cardColorCount[0]);
                            colors[1] = new Color("梅花", pvpMain.cardColorCount[1]);
                            colors[2] = new Color("红桃", pvpMain.cardColorCount[2]);
                            colors[3] = new Color("方块", pvpMain.cardColorCount[3]);
                            Arrays.sort(colors, new Comparator<Color>() {
                                @Override
                                public int compare(Color o1, Color o2) {
                                    return o2.count - o1.count;
                                }
                            });

                            for (Card card : pvpMain.playerCards) {
                                if (card.color.equals(colors[0].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }

                            if (flag == 1)
                                continue;

                            for (Card card : pvpMain.playerCards) {
                                if (card.color.equals(colors[1].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }

                            if (flag == 1)
                                continue;

                            for (Card card : pvpMain.playerCards) {
                                if (card.color.equals(colors[2].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }

                            if (flag == 1)
                                continue;

                            for (Card card : pvpMain.playerCards) {
                                if (card.color.equals(colors[3].name) && !card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 1).color)) {
                                    selectCard(card);
                                    Thread.sleep(1000);
                                    place(card);
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag == 0) {
                                turnOver();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
        card.setLocation(700, 250);
        pvpMain.container.setComponentZOrder(card, 0);
        pvpMain.cardList.remove(pvpMain.cardList.size() - 1);
        gameEnd();
        pvpMain.cardsPlacement.add(card);
        if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color))
            turnOverAction.eatCard();
        switchPlayer();
        turnOverAction.gameEnd();
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
                card.setLocation(700, 250);
                pvpMain.container.setComponentZOrder(card, 0);
                MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards2, 2);
            }
        }
        else {
            pvpMain.playerCards.remove(card);

            if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color)) {

            }
            else {
                card.setLocation(700, 250);
                pvpMain.container.setComponentZOrder(card, 0);
                MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards, 1);
            }
        }

        card.canClick = false;
        card.clicked = false;
        switchPlayer();
    }

    public void gameEnd() {
        if (pvpMain.cardList.size() == 0) {
            if (pvpMain.playerCards.size() < pvpMain.playerCards2.size()) {
                JOptionPane.showMessageDialog(pvpMain,"玩家1获胜！点击确定重新游戏");
                new StartGame();
                pvpMain.dispose();
            }
            else if (pvpMain.playerCards.size() > pvpMain.playerCards2.size()) {
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