package com.editow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * 简易扑克牌游戏 
 * 功能描述：
 * 1 创建一副扑克牌,包括四种花色:黑桃、红桃、梅花、方片;十三种点数: 2-10,J Q、K、A,不考虑大小王
 * 2 创建两名玩家,玩家至少要有ID、姓名、手牌等属性,手牌为扑克牌的集合
 * 3 洗牌,将之前创建的“一副扑克牌”打乱顺序
 * 4 发牌,将洗牌之后的扑克牌集合，从第一张开始,发给两名玩家,按照一人一张的方式,每人发两张
 * 5 游戏,比较两名玩家手中的扑克牌,规则为: 取两人各自手中点数最大的牌进行比较，点数大的赢;
 * @author Editow
 *
 */

public class InitGame {
	public final static String[] puStr = new String[]{
			"A","2","3","4","5","6","7","8","9","10","J","Q","K"
	};
	public final static String[] puColor = new String[]{
			"♦","♣","♥","♠"
	};
	public Player player1 = new Player();	// 玩家1
	public Player player2 = new Player();	// 玩家2
	public List<Card> cardList = new ArrayList<Card>();	// 用以存储纸牌数组
	public final int len = 13;				// 单色纸牌数量
	public final int lenColor = 4;			// 花色数量
	public Random random = new Random();
	public Scanner console = new Scanner(System.in);
	
