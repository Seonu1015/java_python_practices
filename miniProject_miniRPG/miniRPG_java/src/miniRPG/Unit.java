package miniRPG;

abstract class Unit {

	private String name;
	private int health;
	private int maxHealth;
	private int attack;
	private int randomAttack;
	private int maxDamage;
	private int minDamage;

	Unit() {

	}

	Unit(String name, int health, int maxDamage, int minAttack) {
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
		if (health <= 0) {
			return health = 0;
		} else {
			return health;
		}
	}

	void setHealth(int health) {
		this.health = health;
	}

	int getMaxHealth() {
		return maxHealth;
	}

	void setMaxHealth(int health) {
		this.maxHealth = health;
	}

	int getAttack() {
		return attack;
	}

	void setAttack(int attack) {
		this.attack = attack;
	}

	int getRandomAttack() {
		return randomAttack;
	}
	
	void setRandomAttack() {
		this.randomAttack = (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
	}

	int getMinDamage() {
		return minDamage;
	}

	int getMaxDamage() {
		return maxDamage;
	}

	void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	abstract void unitInfo();

}
