package miniRPG;

import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {

	private static ArrayList<UnitMonster> monsterPool;
	private static ArrayList<UnitBoss> bossPool;
	private static int currentFloor = 1;
	private static UnitCharacter character;

	private static final int maxFloor = 20;

	private ArrayList<UnitMonster> copyMonsters;

	public Dungeon(UnitCharacter character) {
		this.character = character;
		this.monsterPool = generateMonsterPool();
		this.bossPool = generateBossPool();
		this.currentFloor = 1;
		this.copyMonsters = new ArrayList<>();
	}
	
	int getCurrentFloor() {
		return currentFloor;
	}

	private ArrayList<UnitMonster> generateMonsterPool() { // 일반몹은 랜덤하게 나오도록
		ArrayList<UnitMonster> monsters = new ArrayList<>();
		monsters.add(new UnitMonster("슬라임", 70, 13, 9));
		monsters.add(new UnitMonster("광인", 90, 15, 8));
		monsters.add(new UnitMonster("거미", 70, 17, 6));
		monsters.add(new UnitMonster("구울", 90, 18, 6));
		monsters.add(new UnitMonster("광신도", 80, 21, 5));
		return monsters;
	}

	private ArrayList<UnitBoss> generateBossPool() { // 보스는 순서대로 나오게
		ArrayList<UnitBoss> boss = new ArrayList<>();
		boss.add(new UnitBoss("무명의 묘지기", 150, 30, 20, "낫 휘두르기", 40));
		boss.add(new UnitBoss("방황하는 데몬", 200, 40, 25, "폭발 찍기", 50));
		boss.add(new UnitBoss("종의 가고일", 250, 50, 30, "브레스", 60));
		boss.add(new UnitBoss("피의 군주", 300, 60, 40, "피의 해일", 70));
		boss.add(new UnitBoss("만월의 여왕", 400, 70, 50, "고고한 밤", 80));
		return boss;
	}

	public static void startDungeon() {
		Scanner sc = new Scanner(System.in);
		UnitCharacter character = new UnitCharacter();

		System.out.println("던전에 오신 것을 환영합니다!");

		character.getName();
		character.getBirth();

		Dungeon dungeon = new Dungeon(character);

		while (character.getHealth() > 0) {
			if (currentFloor > maxFloor) {
				System.out.println("던전의 최상층을 클리어하셨습니다!");
				break;
			}

			System.out.println(currentFloor + "층에 입장합니다.");
			boolean cleared = dungeon.battleFloor();

			if (!cleared) {
				System.out.println(currentFloor + "층에서 패배하셨습니다.");
				break;
			} else {
				currentFloor++;
			}
		}
		if (character.getHealth() <= 0) {
			System.out.println("게임 오버!");
			System.out.println("새로운 캐릭터로 다시 도전해보시겠습니까? y/n");
			String retry = sc.next();
			if(retry.equals("y")) {
				Dungeon.startDungeon();
			} else {
				System.out.println("게임을 종료합니다.");
			}
			
		} else {
			dungeon.checkBossBattle();
			System.out.println("던전 탐험이 완료되었습니다.");
		}
	}

	private boolean battleFloor() {
		this.copyMonsters = new ArrayList<>();
		boolean cleared = true;

		if (currentFloor % 4 == 0) {
			int bossIndex = (currentFloor / 4) - 1;
			if (bossIndex >= 0 && bossIndex < bossPool.size()) {
				UnitBoss boss = bossPool.get(bossIndex);
				System.out.println("보스와의 전투를 시작합니다!");
				Battle.bossBattle(character, boss);

				if (character.getHealth() <= 0) {
					cleared = false;
				}
			}
		} else {
			int numMonsters = 1;
			for (int i = 0; i < numMonsters; i++) {
				UnitMonster monster = getRandomMonster();
				System.out.println(monster.getName() + " 이(가) 등장하였습니다.");
				Battle.repeatBattle(character, monster);

				if (character.getHealth() <= 0) {
					cleared = false;
					break;
				} else if (monster.getHealth() > 0) {
					UnitMonster copiedMonster = new UnitMonster(monster.getName(), monster.getHealth(),
							monster.getMaxDamage(), monster.getMinDamage());
					copyMonsters.add(copiedMonster);
				}
			}
		}
		return cleared;
	}

	private UnitMonster getRandomMonster() {
		int randomIndex = (int) (Math.random() * monsterPool.size());
		UnitMonster randomMonster = monsterPool.get(randomIndex);
		return new UnitMonster(randomMonster.getName(), randomMonster.getHealth(), randomMonster.getMaxDamage(),
				randomMonster.getMinDamage());
	}

	private void checkBossBattle() {
		if (currentFloor > 0 && currentFloor % 4 == 0) {
			int bossIndex = (currentFloor / 4) - 1;
			if (bossIndex >= 0 && bossIndex < bossPool.size()) {
				UnitBoss boss = bossPool.get(bossIndex);
				System.out.println("보스와의 전투를 시작합니다!");
				Battle.bossBattle(character, boss);
			}
		}
	}
}