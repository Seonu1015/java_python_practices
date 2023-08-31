import random
from Unit import *
from Item import *
from Interface import *


class Battle:
    @staticmethod
    def battle(character, enemy):
        for i in range(2):
            if i == 0:
                Battle.take_turn(character, enemy)
                if enemy.get_hp() <= 0:
                    Battle.victory(character, enemy)
                    character.accumulate_exp()
                    enemy.drop()
                    break
            elif i == 1:
                Battle.take_turn(enemy, character)
                if character.get_hp() <= 0:
                    print(f"{character.get_name()}이(가) 전투에서 패배했습니다.")
                    break

    @staticmethod
    def take_turn(attacker, defender):
        attacker.set_rand_attack()  # 무작위 공격력 결정
        attack_damage = attacker.get_rand_attack()  # 무작위 공격력 얻음
        print(f"{attacker.get_name()} 이(가) {defender.get_name()}에게 {attack_damage}만큼의 데미지를 주었습니다.")
        defender.take_damage(attack_damage)
        print(f"{defender.get_name()}의 남은 체력: {defender.get_hp()} / {defender.get_max_hp()}")

    @staticmethod
    def victory(victor, loser):
        print(f"{victor.get_name()} 이(가) {loser.get_name()} 을(를) 쓰러뜨렸습니다.")

    @staticmethod
    def repeat_battle(character, enemy):
        character.unit_info()
        print(f"{enemy.get_name()}을(를) 마주쳤습니다. 전투를 시작합니다.")
        enemy.unit_info()
        Line.line_two()

        while enemy.is_alive() and character.is_alive():
            select_action = input("공격(a), 회복(h) 중에 선택하세요: ")

            if select_action == "a":
                Battle.battle(character, enemy)
                if character.get_hp() <= 0:
                    print(f"{character.get_name()}이(가) 전투에서 패배했습니다.")
                    break
            elif select_action == "h":
                character.use()
            Line.line_one()

    @staticmethod
    def boss_battle(character, boss):
        character.unit_info()
        print(f"{boss.get_name()}와의 전투를 시작합니다.")
        boss.unit_info()
        Line.line_one()

        boss_skill_use = 3
        boss_skill_count = 0

        while character.is_alive() and boss.is_alive():
            select_action = input("공격(a), 회복(h) 중에 선택하세요: ")

            if select_action == "a":
                if boss_skill_count < boss_skill_use:
                    boss_skill_count += 1
                    if boss_skill_count == boss_skill_use:
                        Battle.use_boss_skill(character, boss)
                Battle.battle(character, boss)
            elif select_action == "h":
                character.use()

        if not boss.is_alive():
            Battle.defeat_boss(character, boss)

    @staticmethod
    def use_boss_skill(character, boss):
        print(f"{boss.get_name()}이(가) {boss.get_skill()} 스킬을 시전합니다!")
        print("주사위를 굴려 스킬 회피 여부를 결정합니다.")
        character_dice = random.randint(1, 6)
        boss_dice = random.randint(1, 6)
        print(f"{character.get_name()}의 주사위: {character_dice}, {boss.get_name()}의 주사위: {boss_dice}")
        if character_dice >= boss_dice:
            print(f"{character.get_name()}이(가) {boss.get_skill()} 스킬을 회피했습니다!")
        else:
            damage = boss.get_skill_damage()
            print(f"{boss.get_name()}의 {boss.get_skill()} 스킬로 인해 {damage}의 데미지를 받았습니다!")
            character.take_damage(damage)
            if character.get_hp() <= 0:
                Battle.victory(boss, character)

    @staticmethod
    def defeat_boss(character, boss):
        dropped_weapon = Weapon(
            f"{boss.get_name()}의 무기",
            "강력한 보스의 무기입니다.",
            boss.get_max_damage() - 10,
            boss.get_min_damage() - 15,
        )
        print(f"보스가 {dropped_weapon.get_name()}을(를) 드랍했습니다!")
        character.equip(dropped_weapon)


class Dungeon:  # 일반 몬스터 10번 잡으면 보스몬스터 등장 -> 처치시 해당층 클리어
    max_floor = 10
    current_floor = 1

    def __init__(self):
        self._bosses = []

    @classmethod
    def start_dungeon(cls):
        Dungeon_instance = cls()
        character = Character()
        Line.line_two()
        print("던전에 오신 것을 환영합니다.")
        Line.line_two()

        while character.is_alive():
            if Dungeon_instance.current_floor > cls.max_floor:
                print("던전의 최상층을 클리어하셨습니다!")
                break
            Dungeon_instance.battle_floor(character)

        if not character.is_alive():
            Dungeon_instance.restart_dungeon()

    @classmethod
    def restart_dungeon(cls):
        print("YOU DIED!")
        retry = str(input("새로운 캐릭터로 다시 시작하시겠습니까? (y/n) : "))
        if retry == 'y':
            Dungeon.start_dungeon()
        else:
            print("게임을 종료합니다.")

    def battle_floor(self, character):
        cleared = self.battle_normal_monsters(character)

        if cleared and self.current_floor <= Dungeon.max_floor:
            cleared = self.battle_boss_monster()

        if cleared:
            self.current_floor += 1
            if self.current_floor <= Dungeon.max_floor:
                print("다음 층으로 이동합니다.")

    @staticmethod
    def battle_normal_monsters(character):
        cleared = True

        for i in range(10):
            encounter_monster = Monster.random_monster()
            Battle.repeat_battle(character, encounter_monster)

            if not character.is_alive():
                print("캐릭터가 사망하여 던전을 클리어하지 못했습니다.")
                cleared = False
                break

        return cleared

    def load_boss(self):
        self._bosses = Boss.planned_boss()

    def battle_boss_monster(self):
        if 0 < self.current_floor <= len(self._bosses):
            boss_monster = self._bosses[self.current_floor - 1]  # 층-1 인덱스로 보스 불러오기
            character = Character()
            Battle.repeat_battle(character, boss_monster)

            if not character.is_alive():
                print("보스 몬스터와의 전투에서 캐릭터가 사망하였습니다.")
                return False
            else:
                print(f"보스 몬스터를 처치하여 {self.current_floor}층을 클리어하셨습니다!")
                return True
