package com.xwh.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;
//卡组
public class Card extends JLabel implements MouseListener {

    Container container;
    PvpMain pvpMain;//Main类的引用
    String name;//图片url名字
    boolean up;//是否正反面
    public boolean canClick=false;//是否可被点击
    String color;//花色
    int num;//数值
    List<Card> cardsPlacement;
    boolean clicked = false;

    public Card(PvpMain m, String name, boolean up, String color, int num, List<Card> cardsPlacement){
        this.cardsPlacement = cardsPlacement;
        this.num = num;
        this.pvpMain =m;
        container = m.getContentPane();
        this.name=name;
        this.up=up;
        this.color=color;
        if(this.up)
            this.turnFront();
        else {
            this.turnRear();
        }
        this.setVisible(true);
        this.addMouseListener(this);
    }
    /**
     * 正面
     */
    public void turnFront() {
//        URL headerUrl = BackgroundImage.class.getResource("/images/" + name + ".jpg");
        URL headerUrl = BackgroundImage.class.getResource("/" + name + ".jpg");
        ImageIcon imageIcon = new ImageIcon(headerUrl);
        this.setIcon(imageIcon);
        this.setSize(100, 120);
        this.up = true;
    }

    /**
     * 反面
     */
    public void turnRear() {
        URL headerUrl = BackgroundImage.class.getResource("/back" + ".jpg");
        ImageIcon imageIcon = new ImageIcon(headerUrl);
        this.setIcon(imageIcon);
        this.setSize(100, 120);
        this.up = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        if (canClick) {
            if (clicked) {
                moveCard();
            }
            else {
                if (pvpMain.playerCards.contains(this)) {
                    int i;
                    for (i = 0; i < pvpMain.playerCards.size(); i++) {
                        if (pvpMain.playerCards.get(i).clicked)
                            break;
                    }
                    if (i == pvpMain.playerCards.size()) {
                        moveCard();
                    }
                }
                else {
                    int i;
                    for (i = 0; i < pvpMain.playerCards2.size(); i++) {
                        if (pvpMain.playerCards2.get(i).clicked)
                            break;
                    }
                    if (i == pvpMain.playerCards2.size()) {
                        moveCard();
                    }
                }
            }
        }
    }

    public void moveCard() {
        int step;
        if (clicked)
            step = 20;
        else
            step = -20;
        clicked = !clicked;
        if (pvpMain.playOrder)
            this.setLocation(this.getX(), this.getY() + step);
        else
            this.setLocation(this.getX(), this.getY() - step);
    }

    @Override
    public String toString() {
        return "Card{" +
                "color='" + color + '\'' +
                ", num=" + num +
                '}';
    }
}
