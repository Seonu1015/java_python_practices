package miniRPG;

import java.util.ArrayList;

public class UnitBossMonster extends UnitMonster {

	
	// 공격 패턴이 있음
	// 2번 평타 1번 큰 공격시전
	// 큰 공격 때 주사위를 굴려서 user가 이기면 회피성공 / 지면 공격받음 <- 배틀 클래스에서 처리해도 될듯?
	
	private int attack;
	private int maxDamage;
	private int minDamage;
	private String skill;
	private int skillDamage;
	
	UnitBossMonster(String name, int health, int maxDamage, int minDamage, String skill, int skillDamage) {
		super(name, health, maxDamage, minDamage);
		this.skill = skill;
		this.skillDamage = skillDamage;
		this.setAttack();
	}
	
	int getAttack() {
		return this.attack;
	}

	void setAttack() {
		this.attack = (int) (Math.random() * (maxDamage - minDamage) + minDamage);
	}
	
	void setSkillAttack() {
		this.attack = (int) (Math.random() * (maxDamage - minDamage) + minDamage + skillDamage);
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
		System.out.println(this.getAttack());
	}

}
