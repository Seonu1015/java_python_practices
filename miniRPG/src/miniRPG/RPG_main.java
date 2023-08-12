package miniRPG;

public class RPG_main {
	public static void main(String[] args) {

		UnitCharacter user = new UnitCharacter();
		user.unitInfo();
		
		UnitMonster mob1 = new UnitMonster("광인", 100, 20, 5);
		UnitMonster mob2 = new UnitMonster("거미", 70, 17, 7);
		UnitMonster mob3 = new UnitMonster("구울", 90, 24, 8);
		UnitMonster mob4 = new UnitMonster("광신도", 110, 27, 11);
		
		UnitBoss boss1 = new UnitBoss("트리가드", 200, 40, 30, "벼락치기", 15);
		
//		ItemWeapon weapon1 = new ItemWeapon();
//		weapon1.itemInfo();

		Battle.repeatBattle(user, mob1); // 해당 객체에서 메서드를 static으로 만들어버리면 클래스명 자체를 끌고와서 실행할 수 있다.
		
		
	}
}

//이모지 사이트 참고해서 넣으면 더 재밌어질지도??