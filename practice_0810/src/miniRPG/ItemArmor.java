package miniRPG;

public class ItemArmor extends Item {
	
	private int defense;
	
	ItemArmor() {
		super("누군가 쓰다버린 갑옷", "과거 잘나갔던 용병이 착용했던 갑옷으로 시간이 지나 꽤 녹슬었다.");
		defense = 3;
	}

	@Override
	void itemInfo() {		
		System.out.println("-----------------------");
		System.out.println("[[ " + this.getName() + " ]]");
		System.out.println("┌ 설명 : " + this.getDescription());
		System.out.println("└ 방어력 : " + defense);	
		System.out.println("-----------------------");
	}

	
	
}
