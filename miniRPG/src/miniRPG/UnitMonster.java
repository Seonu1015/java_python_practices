package miniRPG;

import java.util.ArrayList;

public class UnitMonster extends Unit implements Interface_DropItem {
	
	private int attack;
	private int maxDamage;
	private int minDamage;

	private static final ArrayList<UnitMonster> monsterList = new ArrayList<>();
	
	UnitMonster(String name, int health, int maxDamage, int minDamage) {
		super(name, health);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
		this.setAttack();
		this.addMonster();
	}

	int getAttack() {
		return attack;
	}

	void setAttack() {
		attack = (int) (Math.random() * (maxDamage - minDamage) + minDamage);
	}
	
    public static ArrayList<UnitMonster> getMonsterList() {
        return monsterList;
    }
	
	void addMonster() {
		monsterList.add(this);
	}
	
	@Override
	void unitInfo() {
		System.out.println("-----------------------");
		System.out.println("┌ 몬스터명 : " + this.getName());
		System.out.println("└ 체력 : " + this.getHealth());
		//System.out.println(this.getAttack());
		System.out.println("-----------------------");
	}

	@Override
	public void dropItem(Item potion) {
	    if (potion instanceof ItemPotion) {
	        ItemPotion p = (ItemPotion) potion;
	        int amount = (int) (Math.random() * 3 + 1);
	        p.increaseQuantity(amount);
	        
	        if (this.getHealth() <= 0) {
	            System.out.println(potion.getName() + "을(를) " + amount + "개 획득하였습니다.");
	        }
	    }
	}
	
	
}
