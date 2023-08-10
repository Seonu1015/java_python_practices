package miniRPG;

import java.util.Scanner;

public class Unit {
	
	Scanner sc = new Scanner(System.in);
	
	private String name;
	private int health;
	private int attack;
	
	Unit() {
		
	}
	
	int getHealth() {
		return health;
	}
	
	String getName() {
		return name;
	}
	
	
}
