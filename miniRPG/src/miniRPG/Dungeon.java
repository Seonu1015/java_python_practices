package miniRPG;

import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {

    private ArrayList<UnitMonster> monsters;
    private int currentFloor;
    private UnitCharacter character;
    
    private static final String[] monsterNames = { "구울", "거미", "광인", "광신도" };
    
    private static final int maxDungeonFloor = 100;

    public Dungeon(UnitCharacter character) {
        this.character = character;
        monsters = new ArrayList<>();
        currentFloor = 1;
        generateMonsters();
    }

    private void generateMonsters() {
        int numMonsters = currentFloor * 2;
        for (int i = 0; i < numMonsters; i++) {
            String monsterName = "몬스터 " + (i + 1);
            int monsterHealth = 100;
            int monsterMaxDamage = 20;
            int monsterMinDamage = 5;

            UnitMonster monster = UnitMonster.createMonster(monsterName, monsterHealth, monsterMaxDamage, monsterMinDamage);
            addMonster(monster);
        }
    }
    
    private String getRandomMonsterName() {
        int randomIndex = (int) (Math.random() * monsterNames.length);
        return monsterNames[randomIndex];
    }

    private void addMonster(UnitMonster monster) {
        monsters.add(monster);
    }

    public void startDungeon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("던전의 " + currentFloor + "층에 오신 것을 환영합니다.");

        for (UnitMonster monster : monsters) {
            Battle.repeatBattle(character, monster);

            if (character.getHealth() <= 0) {
                System.out.println("게임 오버");
                character = new UnitCharacter();
                System.out.println("새로운 캐릭터 " + character.getName() + "로 다시 시작합니다.");
                return; // 게임 오버 시, 더이상 던전을 진행하지 않고 종료
            }

            character.unitInfo();

            if (currentFloor > maxDungeonFloor) {
                System.out.println("모든 던전 클리어!");
                return;
            }
        }
        currentFloor++; // 몬스터들을 다 처리한 후에 층을 올림
        generateMonsters(); // 다음 층의 몬스터들을 생성
    }
    
}
