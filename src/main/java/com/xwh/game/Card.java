package com.xwh.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;
import java.util.Objects;

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

    public String transfer() {
        String[] colors = new String[]{"S", "C", "H", "D"};
        int id = Integer.parseInt(name);
        String color = colors[(id - 1) / 13];
        String num = Integer.toString((id - 1) % 13 + 1);
        if ("11".equals(num))
            num = "J";
        if ("12".equals(num))
            num = "Q";
        if ("13".equals(num))
            num = "K";
        return color + num;
    }

    public Card(PvpMain m, String str, boolean up, List<Card> cardsPlacement){
        String color = str.substring(0, 1);
        String num = str.substring(1, str.length());
        if ("J".equals(num))
            num = "11";
        if ("Q".equals(num))
            num = "12";
        if ("K".equals(num))
            num = "13";
        String[] colors = new String[]{"S", "C", "H", "D"};
        for (int i = 0; i < 52; i++) {
            if ((colors[i / 13] + Integer.toString(i % 13 + 1)).equals(color + num)) {
                this.name = Integer.toString(i + 1);
                this.num = i % 13 + 1;
                if (i / 13 == 0)
                    this.color = "黑桃";
                if (i / 13 == 1)
                    this.color = "草花";
                if (i / 13 == 2)
                    this.color = "红桃";
                if (i / 13 == 3)
                    this.color = "方块";
                break;
            }
        }

        this.cardsPlacement = cardsPlacement;
        this.pvpMain =m;
        container = m.getContentPane();
        this.up=up;
        if(this.up)
            this.turnFront();
        else {
            this.turnRear();
        }
        this.setVisible(true);
        this.addMouseListener(this);
    }

    public Card(PvpMain m, String name, boolean up, String color, int num, List<Card> cardsPlacement){
        this.cardsPlacement = cardsPlacement;
        this.num = num;
        this.pvpMain =m;
        if (m != null)
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
        if (pvpMain.playOrder || pvpMain.online)
            this.setLocation(this.getX(), this.getY() + step);
        else
            this.setLocation(this.getX(), this.getY() - step);
    }

    @Override
    public String toString() {
        return color + num;
    }
}
