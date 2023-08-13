package miniRPG;

import java.util.ArrayList;

public class UnitBoss extends UnitMonster {

	private String skill;
	private int skillDamage;

	UnitBoss(String name, int health, int maxDamage, int minDamage, String skill, int skillDamage) {
		super(name, health, maxDamage, minDamage);
		this.skill = skill;
		this.skillDamage = skillDamage;
	}

	String getskill() {
		return this.skill;
	}

	int getSkillDamage() {
		return this.skillDamage;
	}

	@Override
	void unitInfo() {
		System.out.println("-----------------------------------------");
		System.out.println("┌ 보스명 : " + this.getName());
		System.out.println("│ 체력 : " + this.getHealth());
		System.out.println("└ 스킬 : " + skill);
		System.out.println("-----------------------------------------");
	}

}
