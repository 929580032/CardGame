package com.xwh.game;

import com.xwh.NetworkInterfaceUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacementAction implements ActionListener {
    PvpMain pvpMain;

    public PlacementAction(PvpMain pvpMain) {
        this.pvpMain = pvpMain;
    }

    public void placement(Card card) {
        for (Card card1 : pvpMain.playerCards2) {
            if (card1.name.equals(card.name))
                card = card1;
        }
        pvpMain.cardsPlacement.add(card);
        pvpMain.playerCards2.remove(card);
        pvpMain.container.add(card);

        if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color)) {
            pvpMain.playerCards2.addAll(pvpMain.cardsPlacement);
            MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards2, 2);
            pvpMain.cardsPlacement.clear();
        }
        else {
            card.setLocation(700, 250);
            pvpMain.container.setComponentZOrder(card, 0);
            MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards2, 2);
        }
        card.canClick = false;
        card.clicked = false;

        if (!pvpMain.online)
            switchPlayer();
        else {
            pvpMain.jLabel.setText("剩余数量:" + pvpMain.cardList.size());
            pvpMain.jLabel1.setText("剩余数量:" + pvpMain.cardsPlacement.size());
        }
    }


    public void placement1() {
        if (pvpMain.playOrder || pvpMain.online) {
            Card card = null;
            int count = 0;
            for (Card playerCard1 : pvpMain.playerCards) {
                if (playerCard1.clicked) {
                    count++;
                    card = playerCard1;
                }
            }
            if (count == 1) {
                pvpMain.cardsPlacement.add(card);
                pvpMain.playerCards.remove(card);
                if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color))
                    eatCard();
                else {
                    card.setLocation(700, 250);
                    pvpMain.container.setComponentZOrder(card, 0);
                    MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards, 1);
                }
                card.canClick = false;
                card.clicked = false;
                if (!pvpMain.online) {
                    switchPlayer();
                }
                else {
                    NetworkInterfaceUtils.doPlayerOperation(pvpMain.token, pvpMain.uuid, card.transfer());
                    pvpMain.jLabel.setText("剩余数量:" + pvpMain.cardList.size());
                    pvpMain.jLabel1.setText("剩余数量:" + pvpMain.cardsPlacement.size());
                    for (Card card1 : pvpMain.playerCards) {
                        card1.canClick = true;
                    }
                }

            }
        }
        else {
            Card card = null;
            int count = 0;
            for (Card playerCard1 : pvpMain.playerCards2) {
                if (playerCard1.clicked) {
                    count++;
                    card = playerCard1;
                }
            }
            if (count == 1) {
                pvpMain.cardsPlacement.add(card);
                pvpMain.playerCards2.remove(card);

                if (pvpMain.cardsPlacement.size() > 1 && card.color.equals(pvpMain.cardsPlacement.get(pvpMain.cardsPlacement.size() - 2).color))
                    eatCard();
                else {
                    card.setLocation(700, 250);
                    pvpMain.container.setComponentZOrder(card, 0);
                    MoveCardUtil.rePosition(pvpMain, pvpMain.playerCards2, 2);
                }
                card.canClick = false;
                card.clicked = false;
                switchPlayer();
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        placement1();
    }

    public void switchPlayer() {
        if (!pvpMain.playOrder) {
            pvpMain.playOrder = !pvpMain.playOrder;
            for (Card playerCard : pvpMain.playerCards2) {
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
}
