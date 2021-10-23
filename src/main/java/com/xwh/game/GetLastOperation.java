package com.xwh.game;

import com.alibaba.fastjson.JSONObject;
import com.xwh.NetworkInterfaceUtils;
import com.xwh.gameframe.RoomFrame;

import javax.swing.*;

public class GetLastOperation extends Thread{
    PvpMain pvpMain;
    TurnOverAction turnOverAction;
    PlacementAction placementAction;
    String id = "2P";

    public GetLastOperation(PvpMain pvpMain) {
        this.pvpMain = pvpMain;
        this.turnOverAction = new TurnOverAction(pvpMain);
        this.placementAction = new PlacementAction(pvpMain);
    }

    @Override
    public void run() {
        super.run();
        String last = null;
        while (true) {
            try {
                Thread.sleep(1);
                String jsonString = NetworkInterfaceUtils.getLastOperation(pvpMain.token, pvpMain.uuid);
                doAction(jsonString ,last);
                last = jsonString;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doAction(String jsonString, String last) {
        if (!jsonString.equals(last)) {
            last = jsonString;
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            JSONObject data = jsonObject.getJSONObject("data");
            if ("人还没齐".equals(data.getString("err_msg"))) {
                id = "1P";
            }
            if ("对局已结束".equals(data.getString("err_msg"))) {
                gameEnd();
            }
//            System.out.println(jsonObject);
            if (jsonObject.getString("code").equals("200")) {
                if (data.getBoolean("your_turn") && (!data.getString("last_msg").substring(0, 2).equals(id) || "对局刚开始".equals(data.getString("last_msg")))) {
                    if (data.getString("last_code").split(" ").length == 3) {
                        if ("0".equals(data.getString("last_code").split(" ")[1]))
                            turnOverAction.turnOver(new Card(pvpMain, data.getString("last_code").split(" ")[2], true, pvpMain.cardsPlacement));
                        else
                            placementAction.placement(new Card(pvpMain, data.getString("last_code").split(" ")[2], true, pvpMain.cardsPlacement));
                    }
                    pvpMain.playOrder = true;
                    if (!pvpMain.isAIBattle) {
                        pvpMain.turnOverButton1.setVisible(true);
                        pvpMain.placeButton1.setVisible(true);
                        pvpMain.AIbutton1.setVisible(true);
                        pvpMain.turnOverButton1.setEnabled(true);
                        pvpMain.placeButton1.setEnabled(true);
                        pvpMain.AIbutton1.setEnabled(true);
                    }
                }
                else {
                    pvpMain.playOrder = false;
                    pvpMain.turnOverButton1.setEnabled(false);
                    pvpMain.placeButton1.setEnabled(false);
                    pvpMain.AIbutton1.setEnabled(false);
                }
            }
        }
    }

    public void gameEnd() {
        if (pvpMain.playerCards.size() < pvpMain.playerCards2.size()) {
            JOptionPane.showMessageDialog(pvpMain,"玩家1获胜！点击确定重新游戏");
        }
        else if (pvpMain.playerCards.size() > pvpMain.playerCards2.size()) {
            JOptionPane.showMessageDialog(pvpMain,"玩家2获胜！点击确定重新游戏");
        }
        else {
            JOptionPane.showMessageDialog(pvpMain,"平局！点击确定重新游戏");
        }
        if (pvpMain.ai != null)
            pvpMain.ai.stop();
        if (pvpMain.ai2 != null)
            pvpMain.ai2.stop();
        new RoomFrame(pvpMain.token);
        pvpMain.dispose();
    }

}
