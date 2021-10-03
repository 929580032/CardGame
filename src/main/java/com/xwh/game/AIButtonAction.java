package com.xwh.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AIButtonAction implements ActionListener {
    PvpMain pvpMain;
    public AIButtonAction(PvpMain pvpMain) {
        this.pvpMain = pvpMain;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (pvpMain.AIbutton.getText().equals("托管")) {
            pvpMain.placeButton2.setVisible(false);
            pvpMain.turnOverButton2.setVisible(false);
            pvpMain.ai = new AI(pvpMain, true);
            pvpMain.ai.start();
            pvpMain.AIbutton.setText("取消托管");
        }
        else {
            pvpMain.placeButton2.setVisible(true);
            pvpMain.turnOverButton2.setVisible(true);
            pvpMain.ai.stop();
            pvpMain.ai = null;
            pvpMain.AIbutton.setText("托管");
        }
    }
}
