package com.xwh.gameframe;

import com.xwh.NetworkInterfaceUtils;
import com.xwh.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{

    private Container c = this.getContentPane();
    private JLabel a1 = new JLabel("学   号");
    private JTextField username = new JTextField();
    private JLabel a2 = new JLabel("密   码");
    private JPasswordField password = new JPasswordField();
    private JButton okbtn = new JButton("确定");
    private JButton cancelbtn = new JButton("取消");
    StartGame startGame;

    public LoginFrame(StartGame startGame) {

        this.startGame = startGame;

        this.setTitle("登录");
        //设置窗体的位置及大小
        this.setBounds(600, 200, 300, 220);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(this.getOwner());
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        this.setVisible(true);
        listerner();
    }
    public void init() {
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        JLabel loginJLabel = new JLabel("登录");
        loginJLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
        titlePanel.add(loginJLabel);
        c.add(titlePanel, "North");

        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 50, 20);
        a1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        a2.setBounds(50, 60, 50, 20);
        a2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        username.setBounds(110, 20, 120, 20);
        password.setBounds(110, 60, 120, 20);
        fieldPanel.add(username);
        fieldPanel.add(password);
        c.add(fieldPanel, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        okbtn.setFont(new Font("微软雅黑", Font.BOLD, 15));
        cancelbtn.setFont(new Font("微软雅黑", Font.BOLD, 15));
        buttonPanel.add(cancelbtn);
        c.add(buttonPanel, "South");
    }
    //测试
    public static void main(String[] args) {
        new LoginFrame(null);
    }
    public void listerner() {
        //确认按下去获取
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String uname = username.getText() != null ? username.getText() : "031902123";
//                String pwd = String.valueOf(password.getPassword()) != null ? String.valueOf(password.getPassword()) : "YXI691128";
                String uname;
                String pwd;
                if ("".equals(username.getText())) {
                    uname = "031902123";
                    pwd = "YXI691128";
                }
                else if ("1".equals(username.getText())) {
                    uname = "031902117";
                    pwd = "161503nyh!";
                }
                else {
                    uname = username.getText();
                    pwd = String.valueOf(password.getPassword());
                }
                new RoomFrame(NetworkInterfaceUtils.login(uname, pwd));
                startGame.dispose();
//                new RoomFrame(NetworkInterfaceUtils.login(("1".equals(uname) ? "031902123" : "031902117"), ("1".equals(uname) ? "YXI691128" : "161503nyh!")));
                dispose();
            }
        });
        //取消按下去清空
        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame.dispose();
                new StartGame();
                dispose();
            }
        });
    }
}