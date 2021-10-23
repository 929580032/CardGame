package com.xwh.gameframe;

import com.xwh.NetworkInterfaceUtils;
import com.xwh.game.BackgroundImage;
import com.xwh.game.PvpMain;
import com.xwh.game.RButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class RoomFrame extends JFrame implements ActionListener {
    Container container = null;
    RButton createGameButton = new RButton("创建新的对局");
    RButton joinGameButton = new RButton("加入一个对局");
    RButton aiBattleButton = new RButton("最强AI角逐赛");
    RButton jButton1 = new RButton("创建AI对局");
    RButton jButton2 = new RButton("加入AI对局");
    String token;
    boolean clicked = false;
//    private JTextField uuid = new JTextField();


    public void initButtons() {
        initButton(createGameButton);
        initButton(joinGameButton);
//        initButton(aiBattleButton);

        initButton(jButton1);
        initButton(jButton2);


        createGameButton.setBounds(500, 150, 200, 50);
        joinGameButton.setBounds(500, 250, 200, 50);
//        aiBattleButton.setBounds(500, 400, 200, 50);

        jButton1.setBounds(500, 350,200, 50);
        jButton2.setBounds(500, 450,200, 50);

    }

    public void initButton(RButton jButton) {
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

    public static void main(String[] args) {
        new RoomFrame("");
    }
    public RoomFrame(String token) {
        this.token = token;
        Init();// 初始化窗体
        this.setVisible(true);
        initButtons();

//        container.add(uuid);
//        uuid.setBounds(200, 300, 200, 50);
//        uuid.setVisible(true);
//        uuid.setFont(new Font("微软雅黑", Font.BOLD, 12));
//        container.setComponentZOrder(uuid, 0);
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
            System.out.println(uuid);
            JOptionPane.showMessageDialog(this,"你的房间号为" + uuid);
            new PvpMain(true, token, uuid);
            dispose();
        }
        if (e.getSource() == joinGameButton) {
            new EnterGameFrame(token);
            dispose();
        }
        if (e.getSource() == jButton1) {
            String uuid = NetworkInterfaceUtils.createGame(token, false);
            System.out.println(uuid);
            JOptionPane.showMessageDialog(this,"你的房间号为" + uuid);
            new PvpMain(true, token, uuid, true);
            dispose();
        }
        if (e.getSource() == jButton2) {
            new EnterGameFrame(token, true);
            dispose();
        }
    }
}
