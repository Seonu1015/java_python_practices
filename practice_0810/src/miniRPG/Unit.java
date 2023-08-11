package miniRPG;

abstract class Unit {
	
	private String name;
	private int health;
	
	Unit() {
		
	}
	
	Unit(String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	String getName() {
		return name;
	}
	
	int getHealth() {
		return health;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setHealth(int health) {
		this.health = health;
	}
	
	abstract void unitInfo();
	
}
