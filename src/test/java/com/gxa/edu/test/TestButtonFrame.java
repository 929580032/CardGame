
package com.gxa.edu.test;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class TestButtonFrame extends JFrame {
    RButton b2;

    public TestButtonFrame() {
        super("测试");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.getContentPane().setLayout(new FlowLayout());
        b2 = new RButton("RButton");
        this.getContentPane().add(b2);

        this.setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new TestButtonFrame();
    }

}