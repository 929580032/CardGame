package com.xwh.game;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

//移动卡牌的工具类
public class MoveCardUtil {
    /**
     * 重新定位
     * @param m
     * @param list
     */
    public static void rePosition(PvpMain m, List<Card> list, int flag) {
        list.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return Integer.parseInt(o2.name) - Integer.parseInt(o1.name);
            }
        });
        Point p = new Point();
        if (flag == 0) {
            p.x = 50;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        if (flag == 1) {// 我的排序 _y=450 width=830
            p.x = (800 / 2) - (list.size() + 1) * 21 / 2 + 175;
            p.y = 525;
        }
        if (flag == 2) {
            p.x = (800 / 2) - (list.size() + 1) * 21 / 2 + 175;
            p.y = 0;
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Card card = list.get(i);
            move(card, card.getLocation(), p);
            m.container.setComponentZOrder(card, 0);
//            if (flag == 1)
                p.x += 21;
//            else
//                p.y += 15;
        }
    }
    /**
     * 移动卡牌的函数
     * @param card
     * @param from
     * @param to
     */
    public static void move(Card card, Point from, Point to) {
        if (to.x != from.x) {
            double k = (1.0) * (to.y - from.y) / (to.x - from.x);
            double b = to.y - to.x * k;
            int flag = 0;// 判断向左还是向右移动步幅
            if (from.x < to.x)
                flag = 20;
            else {
                flag = -20;
            }
            for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
                double y = k * i + b;// 这里主要用的数学中的线性函数

                card.setLocation(i, (int) y);
//                try {
//                    Thread.sleep(5); // 延迟，可自己设置
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
        // 位置校准
        card.setLocation(to);
    }
}
