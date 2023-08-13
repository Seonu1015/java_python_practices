package miniRPG;

public class ItemWeapon extends Item {

	private int maxDamage;
	private int minDamage;
	private static ItemWeapon instance;

	ItemWeapon() {
		super("누군가 쓰다버린 검", "과거 잘나갔던 용병이 쓰던 검으로 시간이 지나 꽤 녹슬었다.");
		maxDamage = 3;
		minDamage = 1;
	}

	ItemWeapon(String name, String description, int maxDamage, int minDamage) {
		super(name, description);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
	}

	ItemWeapon(String name, String description, int maxDamage, int minDamage, String skill, int skillDamage) {
		super(name, description);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
	}

	int getMaxDamage() {
		return maxDamage;
	}

	int getMinDamage() {
		return minDamage;
	}

	public static ItemWeapon getInstance() {
		if (instance == null) {
			instance = new ItemWeapon();
		}
		return instance;
	}

	@Override
	void itemInfo() {
		System.out.println("-----------------------------------------");
		System.out.println("🗡 " + this.getName());
		System.out.println("┌ 설명 : " + this.getDescription());
		System.out.println("└ 공격력 : " + minDamage + " ~ " + maxDamage);
		System.out.println("-----------------------------------------");
	}

}
