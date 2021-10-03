package com.xwh;

import com.xwh.game.BackgroundImage;
import com.xwh.game.PvpMain;
import com.xwh.gameframe.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGame extends JFrame implements ActionListener {
    Container container = null;
    JButton pvpButton = new JButton("双人对战");
    JButton pveButton = new JButton("人机对战");
    JButton onlineButton = new JButton("在线对战");

    public static void main(String[] args) {
        new StartGame();
    }

    public void initButtons() {
        initButton(pvpButton);
        initButton(pveButton);
        initButton(onlineButton);
        pvpButton.setBounds(500, 200, 200, 50);
        pveButton.setBounds(500, 300, 200, 50);
        onlineButton.setBounds(500, 400, 200, 50);
    }

    public void initButton(JButton jButton) {
        jButton.setFocusPainted(false);
        container.add(jButton);
        jButton.setVisible(true);
        jButton.addActionListener(this);
        jButton.setFont(new Font("微软雅黑", Font.BOLD, 25));
        container.setComponentZOrder(jButton, 0);
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
        BackgroundImage backgroundImage = new BackgroundImage();
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
            new LoginFrame();
            dispose();
        }
    }
}
