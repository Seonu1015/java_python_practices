package miniRPG;

public class battle2 {

	void battle(UnitCharacter character, UnitMonster monster) {
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				this.battleUnit(character, monster);
				if (character.getRandomAttack() >= monster.getHealth()) {
					character.accumulateExp();
					monster.dropItem(ItemPotion.getInstance());
					break;
				}
			} else if (i == 1) {
				this.battleUnit(monster, character);
				if (monster.getRandomAttack() >= character.getHealth()) {
					System.out.println(monster.getName() + " 이(가) " + character.getName() + " 을(를) 쓰러뜨렸습니다.");
					break;
				}
			}
		}
	}

	void battleUnit(Unit unit1, Unit unit2) {
		System.out.println(
				unit1.getName() + " 이(가) " + unit2.getName() + "에게 " + unit1.getRandomAttack() + "만큼의 데미지를 주었습니다.");
		unit2.setHealth(unit2.getHealth() - unit1.getRandomAttack());
		System.out.println(unit2.getName() + "의 남은 체력 : " + unit2.getHealth() + " / " + unit2.getMaxHealth());
	}

}
