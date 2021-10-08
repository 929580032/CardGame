package com.xwh.game;

import com.xwh.NetworkInterfaceUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PvpMain extends JFrame  {
	int[] cardColorCount = new int[]{13, 13, 13, 13};
	Container container = null;// 定义容器
	List<Card> cardList = new ArrayList<>();//卡组
	public List<Card> cardsPlacement = new ArrayList<>();//卡牌放置区
	List<Card> playerCards = new ArrayList<>();//玩家1手牌
	List<Card> playerCards2 = new ArrayList<>();//玩家2手牌
	boolean playOrder = true;//true表示轮到玩家1, false表示轮到玩家2
	JButton turnOverButton1 = new JButton("翻牌");
	JButton placeButton1 = new JButton("放置");
	JButton turnOverButton2 = new JButton("翻牌" );
	JButton placeButton2 = new JButton("放置");
	TurnOverAction turnOverAction = new TurnOverAction(this);
	PlacementAction placementAction = new PlacementAction(this);
	JLabel jLabel = new JLabel("剩余牌数:52");
	JLabel jLabel1 = new JLabel("剩余牌数:0");
	JLabel[] colorsJLabel = new JLabel[]{new JLabel("黑桃:13"), new JLabel("梅花:13"), new JLabel("红桃:13"), new JLabel("方块:13")};
	JButton AIbutton = new JButton("托管");
	JButton AIbutton1 = new JButton("托管");
	AI ai = null;
	AI ai2 = null;
	public boolean online = false;
	public String token = "";
	public String uuid = "";

	public PvpMain(boolean online, String token, String uuid) {
		Init();// 初始化窗体
		this.setVisible(true);
		CardInit();// 卡组初始化
		buttonsInit();//按钮初始化
		jLabelsInit();//标签初始化
		this.online = online;
		this.token = token;
		this.uuid = uuid;
		GetLastOperation getLastOperation = new GetLastOperation(this);
		getLastOperation.start();
		turnOverButton2.setVisible(false);
		placeButton2.setVisible(false);
		AIbutton.setVisible(false);
		turnOverButton1.setVisible(false);
		placeButton1.setVisible(false);
		AIbutton1.setVisible(false);
	}

	public PvpMain(boolean pveFlag) {
		Init();// 初始化窗体
		this.setVisible(true);
		CardInit();// 卡组初始化
		buttonsInit();//按钮初始化
		jLabelsInit();//标签初始化

		if (pveFlag) {
			AI ai = new AI(this, true);
			ai.start();
			turnOverButton2.setVisible(false);
			placeButton2.setVisible(false);
			AIbutton.setVisible(false);
		}
	}

	public void jLabelInit(Component component, int x, int y, int width, int height) {
		container.add(component);
		component.setFont(new Font("微软雅黑", Font.BOLD, 25));
		component.setSize(width, height);
		component.setLocation(x, y);
		component.setVisible(true);
		container.setComponentZOrder(component, 0);
	}

	public void jLabelsInit() {
		jLabelInit(jLabel, 250, 275, 150, 100);
		jLabelInit(jLabel1, 800, 275, 150, 100);
		for (int i = 0; i < 4; i++) {
			jLabelInit(colorsJLabel[i], 100, 200 + 50 * i, 150, 100);
		}
	}

	public void buttonInit(JButton jButton, int x, int y, int width, int height, ActionListener actionListener) {
		container.add(jButton);
		jButton.setFont(new Font("微软雅黑", Font.BOLD, 25));
		jButton.setSize(width, height);
		jButton.setLocation(x, y);
		jButton.setVisible(true);
		jButton.addActionListener(actionListener);
		container.setComponentZOrder(jButton, 0);
		jButton.setFocusPainted(false);
	}

	//按钮初始化
	public void buttonsInit() {
		buttonInit(turnOverButton1, 400, 450, 100, 50, turnOverAction);
		buttonInit(turnOverButton2, 400, 150, 100, 50, turnOverAction);
		buttonInit(placeButton1, 700, 450, 100, 50, placementAction);
		buttonInit(placeButton2, 700, 150, 100, 50, placementAction);
		buttonInit(AIbutton, 150, 150, 150, 50, new AIButtonAction(this));
		buttonInit(AIbutton1, 150, 450, 150, 50, new AIButtonAction2(this));
	}
	/**
	 * 初始化窗体
	 */
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

	// 卡组初始化
	public void CardInit() {
		for (int i = 1; i <= 52; i++) {
			String color = "";
			switch ((i - 1) / 13) {
				case 0: color = "黑桃";break;
				case 1: color = "草花";break;
				case 2: color = "红桃";break;
				case 3: color = "方块";break;
			}
			Card card = new Card(this, Integer.toString(i), false, color, (i % 13 == 0) ? 13 : i % 13, cardsPlacement);
			cardList.add(card);

//			Card playerCard = new Card(this, Integer.toString(i), true, color, (i % 13 == 0) ? 13 : i % 13, cardsPlacement);
//			playerCard.canClick = true;
//			playerCards.add(new Card(this, playerCard.transfer(), true, cardsPlacement));
//			Card playerCard2 = new Card(this, Integer.toString(i), true, color, (i % 13 == 0) ? 13 : i % 13, cardsPlacement);
//			playerCards2.add(playerCard2);

		}
		Collections.shuffle(cardList);
		for (Card card : cardList) {
			container.add(card);
			card.setLocation(400, 250);
			container.setComponentZOrder(card, 0);
		}
		Card card = new Card(this, "null", true, null, 0, null);
		container.add(card);
		card.setLocation(700, 250);
		container.setComponentZOrder(card, 0);


//		for (int i = 0; i < playerCards.size(); i++) {
//			Card playerCard = playerCards.get(i);
//			System.out.println(playerCard);
//			container.add(playerCard);
//		}
//		MoveCardUtil.rePosition(this, playerCards, 1);
//		for (int i = 0; i < playerCards2.size(); i++) {
//			Card playerCard = playerCards2.get(i);
//			container.add(playerCard);
//		}
//		MoveCardUtil.rePosition(this, playerCards2, 2);

	}

	public static void main(String[] args) {
		new PvpMain(false);
	}
}
