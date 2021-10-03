package com.xwh.game;

import com.xwh.StartGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurnOverAction implements ActionListener {
    PvpMain pvpMain;

    public TurnOverAction(PvpMain pvpMain) {
        this.pvpMain = pvpMain;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Card card = pvpMain.cardList.get(pvpMain.cardList.size() - 1);
        pvpMain.cardColorCount[(Integer.parseInt(card.name) - 1) / 13]--;

        String text = pvpMain.colorsJLabel[(Integer.parseInt(card.name) - 1) / 13].getText();
        String substring = text.substring(0, 3);
        substring += pvpMain.cardColorCount[(Integer.parseInt(card.name) - 1) / 13];
        pvpMain.colorsJLabel[(Integer.parseInt(card.name) - 1) / 13].setText(substring);

        card.setLocation(700, 250);
        pvpMain.container.setComponentZOrder(card, 0);
        pvpMain.cardList.remove(pvpMain.cardList.size() - 1);
        card.turnFront();
        pvpMain.cardsPlacement.add(card);
        if (pvpMain.playOrder) {
            if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color))
                eatCard();
            switchPlayer();
        }
        else {
            if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color))
                eatCard();
            switchPlayer();
        }
        gameEnd();
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

    public void eatCard() {
        if (pvpMain.playOrder) {
            pvpMain.playerCards.addAll(pvpMain.cardsPlacement);
            MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards, 1);
        }
        else {
            pvpMain.playerCards2.addAll(pvpMain.cardsPlacement);
            MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards2, 2);
        }
        pvpMain.cardsPlacement.clear();
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
}
