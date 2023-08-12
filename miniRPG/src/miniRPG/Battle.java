package miniRPG;

import java.util.Scanner;

public class Battle {

	static void battle(UnitCharacter character, UnitMonster monster) {
		// 여기서 나오는 몬스터는 몬스터리스트에서 랜덤으로 나오게 해볼까?
		for (int i = 0; i >= 0; i++) {
			if (i % 2 == 0) {
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
			} else {
				if (monster.getAttack() < character.getHealth()) {
					System.out.println(monster.getName() + " 이(가) " + character.getName() + "에게 " + monster.getAttack()
							+ "만큼의 데미지를 주었습니다.");

					character.setHealth(character.getHealth() - monster.getAttack());
					System.out.println(character.getName() + "의 남은 체력 : " + character.getHealth());

				} else if (monster.getAttack() >= character.getHealth()) {
					System.out.println(monster.getName() + " 이(가) " + character.getName() + " 에게 " + monster.getAttack()
							+ "만큼의 데미지를 주었습니다.");
					System.out.println(monster.getName() + " 이(가) " + character.getName() + " 을(를) 쓰러뜨렸습니다.");

					character.setHealth(character.getHealth() - monster.getAttack());
				}
			}
		}
	}

	static void repeatBattle(UnitCharacter character, UnitMonster monster) {
		Scanner sc = new Scanner(System.in);

		System.out.println(character.getName() + "이(가) " + monster.getName() + "을(를) 만났습니다.");
		System.out.println("전투를 시작합니다.");

		System.out.println("-----------------------");
		for (int i = 0; i >= 0; i++) {
			if (monster.getHealth() <= 0) {
				break;
			}
			System.out.println("공격(a), 회복(h), 도망(e) 중에 선택하세요.");
			String selectAction = sc.next();
			if (selectAction.equals("e")) {
				int characterDice = (int) (Math.random() * 6 + 1);
				int monsterDice = (int) (Math.random() * 6 + 1);
				System.out.println(character.getName() + "의 주사위 : " + characterDice);
				System.out.println(monster.getName() + "의 주사위 : " + monsterDice);
				if (characterDice >= monsterDice) {
					System.out.println("도망에 성공하였습니다.");
					break;
				} else {
					System.out.println("도망에 실패하였습니다.");
				}
			} else if (selectAction.equals("a")) {
				battle(character, monster);
			} else if (selectAction.equals("h")) {
				// this.useHeal(character)
			}
		}
	}

	void clear() {

	}

	static void repeatBattleboss(UnitCharacter character, UnitBoss boss) {
		System.out.println(character.getName() + "이(가) " + boss.getName() + "을(를) 만났습니다.");
		System.out.println("전투를 시작합니다.");

		System.out.println("-----------------------");
		for (int i = 0; i >= 0; i++) {
			if (boss.getHealth() <= 0) {
				break;
			}
			if (i % 5 == 0) {
				// this.battleBossSkill(character, boss); <- 해당 메서드 만들어야 함
			}
			battle(character, boss);
		}
	}

}
