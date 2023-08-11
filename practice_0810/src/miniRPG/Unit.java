package miniRPG;

abstract class Unit {
	
	private String name;
	private int health;
	private int attack;
	
	Unit() {
		
	}
	
	Unit(String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	int getHealth() {
		return health;
	}
	
	void setHealth(int health) {
		this.health = health;
	}
	
	int getAttack() {
		return attack;
	}

	void setAttack(int attack) {
		this.attack = attack;
	}
	
	abstract void unitInfo();
	
}
