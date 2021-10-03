package com.xwh.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AIButtonAction2 implements ActionListener {
    PvpMain pvpMain;
    public AIButtonAction2(PvpMain pvpMain) {
        this.pvpMain = pvpMain;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (pvpMain.AIbutton1.getText().equals("托管")) {
            pvpMain.placeButton1.setVisible(false);
            pvpMain.turnOverButton1.setVisible(false);
            pvpMain.ai2 = new AI(pvpMain, false);
            pvpMain.ai2.start();
            pvpMain.AIbutton1.setText("取消托管");
        }
        else {
            pvpMain.placeButton1.setVisible(true);
            pvpMain.turnOverButton1.setVisible(true);
            pvpMain.ai2.stop();
            pvpMain.ai2 = null;
            pvpMain.AIbutton1.setText("托管");
        }
    }
}
