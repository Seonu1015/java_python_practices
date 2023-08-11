package miniRPG;

import java.util.ArrayList;

public class UnitMonster extends Unit {

	private int attack;
	private int maxDamage;
	private int minDamage;
	
	static ArrayList<UnitMonster> monsterList = new ArrayList<>();
	
	UnitMonster(String name, int health, int maxDamage, int minDamage) {
		super(name, health);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
		this.setAttack();
		monsterList.add(this);
	}
	
	int getAttack() {
		return this.attack;
	}

	void setAttack() {
		this.attack = (int) (Math.random() * (maxDamage - minDamage) + minDamage);
	}
	
	@Override
	void unitInfo() {
		System.out.println("-----------------------");
		System.out.println("┌ 몬스터명 : " + this.getName());
		System.out.println("└ 체력 : " + this.getHealth());
		System.out.println(this.getAttack());
	}
}
