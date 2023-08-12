package miniRPG;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    private static ArrayList<UnitMonster> monsterPool;
    private static int currentFloor=1;
    private static UnitCharacter character;
    
    public Dungeon(UnitCharacter character) {
        this.character = character;
        this.monsterPool = generateMonsterPool();
        this.currentFloor = 1;
    }

    private ArrayList<UnitMonster> generateMonsterPool() {
        ArrayList<UnitMonster> monsters = new ArrayList<>();
        monsters.add(new UnitMonster("슬라임", 50, 10, 5));
        monsters.add(new UnitMonster("광인", 100, 20, 5));
        monsters.add(new UnitMonster("거미", 70, 17, 7));
        monsters.add(new UnitMonster("구울", 90, 24, 8));
        monsters.add(new UnitMonster("광신도", 110, 27, 11));
        return monsters;
    }

    public static void startDungeon() {
        UnitCharacter character = UnitCharacter.getInstance();

        System.out.println("던전에 오신 것을 환영합니다!");
        
        character.getName();
        character.getBirth();
        
        Dungeon dungeon = new Dungeon(character);
        
        while (character.getHealth() > 0 && currentFloor <= dungeon.monsterPool.size()) {
            System.out.println(currentFloor + "층에 입장합니다.");
            boolean cleared = dungeon.battleMonstersOnFloor();
            
            if (cleared) {
                currentFloor++;
            } else {
                System.out.println(currentFloor + "층에서 패배하셨습니다.");
                currentFloor = 1;
            }
        }
        System.out.println("게임 오버!");
    }

    private static boolean battleMonstersOnFloor() {
        boolean cleared = true;
        for (UnitMonster monster : monsterPool) {
            System.out.println("현재 층 몬스터: " + monster.getName());
            Battle.repeatBattle(character, monster);

            if (monster.getHealth() > 0) {
                cleared = false;
                break;
            } else {
                System.out.println(monster.getName() + "를 처치했습니다.");
            }
        }
        return cleared;
    }

    private UnitMonster getRandomMonster() {
        int randomIndex = (int) (Math.random() * monsterPool.size());
        return monsterPool.get(randomIndex);
    }
    
    // 보스 전투는 5층마다 진행 예정
}