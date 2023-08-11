package miniRPG;

public class ItemPortion extends Item {

	
	
	@Override
	void itemInfo() {		
		System.out.println("-----------------------");
		System.out.println("[[ " + this.getName() + " ]]");
		System.out.println("- 설명 : " + this.getDescription());
		System.out.println("-----------------------");
	}
}
