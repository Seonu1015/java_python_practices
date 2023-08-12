package miniRPG;

import java.util.Scanner;

public class Battle {

	
	static void battle(UnitCharacter character, UnitMonster monster) {
		for(int i = 0 ; i<2; i++) {
			if(i==0) {
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
			} else if(i==1) {
	            if (monster.getAttack() < character.getHealth()) {
	                System.out.println(monster.getName() + " 이(가) " + character.getName() + "에게 " + monster.getAttack()
	                        + "만큼의 데미지를 주었습니다.");

	                character.setHealth(character.getHealth() - monster.getAttack());
	                System.out.println(character.getName() + "의 남은 체력 : " + character.getHealth() + " / " + character.getMaxHealth());
	            } else {
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
        
        character.unitInfo();

        System.out.println("전투를 시작합니다.");
        System.out.println("-----------------------");

        for(int i=0;i>=0; i++) {
        	if(monster.getHealth() > 0) {
        		System.out.println("공격(a), 회복(h) 중에 선택하세요.");
                String selectAction = sc.next();
                if (selectAction.equals("a")) {
                    battle(character, monster);
                } else if (selectAction.equals("h")) {
                	ItemPotion.getInstance().itemInfo();
                	character.use();
                }
        	} else if (monster.getHealth()<=0) {
                break;
        	} else {
                System.out.println(character.getName() + "이(가) 전투에서 패배했습니다.");
                break;
            }
        }
	}
	
	// 보스 전투 부분 추가 필요 <- 보스와의 전투시 회피에 성공하면 한단계 아래층으로 이동하게끔
}
