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
        monsters.add(new UnitMonster("슬라임", 70, 10, 5));
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
        
        while (character.getHealth() > 0) {
            System.out.println(currentFloor + "층에 입장합니다. 몬스터 " + (currentFloor) + "마리가 등장 합니다.");
            boolean cleared = dungeon.battleFloor();
            
            if (cleared) {
                currentFloor++;
            } else {
                System.out.println(currentFloor + "층에서 패배하셨습니다.");
                currentFloor = 1;
            }
        }
        System.out.println("게임 오버!");
    }

    private boolean battleFloor() {
        boolean cleared = true;
        int numMonsters = currentFloor;
        for (int i = 0; i < numMonsters; i++) {
            UnitMonster monster = getRandomMonster();
            System.out.println(monster.getName() + " 이(가) 등장하였습니다.");
            Battle.repeatBattle(character, monster);

            if (monster.getHealth() > 0) {
                cleared = false;
                break;
            }
        }
        System.out.println(currentFloor + "층을 클리어 하였습니다. 다음 층으로 이동합니다.");
        return cleared;
        
    }

    private UnitMonster getRandomMonster() {
        int randomIndex = (int) (Math.random() * monsterPool.size());
        return monsterPool.get(randomIndex);
    }
    
    // 보스 전투는 5층마다 진행 예정
}