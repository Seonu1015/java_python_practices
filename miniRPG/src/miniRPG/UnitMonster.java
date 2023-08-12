package miniRPG;

import java.util.ArrayList;

public class UnitMonster extends Unit implements Interface_DropItem {
	
	private int attack;
	private int maxDamage;
	private int minDamage;

	UnitMonster(String name, int health, int maxDamage, int minDamage) {
		super(name, health);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
		this.setAttack();
	}

	int getAttack() {
		return attack;
	}

	void setAttack() {
		attack = (int) (Math.random() * (maxDamage - minDamage) + minDamage);
	}
	
    public static UnitMonster createMonster(String name, int health, int maxDamage, int minDamage) {
        UnitMonster monster = new UnitMonster(name, health, maxDamage, minDamage);
        return monster;
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
		if(potion instanceof ItemPotion) {
			ItemPotion p = (ItemPotion) potion;
			p.setQuantity((int) (Math.random()*3+1));
			if (this.getHealth() <= 0) {
				System.out.println(potion.getName() + "을(를) " + p.getQuantity() + "개 획득하였습니다.");
				Interface_Use character = (Interface_Use) UnitCharacter.getInstance();
                character.use(p);
			}			
		}
	}
	
	
}
