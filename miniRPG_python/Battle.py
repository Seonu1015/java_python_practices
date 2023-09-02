import random
from Unit import *
from Item import *
from Interface import *


class Battle:
    @staticmethod
    def battle(character, enemy):
        for i in range(2):
            if i == 0:
                Battle.character_turn(character, enemy)
                if enemy.get_hp() <= 0:
                    Battle.victory(character, enemy)
                    character.accumulate_exp()
                    enemy.drop()
                    break
            elif i == 1:
                Battle.monster_turn(enemy, character)
                if character.get_hp() <= 0:
                    print(f"{character.get_name()}이(가) 전투에서 패배했습니다.")
                    break

    @staticmethod
    def take_turn(character, enemy):
        character.set_rand_attack()
        attack_damage = character.get_rand_attack()
        print(f"{character.get_name()} 이(가) {enemy.get_name()}에게 {attack_damage}만큼의 데미지를 주었습니다.")
        enemy.take_damage(attack_damage)
        print(f"{enemy.get_name()}의 남은 체력: {enemy.get_hp()} / {enemy.get_max_hp()}")

    @staticmethod
    def monster_turn(enemy, character):
        enemy.set_rand_attack()
        attack_damage = enemy.get_rand_attack()
        print(f"{enemy.get_name()} 이(가) {character.get_name()}에게 {attack_damage}만큼의 데미지를 주었습니다.")
        character.take_damage(attack_damage)
        print(f"{character.get_name()}의 남은 체력: {character.get_hp()} / {character.get_max_hp()}")

        if character.get_poisoned():
            character.apply_poison(character)

    @staticmethod
    def victory(victor, loser):
        Line.line_two()
        print(f"{victor.get_name()} 이(가) {loser.get_name()} 을(를) 쓰러뜨렸습니다.")

    @staticmethod
    def repeat_normal_battle(character, enemy):
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
                try:
                    character.use_potion()
                except ValueError:
                    print("ERROR: 잘못된 입력입니다. a 또는 h를 입력하세요.")
                except Exception as e:
                    print("ERROR: 예외가 발생했습니다:", e)
            else:
                print("ERROR: a 또는 h를 입력하세요.")

            Line.line_one()

    @staticmethod
    def repeat_boss_battle(character, boss):
        character.unit_info()
        print(f"{boss.get_name()}와의 전투를 시작합니다.")
        boss.unit_info()
        Line.line_two()

        boss_skill_use = 3
        boss_skill_count = 0

        while character.is_alive() and boss.is_alive():
            try:
                select_action = input("공격(a), 회복(h) 중에 선택하세요: ")

                if select_action == "a":
                    if boss_skill_count < boss_skill_use:
                        boss_skill_count += 1
                        if boss_skill_count == 2:
                            print("곧 보스가 스킬을 시전합니다. 대비하세요!!")
                        elif boss_skill_count == boss_skill_use:
                            Battle.use_boss_skill(character, boss)
                            boss_skill_count = 0
                    Battle.battle(character, boss)
                elif select_action == "h":
                    character.use_potion()
                else:
                    print("ERROR: a 또는 h를 입력하세요.")
            except Exception as e:
                print("ERROR: 예외가 발생했습니다:", e)
            Line.line_one()

        if not boss.is_alive():
            Boss.defeat_boss(boss, character)

    @staticmethod
    def use_boss_skill(character, boss):
        Line.line_one()
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
        Line.line_one()


class Dungeon:
    max_floor = 10
    current_floor = 1
    bosses = []

    @classmethod
    def start_dungeon(cls):
        Monster.read_csv_file()
        dungeon_instance = Dungeon()
        character = Character()
        Line.line_two()
        print("던전에 오신 것을 환영합니다.")
        Line.line_two()

        while character.is_alive():
            if dungeon_instance.current_floor > cls.max_floor:
                print("던전의 최상층을 클리어하셨습니다!")
                break
            dungeon_instance.battle_floor(character)

        if not character.is_alive():
            dungeon_instance.restart_dungeon()

    @classmethod
    def restart_dungeon(cls):
        print("YOU DIED!")
        retry = str(input("새로운 캐릭터로 다시 시작하시겠습니까? (y/n) : "))
        if retry == 'y':
            Dungeon.start_dungeon()
        else:
            print("게임을 종료합니다.")

    def battle_floor(self, character):
        self.battle_normal_monsters(character)

        if character.is_alive() and self.current_floor <= Dungeon.max_floor:
            print("해당 층의 모든 몬스터를 처치했습니다.")
            print("보스가 등장합니다.")
            self.battle_boss_monster(character)
            if character.is_alive():
                Monster.upgrade_monster_lst()
                self.current_floor += 1
                if self.current_floor <= Dungeon.max_floor:
                    print(f"{self.current_floor + 1} 층으로 이동합니다.")

    @staticmethod
    def battle_normal_monsters(character):
        for i in range(10):
            encounter_monster = Monster.random_monster()
            print(f"해당 층의 남은 몬스터 : {10 - i} / {10}")
            Battle.repeat_normal_battle(character, encounter_monster)

            if not character.is_alive():
                print("캐릭터가 사망하여 던전을 클리어하지 못했습니다.")
                break

    @staticmethod
    def battle_boss_monster(character):
        bosses = Boss.planned_boss()

        for i in range(len(bosses)):
            if i == Dungeon.current_floor - 1:
                Battle.repeat_boss_battle(character, bosses[i])

                if not character.is_alive():
                    print(f"보스 {bosses[i].get_name()}와(과)의 전투에서 캐릭터가 사망하였습니다.")
                    break
