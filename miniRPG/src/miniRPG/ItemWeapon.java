package miniRPG;

public class ItemWeapon extends Item {

	private int maxDamage;
	private int minDamage;
//	private String skill;
//	private int skillDamage;
	
	ItemWeapon () {
		super("누군가 쓰다버린 검", "과거 잘나갔던 용병이 쓰던 검으로 시간이 지나 꽤 녹슬었다.");
		maxDamage = 3;
		minDamage = 1;
		//skill = "없음";
	}
	
	ItemWeapon (String name, String description, int maxDamage, int minDamage) {
		super(name, description);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;		
		//skill = "없음";
	}
	
	ItemWeapon (String name, String description, int maxDamage, int minDamage, String skill, int skillDamage) {
		super(name, description);
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;		
//		this.skill = skill;
//		this.skillDamage = skillDamage;
	}
	
	@Override
	void itemInfo() {		
		System.out.println("-----------------------");
		System.out.println("🗡 " + this.getName());
		System.out.println("┌ 설명 : " + this.getDescription());
		System.out.println("└ 공격력 : " + minDamage + " ~ " + maxDamage);		
		//System.out.println("└ 스킬 : " + skill);
		System.out.println("-----------------------");
	}
	
}
