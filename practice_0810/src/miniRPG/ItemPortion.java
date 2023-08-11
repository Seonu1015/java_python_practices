package miniRPG;

public class ItemPortion extends Item {

	private int heal;
	
	ItemPortion(String name, String description, int heal) {
		super(name, description);
		this.heal = heal;
	}
	
	int getHeal() {
		return heal;
	}
	
	void setHeal(int heal) {
		this.heal = heal;
	}
	
	@Override
	void itemInfo() {		
		System.out.println("-----------------------");
		System.out.println("[[ " + this.getName() + " ]]");
		System.out.println("- 설명 : " + this.getDescription());
		System.out.println("-----------------------");
	}
	
	
}
