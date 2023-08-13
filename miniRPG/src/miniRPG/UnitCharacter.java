package miniRPG;

import java.util.Scanner;

public class UnitCharacter extends Unit implements Interface_Equip, Interface_Use {

	private Scanner sc = new Scanner(System.in);

	private String birth;
	private int level = 0;
	private double exp = 0;
	private int maxHealth;
	private ItemWeapon equippedWeapon;
	private int baseAttack;
	
	private static UnitCharacter instance;

	UnitCharacter() {
		super();
		this.setName();
		this.setBirth();
		
		ItemWeapon defaultWeapon = ItemWeapon.getInstance();
	    this.equip(defaultWeapon);
	}

    void setName() {
        System.out.println("캐릭터명을 지어주세요.");
        String inputName = sc.next();
        this.setName(inputName);
    }
	
	String getBirth() {
		return birth;
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
		this.maxHealth = this.getHealth();
	}
	
	int getMaxHealth() {
		return maxHealth;
	}

	@Override
	void unitInfo() {
		System.out.println("-----------------------");
		System.out.println("┌ 캐릭터명 : " + this.getName());
		System.out.println("│ 레벨 : " + level);
		System.out.println("│ 체력 : " + this.getHealth() + " / " + this.getMaxHealth());
	    if (equippedWeapon != null) {
	        System.out.println("│ 장착무기 : " + equippedWeapon.getName());
	        System.out.println("└ 공격력 : " + this.getMinDamage() + " ~ " + this.getMaxDamage());
	    } else {
	        System.out.println("└ 장착무기 : 없음");
	    }
	}

	double getExp() {
		return this.exp;
	}

	double setExp() {
		exp = Math.round((Math.random() * 90 + 20) * 100.00) / 100.00;
		System.out.println(exp + "의 경험치를 획득하였습니다.");
		return this.getExp();
	}

	double accumulateExp() {
		this.exp += this.setExp();
		if (this.exp >= (100+ (this.level*50))) {
			System.out.println("-----------------------");
			System.out.println("★ " + this.getName() + " LEVEL UP ★");
			System.out.println(this.getName() + "의 공격력이 상승합니다. (+3)");
			System.out.println(this.getName() + "의 최대 체력이 상승합니다. (+5)");
			System.out.println("-----------------------");
			this.level++;
			this.exp -= 100;
			this.setMinDamage(this.getMinDamage() + 3);
			this.setMaxDamage(this.getMaxDamage() + 3);
			this.maxHealth += 5;
		}
		return this.getExp();
	}
	
    public static UnitCharacter getInstance() {
        if (instance == null) {
            instance = new UnitCharacter();
        }
        return instance;
    }

	@Override
	public void equip(Item item) {
	    if (item instanceof ItemWeapon) {
	        ItemWeapon weapon = (ItemWeapon) item;

	        if (equippedWeapon != null) {
	            unequip(equippedWeapon);
	        }
	        
	        this.baseAttack = this.getAttack();

	        this.setMinDamage(this.baseAttack + weapon.getMinDamage());
	        this.setMaxDamage(this.baseAttack + weapon.getMaxDamage());

	        equippedWeapon = weapon;
	        System.out.println(weapon.getName() + "을(를) 장착했습니다.");
	        randomNewDamage();
	    }
	}
	
	private void randomNewDamage() {
	    int randomDamage = (int) (Math.random() * (this.getMaxDamage() - this.getMinDamage())) + this.getMinDamage();
	    setAttack(randomDamage);
	}
	
	@Override
	public void unequip(Item item) {
	    if (equippedWeapon != null) {
	        System.out.println(equippedWeapon.getName() + "을(를) 해제했습니다.");
	        equippedWeapon = null;
	    } else {
	        System.out.println("장착한 무기가 없습니다.");
	    }
	}

	@Override
	public void use() {
	    if (this.getHealth() >= this.getMaxHealth()) {
	        System.out.println("이미 최대 체력입니다.");
	        return;
	    }

	    if (ItemPotion.getInstance().getQuantity() > 0) {
	        int healAmount = ItemPotion.getInstance().getHeal();
	        this.setHealth(Math.min(this.getHealth() + healAmount, this.getMaxHealth()));

	        ItemPotion.getInstance().decreaseQuantity(1);
	        System.out.println(this.getName() + "이(가) 회복 포션을 사용하여 " + healAmount + "만큼 회복합니다.");
	        System.out.println("남은 체력: " + this.getHealth() + " / " + this.getMaxHealth());
	    } else {
	        System.out.println("회복 포션이 없습니다.");
	    }
	}


}
