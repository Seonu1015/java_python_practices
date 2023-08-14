package miniRPG;

import java.util.Scanner;

public class memo {
	
	int CF = 5;
	for (int j = 0; j < maxFloor / 4; j++) {
		for (int k = 0; k < 3; k++) {
			if (this.currentFloor == CF + k + (j * 4)) {
				return new UnitMonster(randomMonster.getName(), randomMonster.getHealth()*1.5, randomMonster.getMaxDamage()*1.3,
						randomMonster.getMinDamage()*1.3);
				break;
			} else {
				return new UnitMonster(randomMonster.getName(), randomMonster.getHealth(), randomMonster.getMaxDamage(),
						randomMonster.getMinDamage());
			}
		}
	}
}

