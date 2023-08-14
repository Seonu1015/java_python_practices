package miniRPG;

import java.util.ArrayList;

public class UnitMonster extends Unit implements Interface_DropItem {

	UnitMonster(String name, int health, int maxDamage, int minDamage) {
		super(name, health, maxDamage, minDamage);
		setMinDamage(minDamage);
		setMaxDamage(maxDamage);
		setMaxHealth(this.getHealth());
	}
	
//	void upgradeMonster(Dungeon dungeon) {
//		this.setMinDamage((int) (getMinDamage()*1.3));
//		this.setMaxDamage((int) (getMaxDamage()*1.3));
//		this.setHealth((int) (getHealth()*1.5));
//	}

	@Override
	void unitInfo() {
		System.out.println("-----------------------------------------");
		System.out.println("┌ 몬스터명 : " + this.getName());
		System.out.println("└ 체력 : " + this.getHealth());
		System.out.println("-----------------------------------------");
	}

	@Override
	public void dropItem(Item potion) {
		if (potion instanceof ItemPotion) {
			ItemPotion p = (ItemPotion) potion;
			int amount = (int) (Math.random() * 3 + 1);
			p.increaseQuantity(amount);

			System.out.println(potion.getName() + "을(를) " + amount + "개 획득하였습니다.");
		}
	}
}
