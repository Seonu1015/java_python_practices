package miniRPG;

import java.util.Scanner;

public class Battle {

	
	static void battle(UnitCharacter character, UnitMonster monster) {
		for(int i = 0 ; i<2; i++) {
			if(i==0) {
				if (character.getRandomAttack() < monster.getHealth()) {
	                System.out.println(character.getName() + " 이(가) " + monster.getName() + "에게 " + character.getRandomAttack()
	                        + "만큼의 데미지를 주었습니다.");

	                monster.setHealth(monster.getHealth() - character.getRandomAttack());
	                System.out.println(monster.getName() + "의 남은 체력 : " + monster.getHealth());
	            } else {
	                System.out.println(character.getName() + " 이(가) " + monster.getName() + " 에게 " + character.getRandomAttack()
	                        + "만큼의 데미지를 주었습니다.");
	                System.out.println(character.getName() + " 이(가) " + monster.getName() + " 을(를) 쓰러뜨렸습니다.");
	                monster.setHealth(monster.getHealth() - character.getRandomAttack());
	                character.accumulateExp();
	                monster.dropItem(ItemPotion.getInstance());
	                break;
	            }
			} else if(i==1) {
		        if (monster.getRandomAttack() < character.getHealth()) {
		            System.out.println(monster.getName() + " 이(가) " + character.getName() + "에게 " + monster.getRandomAttack()
		                    + "만큼의 데미지를 주었습니다.");

		            character.setHealth(character.getHealth() - monster.getRandomAttack());
		            System.out.println(character.getName() + "의 남은 체력 : " + character.getHealth() + " / " + character.getMaxHealth());
		        } else {
	                System.out.println(monster.getName() + " 이(가) " + character.getName() + " 에게 " + monster.getRandomAttack()
	                        + "만큼의 데미지를 주었습니다.");
	                System.out.println(monster.getName() + " 이(가) " + character.getName() + " 을(를) 쓰러뜨렸습니다.");
	                character.setHealth(character.getHealth() - monster.getRandomAttack());
	                break;
	            }
			}
		}
	}
	
	static void repeatBattle(UnitCharacter character, UnitMonster monster) {
	    Scanner sc = new Scanner(System.in);
	    character.unitInfo();

	    System.out.println("전투를 시작합니다.");
	    System.out.println("-----------------------------------------");

	    while (monster.getHealth() > 0 && character.getHealth() > 0) {
	        System.out.println("공격(a), 회복(h) 중에 선택하세요.");
	        String selectAction = sc.next();

	        if (selectAction.equals("a")) {
	            battle(character, monster);
	            if (character.getHealth() <= 0) {
	                System.out.println(character.getName() + "이(가) 전투에서 패배했습니다.");
	                break;
	            }
	        } else if (selectAction.equals("h")) {
	            ItemPotion.getInstance().itemInfo();
	            character.use();
	        }
	    }
	}
	
    static void bossBattle(UnitCharacter character, UnitBoss boss) {
        Scanner sc = new Scanner(System.in);

        character.unitInfo();

        System.out.println(boss.getName() + "와의 전투를 시작합니다.");
        System.out.println("-----------------------------------------");

        int bossSkillUse = 3;
        int bossSkillCount = 0;

        while (character.getHealth() > 0 && boss.getHealth() > 0) {
            System.out.println("공격(a), 회복(h) 중에 선택하세요.");
            String selectAction = sc.next();

            if (selectAction.equals("a")) {
                if (bossSkillCount < bossSkillUse) {
                    bossSkillCount++;
                    if (bossSkillCount == bossSkillUse) {
                        System.out.println(boss.getName() + "이(가) " + boss.getskill() + " 스킬을 시전합니다!");
                        System.out.println("주사위를 굴려 스킬 회피 여부를 결정합니다.");
                        int characterDice = (int) (Math.random()*6+1);
                        int bossDice = (int) (Math.random()*6+1);
                        System.out.println(character.getName() + "의 주사위 : " + characterDice + ", " + boss.getName() + "의 주사위 : " + bossDice);
                        if(characterDice>=bossDice) {
                        	System.out.println(character.getName() + "이(가) " + boss.getskill() + " 스킬을 회피했습니다!");
                        } else {
                        	System.out.println(boss.getName() + "의 " + boss.getskill() + " 스킬로 인해 " + boss.getSkillDamage() + "의 데미지를 받았습니다!");
                            character.setHealth(character.getHealth() - boss.getSkillDamage());
                        }
                    }
                }
                battle(character, boss);
            } else if (selectAction.equals("h")) {
                ItemPotion.getInstance().itemInfo();
                character.use();
            }
        }
        if (character.getHealth() <= 0) {
            System.out.println(character.getName() + "이(가) 전투에서 패배했습니다.");
        } else {            
            ItemWeapon droppedWeapon = new ItemWeapon(boss.getName() + "의 무기", "강력한 보스의 무기입니다.", boss.getMaxDamage()-10, boss.getMinDamage()-15);
            System.out.println("보스가 " + droppedWeapon.getName() + "을(를) 드랍했습니다!");
            character.equip(droppedWeapon);
        }
    }

}
