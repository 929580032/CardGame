package com.xwh;

import com.xwh.game.BackgroundImage;
import com.xwh.game.*;
import com.xwh.game.PvpMain;
import com.xwh.game.RButton;
import com.xwh.gameframe.LoginFrame;
import org.jetbrains.annotations.TestOnly;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartGame extends JFrame implements ActionListener {
    Container container = null;
    RButton pvpButton = new RButton("双人对战");
    RButton pveButton = new RButton("人机对战");
    RButton onlineButton = new RButton("在线对战");
//    RButton ruleButton = new RButton("游戏规则");
    JLabel title = new JLabel("猪尾巴");



    public static void main(String[] args) {
        new StartGame();
    }

    public void initButtons() {
        initButton(pvpButton);
        initButton(pveButton);
        initButton(onlineButton);
//        initButton(ruleButton);
        pvpButton.setBounds(500, 330, 200, 50);
        pveButton.setBounds(500, 420, 200, 50);
        onlineButton.setBounds(500, 510, 200, 50);
//        ruleButton.setBounds(500, 550, 200, 50);
    }

    public void initButton(RButton jButton) {
//        jButton.setFocusPainted(false);
//        container.add(jButton);
//        jButton.setVisible(true);
//        jButton.addActionListener(this);
//        jButton.setFont(new Font("微软雅黑", Font.BOLD, 25));
//        container.setComponentZOrder(jButton, 0);

        container.add(jButton);
        jButton.setFont(new Font("微软雅黑", Font.BOLD, 25));
        jButton.setVisible(true);
        jButton.addActionListener(this);
        container.setComponentZOrder(jButton, 0);
        jButton.setFocusPainted(false);

        URL headerUrl = PvpMain.class.getResource("/" + "buttonImage3" + ".jpg");
        ImageIcon imageIcon = new ImageIcon(headerUrl);
        jButton.setIcon(imageIcon);
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton.setBorder(null);//设置边框
    }

    public StartGame() {
        Init();// 初始化窗体
        this.setVisible(true);
        initButtons();
    }

    public void Init() {
        this.setTitle("猪尾巴");
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner()); // 屏幕居中
        this.setLayout(null);
        container = this.getContentPane();
        BackgroundImage2 backgroundImage = new BackgroundImage2();
        container.add(backgroundImage);
        backgroundImage.setBounds(0, 0, 1200, 700);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pvpButton) {
            new PvpMain(false);
            dispose();
        }
        if (e.getSource() == pveButton) {
            new PvpMain(true);
            dispose();
        }
        if (e.getSource() == onlineButton) {
            new LoginFrame(this);
            pvpButton.setVisible(false);
            pveButton.setVisible(false);
            onlineButton.setVisible(false);
        }
    }
}
