package miniRPG;

import java.util.Scanner;

public class Battle_fix {

	static void battle(UnitCharacter character, UnitMonster monster) {
		boolean characterTurn = true;
		for(int i=0; i<2; i++) {
			if (characterTurn) {
	            if (monster.getAttack() < character.getHealth()) {
	                System.out.println(monster.getName() + " 이(가) " + character.getName() + "에게 " + monster.getAttack()
	                        + "만큼의 데미지를 주었습니다.");

	                character.setHealth(character.getHealth() - monster.getAttack());
	                System.out.println(character.getName() + "의 남은 체력 : " + character.getHealth());
	            } else {
	                System.out.println(monster.getName() + " 이(가) " + character.getName() + " 에게 " + monster.getAttack()
	                        + "만큼의 데미지를 주었습니다.");
	                System.out.println(monster.getName() + " 이(가) " + character.getName() + " 을(를) 쓰러뜨렸습니다.");

	                character.setHealth(character.getHealth() - monster.getAttack());
	            }
	        } else {
	            if (character.getAttack() < monster.getHealth()) {
	                System.out.println(character.getName() + " 이(가) " + monster.getName() + "에게 " + character.getAttack()
	                        + "만큼의 데미지를 주었습니다.");

	                monster.setHealth(monster.getHealth() - character.getAttack());
	                System.out.println(monster.getName() + "의 남은 체력 : " + monster.getHealth());
	            } else {
	                System.out.println(character.getName() + " 이(가) " + monster.getName() + " 에게 " + character.getAttack()
	                        + "만큼의 데미지를 주었습니다.");
	                System.out.println(character.getName() + " 이(가) " + monster.getName() + " 을(를) 쓰러뜨렸습니다.");

	                monster.setHealth(monster.getHealth() - character.getAttack());
	                character.accumulateExp();
	                monster.dropItem(ItemPotion.getInstance());
	            }
	        }

	        characterTurn = !characterTurn;
	    }
	}

    static void repeatBattle(UnitCharacter character, UnitMonster monster) {
        Scanner sc = new Scanner(System.in);

        System.out.println(character.getName() + "이(가) " + monster.getName() + "을(를) 만났습니다.");
        System.out.println("전투를 시작합니다.");

        System.out.println("-----------------------");
        while (monster.getHealth() > 0 && character.getHealth() > 0) {
            System.out.println("공격(a), 회복(h) 중에 선택하세요.");
            String selectAction = sc.next();
            if (selectAction.equals("a")) {
                battle(character, monster);
            } else if (selectAction.equals("h")) {
            	character.usePotion();
            }
            if (monster.getHealth() <= 0) {
                System.out.println(monster.getName() + "을(를) 격파했습니다!");
                break;
            } else {
                System.out.println(character.getName() + "이(가) 전투에서 패배했습니다.");
                break;
            }
        }

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
