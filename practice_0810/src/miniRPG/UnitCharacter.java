package miniRPG;

import java.util.Scanner;

public class UnitCharacter extends Unit {
	
	Scanner sc = new Scanner(System.in);

	private static final String[] birth = {"퇴역군인", "도굴꾼", "망국의왕족", "역병의사", "못가진자" };
	
	private int endurance = 20;
	private int strength;
	private int agility;
	private int intelligence;
	private int faith;
	
	UnitCharacter() {
		super();
		
	}
	
	String setName() {
		System.out.println("캐릭터명을 지어주세요.");
		String inputName = sc.next();
		return this.getname();
	}
	
	void setBirth() {
		System.out.println("태생을 선택하세요. 선택한 태생에 따라 기본 스탯이 달라집니다.");
		System.out.println("퇴역군인 | 도굴꾼 | 망국의왕족 | 역병의사 | 못가진자");
		String selectBirth = sc.next();
		if(selectBirth.equals("퇴역군인")) {
			this.health = 150;
			this.strength = 10;
			this.agility = 6;
			this.intelligence = 1;
			this.faith = 3;
		} else if (selectBirth.equals("도굴꾼")) {
			this.health = 120;
			this.strength = 6;
			this.agility = 10;
			this.intelligence = 3;
			this.faith = 1;
		} else if (selectBirth.equals("망국의왕족")) {
			this.health = 80;
			this.strength = 1;
			this.agility = 3;
			this.intelligence = 10;
			this.faith = 6;
		} else if (selectBirth.equals("역병의사")) {
			this.health = 100;
			this.strength = 2;
			this.agility = 2;
			this.intelligence = 6;
			this.faith = 10;
		} else if (selectBirth.equals("못가진자")) {
			this.health = 100;
			this.strength = 5;
			this.agility = 5;
			this.intelligence = 5;
			this.faith = 5;
		}
	}
	
}
