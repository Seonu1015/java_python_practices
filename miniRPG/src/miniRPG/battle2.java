package miniRPG;

import java.util.Scanner;

public class battle2 {

//	static void repeatBattle(UnitCharacter character, UnitMonster monster) {
//		Scanner sc = new Scanner(System.in);
//		character.unitInfo();
//		System.out.println("전투를 시작합니다.");
//		System.out.println("-----------------------------------------");
//
//		while (character.getHealth() > 0 && monster.getHealth() > 0) {
//			System.out.println("공격(a), 회복(h) 중에 선택하세요.");
//			String selectAction = sc.next();
//
//			if (selectAction.equals("a")) {
//				castBossSkill(character, monster);
//				battle(character, monster);
//			} else if (selectAction.equals("h")) {
//				ItemPotion.getInstance().itemInfo();
//				character.use();
//			}
//		}
//		if (character.getHealth() <= 0) {
//			System.out.println(character.getName() + "이(가) 전투에서 패배했습니다.");
//		} else {
//			ItemWeapon droppedWeapon = new ItemWeapon(boss.getName() + "의 무기", "강력한 보스의 무기입니다.",
//					boss.getMaxDamage() - 10, boss.getMinDamage() - 15);
//			System.out.println("보스가 " + droppedWeapon.getName() + "을(를) 드랍했습니다!");
//			character.equip(droppedWeapon);
//		}
//	}
//	
//	static void castBossSkill(UnitCharacter character, UnitMonster monster) {
//		int bossSkillUse = 3;
//		int bossSkillCount = 0;
//		
//		UnitBoss boss = (UnitBoss) monster;
//		
//		if (bossSkillCount < bossSkillUse) {
//			bossSkillCount++;
//			if (bossSkillCount == bossSkillUse) {
//				System.out.println(boss.getName() + "이(가) " + boss.getskill() + " 스킬을 시전합니다!");
//				System.out.println("주사위를 굴려 스킬 회피 여부를 결정합니다.");
//				int characterDice = (int) (Math.random() * 6 + 1);
//				int bossDice = (int) (Math.random() * 6 + 1);
//				System.out.println(character.getName() + "의 주사위 : " + characterDice + ", " + boss.getName()
//						+ "의 주사위 : " + bossDice);
//				if (characterDice >= bossDice) {
//					System.out.println(character.getName() + "이(가) " + boss.getskill() + " 스킬을 회피했습니다!");
//				} else {
//					System.out.println(boss.getName() + "의 " + boss.getskill() + " 스킬로 인해 "
//							+ boss.getSkillDamage() + "의 데미지를 받았습니다!");
//					character.setHealth(character.getHealth() - boss.getSkillDamage());
//					if (character.getHealth() <= 0) {
//						System.out.println(boss.getName() + " 이(가) " + character.getName() + " 을(를) 쓰러뜨렸습니다.");
//					}
//				}
//			}
//		}
//	}

}
