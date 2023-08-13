package miniRPG;

import java.util.ArrayList;

public class UnitBoss extends UnitMonster implements Interface_DropItem {

	private String skill;
	private int skillDamage;

	UnitBoss(String name, int health, int maxDamage, int minDamage, String skill, int skillDamage) {
		super(name, health, maxDamage, minDamage);
		this.skill = skill;
		this.skillDamage = skillDamage;
		this.getAttack();
	}

	int getAttack() {
		return (int) (Math.random() * (this.getMaxDamage() - this.getMinDamage()) + this.getMinDamage());
	}

	String getskill() {
		return this.skill;
	}

	int getSkillDamage() {
		return this.skillDamage;
	}

	@Override
	void unitInfo() {
		System.out.println("-----------------------");
		System.out.println("┌ 보스명 : " + this.getName());
		System.out.println("│ 체력 : " + this.getHealth());
		System.out.println("└ 스킬 : " + skill);
		//System.out.println(this.getAttack());
		System.out.println("-----------------------");
	}
	
	@Override
	public void dropItem(Item weapon) {		
		if(weapon instanceof ItemWeapon) {
			ItemWeapon w = (ItemWeapon) weapon;
			if (this.getHealth() <= 0) {
				System.out.println(w.getName() + "을(를) 획득하였습니다.");
			}
			System.out.println("장착하시겠습니까?");
			
		}		
	}

}
