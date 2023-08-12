package miniRPG;

public class ItemPotion extends Item {

	private int heal;
	private int quantity=0;
	
	private static ItemPotion instance;
	
	ItemPotion(String name, String description, int heal) {
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
	
    public static ItemPotion getInstance() {
        if (instance == null) {
            instance = new ItemPotion("회복 포션", "체력을 회복합니다.", 30); // 기본 포션 인스턴스 생성
        }
        return instance;
    }
	
	@Override
	void itemInfo() {		
		System.out.println("-----------------------");
		System.out.println("💊 " + this.getName());
		System.out.println("┌ 설명 : " + this.getDescription());
		System.out.println("└ 회복량 : " + heal);
		System.out.println("-----------------------");
	}
	
	
}
