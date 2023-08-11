package miniRPG;

public class ItemPortion extends Item {

	private int heal;
	private int quantity;
	
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
	
	int getQuantity() {
		return quantity;
	}
	
	void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	void itemInfo() {		
		System.out.println("-----------------------");
		System.out.println("[[ " + this.getName() + " ]]");
		System.out.println("- 설명 : " + this.getDescription());
		System.out.println("-----------------------");
	}
	
	
}
