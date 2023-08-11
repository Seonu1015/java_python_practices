package miniRPG;

import java.util.Scanner;

public class Battle {
	
	Scanner sc = new Scanner(System.in);

	void battle(UnitCharacter character, UnitMonster monster) {
		if (character.getAttack() < monster.getHealth()) {
			System.out.println(character.getName() + " 이(가) " + monster.getName() + "에게 " + character.getAttack()
					+ "만큼의 데미지를 주었습니다.");

			monster.setHealth(monster.getHealth() - character.getAttack());
			System.out.println(monster.getName() + "의 남은 체력 : " + monster.getHealth());

		} else if (character.getAttack() >= monster.getHealth()) {
			System.out.println(character.getName() + " 이(가) " + monster.getName() + " 에게 " + character.getAttack()
					+ "만큼의 데미지를 주었습니다.");
			System.out.println(character.getName() + " 이(가) " + monster.getName() + " 을(를) 쓰러뜨렸습니다.");

			monster.setHealth(monster.getHealth() - character.getAttack());
			character.accumulateExp();
		}
	}

	void repeatBattle(UnitCharacter character, UnitMonster monster) {
		System.out.println(character.getName() + "이(가) " + monster.getName() + "을(를) 만났습니다.");
		System.out.println("전투를 시작합니다.");

		System.out.println("-----------------------");
		for (int i = 0; i >= 0; i++) {
			if (monster.getHealth() <= 0) {
				break;
			}
			System.out.println("공격(a), 회복(h), 도망(e)");
			String selectAction = sc.next();
			if(selectAction.equals("a")) {
				this.battle(character, monster);
			} else if(selectAction.equals("h")) {
				//this.useHeal(character)
			} else {
				//this.escape
			}
			
		}
	}
	
	void repeatBattleboss(UnitCharacter character, UnitBoss boss) {
		System.out.println(character.getName() + "이(가) " + boss.getName() + "을(를) 만났습니다.");
		System.out.println("전투를 시작합니다.");
		
		System.out.println("-----------------------");
		for (int i = 0; i >= 0; i++) {
			if (boss.getHealth() <= 0) {
				break;
			}
			if(i%3 == 0) {
				//this.battleBossSkill(character, boss); <- 해당 메서드 만들어야 함
			}
			this.battle(character, boss);
		}
	}

}