	/**
	 * 创建一副牌
	 * @param args
	 */
	public void createCard() {
		System.out.println("创建扑克牌...");
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < lenColor; j ++) {
				Card card = new Card(puStr[i],puColor[j],lenColor*i+j+1);
				cardList.add(card);
			}
		}
		System.out.println("扑克牌创建成功！");
	}
	
	/**
	 * 初始化玩家1，玩家2
	 * @param args
	 */
	public void initPlayers() {
		System.out.println("初始化玩家...");
//		player1 = new Player("1","毛球");
//		player2 = new Player("2","毒王");
		List<Player> tempPlayer = new ArrayList<Player>();
		while(true) {
			for(int i = 0; i < 2; i++) {
				String strId;
				try {
					System.out.println("请输入第" + (i+1) + "名玩家ID和姓名");
					System.out.println("请输入ID:");
					strId = getStr();
					System.out.println("输入姓名:");
					String name = getStr();
					tempPlayer.add(new Player(strId,name));
				}catch(Exception err) {
					System.out.println("输入错误，请重新输入！");
					tempPlayer.clear();
					break;
				}
				
			}
			if(tempPlayer.size() != 2)
				continue;
			player1 = tempPlayer.get(0);
			player2 = tempPlayer.get(1);
			System.out.println("欢迎玩家" + player1.ID + ":" + player1.name);
			System.out.println("欢迎玩家" + player2.ID + ":" + player2.name);
			break;
		}
		
		
	}
	public String getStr() {
		String str = console.next();
		return str;
	}
	/**
	 * 洗牌,将之前创建的“一副扑克牌”打乱顺序
	 * @param args
	 */
	public void getRandomCard() {
		System.out.println("开始洗牌...");
		Set cardSet = new HashSet();
		cardSet = getCardSet(cardList);
		cardList = setToList(cardSet);
		System.out.println("洗牌结束!");
	}
	/**
	 * 将cardList转换为Set
	 * @param cardList
	 * @return
	 */
	public Set getCardSet(List cardList) {
		Set cardSet = new HashSet();
		for(Object obj : cardList) {
			Card cardTemp = (Card) obj;
			cardSet.add(cardTemp);
		}
		return cardSet;
	}
	/**
	 * 将cardSet转换为cardList
	 * @param cardSet
	 * @return
	 */
	public List setToList(Set cardSet) {
		List cardList = new ArrayList();
		for(Object obj : cardSet) {
			Card cardTemp = (Card) obj;
			cardList.add(cardTemp);
		}
		return cardList;
	}
	
	/**
	 * 开始发牌，每人发两张牌...
	 * @param args
	 */
	public void playerGetPuKe(Player player1,Player player2) {
//		创建一个整型List，用以存储随机生成索引的值
		List<Integer> tempInt = new ArrayList<Integer>();
		
		System.out.println("开始发牌...");
		for(int i = 0; i < 2; i++) {
			int temp;
//			先对玩家1开始取牌
			do {
				temp = random.nextInt(len*lenColor);
			}while(tempInt.contains(temp));
			tempInt.add(temp);
			System.out.println("玩家:" + player1.name + "-拿牌...");
			player1.cardLists.add(cardList.get(temp));
//			再对玩家2开始取牌
			do {
				temp = random.nextInt(len*lenColor);
			}while(tempInt.contains(temp));
			System.out.println("玩家:" + player2.name + "-拿牌...");
			tempInt.add(temp);
			player2.cardLists.add(cardList.get(temp));
		}
		System.out.println("发牌结束！");
	}
	
	/**
	 * 5 游戏,比较两名玩家手中的扑克牌,规则为: 取两人各自手中点数最大的牌进行比较，点数大的赢;
	 * @param args
	 */
	public void playGame(Player p1,Player p2) {
//		开始游戏
		Player tempPlayer = new Player();	// 用以存储赢家
		
		playerGetPuKe(p1, p2);				// 发牌
		System.out.print(p1.name + "手牌为:");
		forEachList(p1.cardLists);			// 打印player1手牌
		System.out.print(p2.name + "手牌为:");
		forEachList(p2.cardLists);			// 打印player2手牌
		
		System.out.println("开始游戏!");
		tempPlayer = getWinner(p1,p2);
		System.out.println("Winner:"+ tempPlayer.name + "[" + 
				tempPlayer.cardLists.get(0).typeColor + 
				tempPlayer.cardLists.get(0).pointNum + "]");
	}
	
	// 比较获得最大手牌，返回赢家
	public Player getWinner(Player p1,Player p2) {
		Card tempCard = new Card();			// 定义临时变量tempCard
		Player tempPlayer = new Player();	// 用以存储赢家
		
		for(Card temp1 : p1.cardLists) {
			for(Card temp2 : p2.cardLists) {
				if(temp2.weight > tempCard.weight) {
					tempPlayer = p2;
					tempCard = temp2;
				}
				if(temp1.weight > tempCard.weight) {
					tempPlayer = p1;
					tempCard = temp1;
				}
			}
		}
		tempPlayer.cardLists.clear();		// 清除赢家的手牌
		tempPlayer.cardLists.add(tempCard);	// 添加赢家最大手牌
		return tempPlayer;
	}
	
	// 遍历数组
	public void forEachList(List<Card> objs) {
//		int i = 1;
//		System.out.print("ForEachList遍历:");
		for(Card temp : objs) {
//			if(i%lenColor == 0)
//				System.out.println("");
			System.out.print(temp.typeColor + temp.pointNum + "\t");	/*+ ":" + temp.weight*/ 
//			i++;
		}
		System.out.println("");
	}
	
	public void forEachSet(Set<Card> objs) {
		int i = 0;
//		System.out.print("ForEachSet遍历:");
		for(Card temp : objs) {
			if(i%lenColor == 0)
				System.out.println("");
			System.out.print(temp.typeColor + temp.pointNum + ":" + temp.weight + "\t");
			i++;
		}
//		System.out.println("");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InitGame ig = new InitGame();
			ig.createCard();								// 初始化纸牌
	
			ig.forEachList(ig.cardList);
			ig.initPlayers();								// 初始化玩家
			
			ig.getRandomCard();								// 洗牌
	//		ig.forEachList(ig.cardList);
			
			ig.playGame(ig.player1,ig.player2);				// 开始游戏
		}catch(NullPointerException err) {
			System.out.println(err);
		}catch(Exception err) {
			System.out.println(err);
		}
		
		
	}
}
