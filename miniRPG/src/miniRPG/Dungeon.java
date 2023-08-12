package miniRPG;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    private static ArrayList<UnitMonster> monsterPool;
    private static ArrayList<UnitBoss> bossPool;
    private static int currentFloor=1;
    private static UnitCharacter character;
    
    public Dungeon(UnitCharacter character) {
        this.character = character;
        this.monsterPool = generateMonsterPool();
        this.bossPool = generateBossPool();
        this.currentFloor = 1;
    }

    private ArrayList<UnitMonster> generateMonsterPool() { // 일반몹은 랜덤하게 나오도록
        ArrayList<UnitMonster> monsters = new ArrayList<>();
        monsters.add(new UnitMonster("슬라임", 70, 10, 5));
        monsters.add(new UnitMonster("광인", 100, 15, 5));
        monsters.add(new UnitMonster("거미", 70, 17, 7));
        monsters.add(new UnitMonster("구울", 90, 18, 8));
        monsters.add(new UnitMonster("광신도", 80, 21, 7));
        return monsters;
    }
    
    private ArrayList<UnitBoss> generateBossPool() { // 보스는 순서대로 나오게
        ArrayList<UnitBoss> boss = new ArrayList<>();
        boss.add(new UnitBoss("트리가드", 150, 20, 10, "벼락치기", 30));
        boss.add(new UnitBoss("방황하는 데몬", 200, 30, 15, "폭발찍기", 40));
        boss.add(new UnitBoss("종의 가고일", 250, 35, 20, "브레스", 45));
        return boss;
    }

    public static void startDungeon() {
        UnitCharacter character = UnitCharacter.getInstance();

        System.out.println("던전에 오신 것을 환영합니다!");
        
        character.getName();
        character.getBirth();
        
        Dungeon dungeon = new Dungeon(character);
        
        while (character.getHealth() > 0) {
            System.out.println(currentFloor + "층에 입장합니다.");
            boolean cleared = dungeon.battleFloor();
            
            if (cleared) {
                currentFloor++;
            } else {
                System.out.println(currentFloor + "층에서 패배하셨습니다.");
                currentFloor = 1;
            }
        }

        dungeon.checkBossBattle();
        System.out.println("게임 오버!");
    }

    private boolean battleFloor() {
        boolean cleared = true;
        int numMonsters = 1;
        for (int i = 0; i < numMonsters; i++) {
            UnitMonster monster = getRandomMonster();
            System.out.println(monster.getName() + " 이(가) 등장하였습니다.");
            Battle.repeatBattle(character, monster);

            if (monster.getHealth() > 0) {
                cleared = false;
                break;
            } else {
                System.out.println("-----------------------");
                System.out.println(currentFloor + "층을 클리어 하였습니다. 다음 층으로 이동합니다.");
            }
        }
        return cleared;
    }

    private UnitMonster getRandomMonster() {
        int randomIndex = (int) (Math.random() * monsterPool.size());
        return monsterPool.get(randomIndex);
    }
    
    private void checkBossBattle() {
        if (currentFloor % 4 == 0) {
            int bossIndex = (currentFloor / 4) - 1;
            if (bossIndex >= 0 && bossIndex < bossPool.size()) {
                UnitBoss boss = bossPool.get(bossIndex);
                System.out.println("보스와의 전투를 시작합니다!");
                Battle.bossBattle(character, boss);
            }
            currentFloor++;
        }
    }
    
    
}