package com.xwh.gameframe;

import com.xwh.NetworkInterfaceUtils;
import com.xwh.StartGame;
import com.xwh.game.BackgroundImage;
import com.xwh.game.PvpMain;
import com.xwh.gameframe.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame implements ActionListener {
    Container container = null;
    JButton createGameButton = new JButton("创建新的对局");
    JButton joinGameButton = new JButton("加入一个对局");
    String token;
    private JTextField uuid = new JTextField();


    public void initButtons() {
        initButton(createGameButton);
        initButton(joinGameButton);
        createGameButton.setBounds(500, 200, 200, 50);
        joinGameButton.setBounds(500, 300, 200, 50);
    }

    public void initButton(JButton jButton) {
        jButton.setFocusPainted(false);
        container.add(jButton);
        jButton.setVisible(true);
        jButton.addActionListener(this);
        jButton.setFont(new Font("微软雅黑", Font.BOLD, 25));
        container.setComponentZOrder(jButton, 0);
    }

    public static void main(String[] args) {
        new StartFrame("");
    }
    public StartFrame(String token) {
        this.token = token;
        Init();// 初始化窗体
        this.setVisible(true);
        initButtons();

        container.add(uuid);
        uuid.setBounds(200, 300, 200, 50);
        uuid.setVisible(true);
        uuid.setFont(new Font("微软雅黑", Font.BOLD, 25));
        container.setComponentZOrder(uuid, 0);
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
        if (e.getSource() == createGameButton) {
            String uuid = NetworkInterfaceUtils.createGame(token, false);
            System.out.println("uuid:" + uuid);

            JFrame jFrame = new JFrame();
            jFrame.setLocationRelativeTo(jFrame.getOwner());
            jFrame.setSize(500, 500);
            jFrame.add(new JTextField(uuid));
            jFrame.setVisible(true);
            new PvpMain(true, token, uuid);

            dispose();
        }
        if (e.getSource() == joinGameButton) {
            NetworkInterfaceUtils.joinGame(token, uuid.getText());
            new PvpMain(true, token, uuid.getText());
            dispose();
        }
    }
}
