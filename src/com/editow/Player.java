package com.editow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 简易扑克牌游戏 
 * 功能描述：
 * 1 创建一副扑克牌,包括四种花色:黑桃、红桃、梅花、方片;十三种点数: 2-10,J Q、K、A,不考虑大小王
 * 2 创建两名玩家,玩家至少要有ID、姓名、手牌等属性,手牌为扑克牌的集合
 * 3 洗牌,将之前创建的“一副扑克牌”打乱顺序
 * 4 发牌,将洗牌之后的扑克牌集合，从第一张开始,发给两名玩家,按照一人一张的方式,每人发两张
 * 5 游戏,比较两名玩家手中的扑克牌,规则为: 取两人各自手中点数最大的牌进行比较，点数大的赢;若两人各自
 *   的点数最大的牌相等,则再按花色比较。
 * @author Editow
 *
 */

public class Player {
	public String ID;
	public String name;
	public List<Card> cardLists;
	
	public Player() {
		
	}
	public Player(String Id,String name) {
		this.ID = Id;
		this.name = name;
		this.cardLists = new ArrayList<Card>();
	}
}
