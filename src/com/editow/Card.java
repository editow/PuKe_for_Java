package com.editow;

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
public class Card implements Comparable<Card>{
	public String pointNum;
	public String typeColor;
	public int weight;
	
	public Card() {
		this.pointNum = "";
		this.typeColor = "";
		this.weight = 0;
	}
	public Card(String pointNum,String typeColor,int weight) {
		this.pointNum = pointNum;
		this.typeColor = typeColor;
		this.weight = weight;
	}
	@Override
	public int compareTo(Card arg0) {
		// TODO Auto-generated method stub
		if(this.weight > arg0.weight)
			return 1;
		else 
			return 0;
	}
}
