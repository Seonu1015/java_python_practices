package miniRPG;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UnitCharacter extends Unit implements Interface_Equip, Interface_Use {

	Scanner sc = new Scanner(System.in);

	private String birth;
	private int level = 0;
	private double exp = 0;
	
	static ArrayList<ItemPortion> portionBag = new ArrayList<>();
	
	

	UnitCharacter() {
		super();
		this.setName();
		this.setBirth();
	}

	String setName() {
		System.out.println("캐릭터명을 지어주세요.");
		String inputName = sc.next();
		this.setName(inputName);
		return this.getName();
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
			this.setAttack(27);
		} else if (selectBirth.equals("도굴꾼")) {
			this.setHealth(120);
			this.setAttack(30);
		} else if (selectBirth.equals("망국의왕족")) {
			this.setHealth(80);
			this.setAttack(32);
		} else if (selectBirth.equals("역병의사")) {
			this.setHealth(90);
			this.setAttack(25);
		} else if (selectBirth.equals("못가진자")) {
			this.setHealth(100);
			this.setAttack(10);
		}
	}

	@Override
	void unitInfo() {
		System.out.println("-----------------------");
		System.out.println("┌ 캐릭터명 : " + this.getName());
		System.out.println("│ 레벨 : " + level);
		System.out.println("└ 체력 : " + this.getHealth());
		//System.out.println(this.getAttack());
		
		//무기를 장착했다면 장착한 무기 정보도 출력하게끔?
		//포션 갯수 확인 가능하게
		
		System.out.println("-----------------------");
	}

	double getExp() {
		return this.exp;
	}

	double setExp() {
		exp = (double) (Math.random() * 90 + 20);
		System.out.println(exp + "의 경험치를 획득하였습니다.");
		return this.getExp();
	}

	double accumulateExp() {
		this.exp += this.setExp();
		if (this.exp >= 300) {
			System.out.println("🎉 " + this.getName() + " LEVEL UP 🎉");
			System.out.println(this.getName() + "의 공격력이 상승합니다. (+3)");
			this.level++;
			this.exp -= 300;
			this.setAttack(3);
		}
		return this.getExp();
	}

	@Override
	public void equip(Item item) {
		
	}

	@Override
	public void unequip(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(Item item) {
		// TODO Auto-generated method stub
		
	}

}
