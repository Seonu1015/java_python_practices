package miniRPG;

import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon_fix {

    private ArrayList<UnitMonster> monsters;
    private int currentFloor;
    private UnitCharacter character;
    
    private static final MonsterInfo[] monsterInfos = {
            new MonsterInfo("구울", 90, 24, 8),
            new MonsterInfo("거미", 70, 17, 7),
            new MonsterInfo("광인", 100, 20, 5),
            new MonsterInfo("광신도", 110, 27, 11)
        };
    
    private static final int maxDungeonFloor = 100;

    public Dungeon_fix(UnitCharacter character) {
        this.character = character;
        monsters = new ArrayList<>();
        currentFloor = 1;
        generateMonsters();
    }

    private void generateMonsters() {
        int numMonsters = currentFloor * 2;
        for (int i = 0; i < numMonsters; i++) {
            UnitMonster monster = getRandomMonster();
            monsters.add(monster);
        }
    }

    private UnitMonster getRandomMonster() {
        int randomIndex = (int) (Math.random() * monsterInfos.length);
        MonsterInfo randomMonsterInfo = monsterInfos[randomIndex];
        return UnitMonster.createMonster(
            randomMonsterInfo.getName(),
            randomMonsterInfo.getHealth(),
            randomMonsterInfo.getMaxDamage(),
            randomMonsterInfo.getMinDamage()
        );
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
