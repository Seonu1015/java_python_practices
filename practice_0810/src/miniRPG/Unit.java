package miniRPG;

import java.util.Scanner;

public class Unit {
	
	Scanner sc = new Scanner(System.in);
	
	private String name;
	private int health;
	
	Unit() {
		
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
	
	void unitInfo() {
		System.out.println("-----------------------");
		System.out.println("┌ 유닛명 : " + name);
		System.out.println("└ 체력 : " + health);
	}
	
}
