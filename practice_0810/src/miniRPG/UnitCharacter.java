package miniRPG;

import java.util.Random;
import java.util.Scanner;

public class UnitCharacter extends Unit {

	Scanner sc = new Scanner(System.in);

	private String birth;
	private int attack;
	private int maxPhysicalDamage;
	private int minPhysicalDamage;
	private int maxMagicalDamage;
	private int minMagicalDamage;
	private int level = 0;
	private int gold = 0;

	UnitCharacter() {
		super();
		this.setName();
		this.setBirth();
		this.setAttack();
	}

	String setName() {
		System.out.println("캐릭터명을 지어주세요.");
		String inputName = sc.next();
		this.setName(inputName);
		return this.getName();
	}
	
	int getAttack() {
		return this.attack; 
	}

	void setAttack() {
		if (this.birth.equals("퇴역군인") || this.birth.equals("도굴꾼")) {
			//랜덤한 공격력???
		}
	}

	void printAttack() {
		System.out.println(this.getAttack());
	}

	void setBirth() {
		System.out.println("태생을 선택하세요. 선택한 태생에 따라 기본 스탯이 달라집니다.");
		System.out.println("퇴역군인 | 도굴꾼 | 망국의왕족 | 역병의사 | 못가진자");
		String selectBirth = sc.next();
		if (selectBirth.equals("퇴역군인")) {
			this.setHealth(130);
			this.maxPhysicalDamage = 25;
			this.minPhysicalDamage = 15;
			this.maxMagicalDamage = 3;
			this.minMagicalDamage = 1;
		} else if (selectBirth.equals("도굴꾼")) {
			this.setHealth(120);
			this.maxPhysicalDamage = 30;
			this.minPhysicalDamage = 10;
			this.maxMagicalDamage = 3;
			this.minMagicalDamage = 1;
		} else if (selectBirth.equals("망국의왕족")) {
			this.setHealth(80);
			this.maxPhysicalDamage = 3;
			this.minPhysicalDamage = 1;
			this.maxMagicalDamage = 30;
			this.minMagicalDamage = 10;
		} else if (selectBirth.equals("역병의사")) {
			this.setHealth(90);
			;
			this.maxPhysicalDamage = 3;
			this.minPhysicalDamage = 1;
			this.maxMagicalDamage = 25;
			this.minMagicalDamage = 15;
		} else if (selectBirth.equals("못가진자")) {
			this.setHealth(100);
			this.maxPhysicalDamage = 11;
			this.minPhysicalDamage = 11;
			this.maxMagicalDamage = 11;
			this.minMagicalDamage = 11;
		}
	}

	@Override
	void unitInfo() {
		System.out.println("-----------------------");
		System.out.println("┌ 캐릭터명 : " + this.getName());
		System.out.println("│ 레벨 : " + level);
		System.out.println("│ 체력 : " + this.getHealth());
		System.out.println("└ 소지금 : " + gold);
	}

}
