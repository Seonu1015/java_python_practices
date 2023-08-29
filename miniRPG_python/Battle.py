import random
from Unit import *
from Item import *
from Interface import *


class Battle:
    @staticmethod
    def battle(character, enemy):
        while character.is_alive() and enemy.is_alive:
            Battle.take_turn(character, enemy)
            if enemy.is_alive():
                Battle.take_turn(enemy, character)

        if not enemy.is_alive():
            Battle.victory(character, enemy)
            character.accumulate_exp()
            enemy.drop()

    @staticmethod
    def take_turn(attacker, defender):
        attack_damage = attacker.get_rand_attack()
        print(f"{attacker.get_name()} 이(가) {defender.get_name()}에게 {attack_damage}만큼의 데미지를 주었습니다.")
        defender.take_damage(attack_damage)
        print(f"{defender.get_name()}의 남은 체력: {defender.get_hp()} / {defender.get_max_hp()}")

    @staticmethod
    def victory(victor, loser):
        print(f"{victor.get_name()} 이(가) {loser.get_name()} 을(를) 쓰러뜨렸습니다.")

    @staticmethod
    def repeat_battle(character, enemy):
        character.unit_info()
        print("전투를 시작합니다.")
        Line.line_one()

        while enemy.is_alive() and character.is_alive():
            select_action = input("공격(a), 회복(h) 중에 선택하세요: ")

            if select_action == "a":
                Battle.battle(character, enemy)
                if character.get_hp() <= 0:
                    print(f"{character.get_name()}이(가) 전투에서 패배했습니다.")
                    break
            elif select_action == "h":
                Potion.get_instance().item_info()
                character.use()

    @staticmethod
    def boss_battle(character, boss):
        character.unit_info()
        print(f"{boss.get_name()}와의 전투를 시작합니다.")
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
                Potion.get_instance().item_info()
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
                Battle.die_unit(boss, character)

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


class Dungeon: # 일반 몬스터 10번 잡으면 보스몬스터 등장 -> 처치시 해당층 클리어
    max_floor = 100

    def __init__(self):
        self._current_floor = 1
    @classmethod
    def start_dungeon(self):
        character = Character()
        Line.line_two()
        print("던전에 오신 것을 환영합니다.")