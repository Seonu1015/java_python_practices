package miniRPG;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UnitCharacter extends Unit implements Interface_Equip, Interface_Use {

	Scanner sc = new Scanner(System.in);

	private String birth;
	private int level = 0;
	private double exp = 0;
	
	private ArrayList<ItemPotion> potionBag;
	private static UnitCharacter instance = null; // 싱글톤 구현해봄

	UnitCharacter() {
		super();
		this.setName();
		this.setBirth();
		potionBag = new ArrayList<>();
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
			System.out.println("★ " + this.getName() + " LEVEL UP ★");
			System.out.println(this.getName() + "의 공격력이 상승합니다. (+3)");
			this.level++;
			this.exp -= 300;
			this.setAttack(3);
		}
		return this.getExp();
	}
	
	void addPotionBag(ItemPotion potion) {
		potionBag.add(potion);
	}
	
    public static UnitCharacter getInstance() {
        if (instance == null) {
            instance = new UnitCharacter();
        }
        return instance;
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
	    if (item instanceof ItemPotion) {
	        ItemPotion potion = (ItemPotion) item;
	        addPotionBag(potion);
	        System.out.println(potion.getName() +  " 을(를) 사용하였습니다.");

	        System.out.println("소지하고 있는 물약 목록: ");
	        for (ItemPotion p : potionBag) {
	            System.out.println(p.getName() + " (" + p.getHeal() + " 회복)");
	        }
	    }
	}
	
    public void usePotion() {
        if (potionBag.isEmpty()) {
            System.out.println("소지한 물약이 없습니다.");
            return;
        }

        System.out.println("사용할 물약을 선택하세요:");
        for (int i = 0; i < potionBag.size(); i++) {
            System.out.println(i + 1 + ". " + potionBag.get(i).getName());
        }

        int choice = sc.nextInt();
        if (choice >= 1 && choice <= potionBag.size()) {
            ItemPotion selectedPotion = potionBag.get(choice - 1);
            use(selectedPotion);
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }

}
