import csv
from Item import *
from abc import ABC, abstractmethod


class Unit(ABC):
    def __init__(self, name, hp, min_damage, max_damage):
        self._name = name
        self._hp = hp
        self._max_hp = hp
        self._attack = 0
        self._rand_attack = 0
        self._max_damage = max_damage
        self._min_damage = min_damage

    def get_name(self):
        return self._name

    def get_hp(self):
        return max(0, self._hp)

    def set_hp(self, hp):
        self._hp = hp

    def get_max_hp(self):
        return self._max_hp

    def set_max_hp(self, max_hp):
        self._max_hp = max_hp

    def get_attack(self):
        return self._attack

    def set_attack(self, attack):
        self._attack = attack

    def get_rand_attack(self):
        return self._rand_attack

    def set_rand_attack(self):
        self._rand_attack = random.randint(self._min_damage, self._max_damage + 1)

    def get_min_damage(self):
        return self._min_damage

    def set_min_damage(self, min_damage):
        self._min_damage = min_damage

    def get_max_damage(self):
        return self._max_damage

    def set_max_damage(self, max_damage):
        self._max_damage = max_damage

    def is_alive(self):
        return self._hp > 0

    def take_damage(self, damage):
        self._hp = max(0, self._hp - damage)

    @abstractmethod
    def unit_info(self):
        pass


class Character(Unit, EquipableItem):
    def __init__(self):
        super().__init__("", 0, 0, 0)
        self._birth = ""
        self._level = 1
        self._exp = 0
        self._equipped_weapon = None
        self._base_attack = 0
        self.set_name()
        self.set_birth()
        default_weapon = Weapon()
        self.equip(default_weapon)
        self._max_exp = 100
        self._poisoned = False
        self._poison_turn = 0

    def set_name(self):
        Line.line_one()
        input_name = str(input("캐릭터명을 지어주세요. : "))
        double_check = str(input(f"{input_name}으로 진행하시겠습니까? (y/n) : "))
        if double_check == 'y':
            self._name = input_name
            Line.line_one()
        else:
            self.set_name()

    def get_level(self):
        return self._level

    def get_birth(self):
        return self._birth

    def set_birth(self):
        print("태생을 선택하세요. 선택한 태생에 따라 기본 스탯이 달라집니다.")
        print("퇴역군인 | 도굴꾼 | 망국의왕족 | 역병의사 | 못가진자")

        while True:
            select_birth = input()
            Line.line_one()

            births = ["퇴역군인", "도굴꾼", "망국의왕족", "역병의사", "못가진자"]

            if select_birth in births:
                break
            else:
                print("ERROR : 유효하지 않은 태생입니다. 다시 선택하세요.")

        if select_birth == "퇴역군인":
            self.set_hp(130)
            self.set_attack(27)
        elif select_birth == "도굴꾼":
            self.set_hp(120)
            self.set_attack(30)
        elif select_birth == "망국의왕족":
            self.set_hp(80)
            self.set_attack(32)
        elif select_birth == "역병의사":
            self.set_hp(90)
            self.set_attack(25)
        elif select_birth == "못가진자":
            self.set_hp(100)
            self.set_attack(10)
        self.set_max_hp(self.get_hp())
        self._birth = select_birth

    def unit_info(self):
        Line.line_star()
        print(f"┌ 캐릭터명 : {self.get_name()} ({self.get_birth()})")
        print(f"│ 레벨 : {self.get_level()}")
        print(f"│ 체력 : {str(self.get_hp())} / {str(self.get_max_hp())}")
        if self._equipped_weapon:
            print(f"│ 장착무기 : {self._equipped_weapon.get_name()}")
            print(f"└ 공격력 : {str(self.get_min_damage())} ~ {str(self.get_max_damage())}")
        else:
            print("└ 장착무기 : 없음")
        Line.line_star()

    def set_exp(self):
        exp = round((random.random() * 70 + 40) * 100.00) / 100.00
        self._exp = exp
        print(f"{str(exp)}의 경험치를 획득하였습니다.")
        print(f"현재 누적 경험치 : {self._exp} / {self._max_exp}")
        return self._exp

    def full_exp(self):
        self._max_exp = 100 + ((self._level - 1) * 50)
        return self._max_exp

    def accumulate_exp(self):
        self._exp += self.set_exp()
        self.full_exp()
        if self._exp >= self._max_exp:
            Line.line_two()
            print(f"★ {self.get_name()} LEVEL UP ★")
            print(f"{self.get_name()}의 공격력이 상승합니다. (+3)")
            print(f"{self.get_name()}의 최대 체력이 상승합니다. (+20)")
            print(f"{self.get_name()}의 체력이 모두 회복됩니다.")
            Line.line_two()
            self._level += 1
            self._exp -= self._max_exp
            self.set_min_damage(self.get_min_damage() + 3)
            self.set_max_damage(self.get_max_damage() + 3)
            self.set_max_hp(self.get_max_hp() + 20)
            self.set_hp(self.get_max_hp())

    def equip(self, weapon):
        if not self._equipped_weapon is None:
            self.un_equip()

        self._base_attack = self.get_attack()

        self.set_min_damage(self._base_attack + weapon.get_min_damage())
        self.set_max_damage(self._base_attack + weapon.get_max_damage())

        self._equipped_weapon = weapon
        if self._equipped_weapon.get_name() == "누군가 쓰다버린 검":
            return
        else:
            print(f"{self._equipped_weapon.get_name()}을(를) 장착했습니다.")
        self.new_random_damage()

    def new_random_damage(self):
        super().set_rand_attack()
        self.set_attack(super().get_rand_attack)

    def un_equip(self):
        if not self._equipped_weapon is None:
            print(f"{self._equipped_weapon.get_name()}을(를) 해제했습니다.")
            self._equipped_weapon = None
        else:
            print("장착한 무기가 없습니다.")

    def use_potion(self):
        if self.get_hp() >= self.get_max_hp():
            print("이미 최대 체력입니다.")
            return

        while True:
            try:
                Line.line_one()
                print("사용할 회복 물약을 선택하세요.")
                print("1. 일반 포션")
                print("2. 특별 포션")
                choice = int(input("선택 : "))

                if choice == 1:
                    potion = RegularPotion()
                    potion.item_info()
                    sel = str(input("일반 포션을 사용하시겠습니까? y/n : "))
                    if sel == 'y':
                        potion.use(self)
                        print("현재 수량 : " + str(potion.get_quantity()))
                    else:
                        Character.use_potion(self)
                    break
                elif choice == 2:
                    potion = SpecialPotion()
                    potion.item_info()
                    sel = str(input("특별 포션을 사용하시겠습니까? y/n : "))
                    if sel == 'y':
                        potion.use(self)
                        print("현재 수량 : " + str(potion.get_quantity()))
                    else:
                        Character.use_potion(self)
                    break
                else:
                    print("ERROR : 입력이 잘못되었습니다. 다시 입력해주세요.")
            except ValueError:
                print("ERROR : 올바른 숫자를 입력하세요.")

    def get_poisoned(self):
        return self._poisoned

    def set_poisoned(self, poisoned):
        self._poisoned = poisoned

    def apply_poison(self, character):
        if self.get_poisoned():
            character.take_damage(10)
            print(f"{character.get_name()}이(가) 중독상태로 체력이 10만큼 줄었습니다. 현재 체력: {character.get_hp()}")
            self._poison_turn -= 1
            if self._poison_turn == 0:
                print(f"{character.get_name()}이(가) 중독상태에서 벗어났습니다.")
                self._poisoned = False


class Monster(Unit, DropItem):
    monsters = []

    def __init__(self, name, hp, min_damage, max_damage):
        super().__init__(name, hp, min_damage, max_damage)

    def unit_info(self):
        Line.line_star()
        print(f"┌ 몬스터명 : {self.get_name()}")
        print(f"│ 체력 :  : {self.get_hp()} / {self.get_max_hp()}")
        print(f"└ 공격력 :  : {self.get_min_damage()} ~ {self.get_max_damage()}")
        Line.line_star()

    def drop(self):
        amount = random.randint(1, 3)
        special_drop_rate = 0.3
        is_special_drop = random.random() <= special_drop_rate

        if is_special_drop:
            potion = SpecialPotion()
            potion.increase_quantity(1)
            print(f"{self.get_name()}이(가) 특별 포션 1개를 드랍했습니다.")
        else:
            potion = RegularPotion()
            potion.increase_quantity(amount)
            print(f"{self.get_name()}이(가) 일반 포션 {amount}개를 드랍했습니다.")

    @classmethod
    def read_csv_file(cls):

        try:
            with open("unit_monster.csv", mode='r', encoding='utf-8') as f:
                reader = csv.reader(f)
                next(reader)

                for row in reader:
                    name, hp, min_attack, max_attack = row
                    mob = cls(name, int(hp), int(min_attack), int(max_attack))
                    cls.monsters.append(mob)

        except FileNotFoundError:
            print("파일을 찾을 수 없습니다.")
        except UnicodeDecodeError:
            print("파일 읽기 오류")

        return cls.monsters

    @classmethod
    def random_monster(cls):
        rand_monster = random.choice(cls.monsters)
        return rand_monster

    def upgrade_monster(self):
        self.set_hp(int(self.get_hp() * 1.3))
        self.set_max_hp(int(self.get_max_hp() * 1.3))
        self.set_min_damage(int(self.get_min_damage() * 1.2))
        self.set_max_damage(int(self.get_max_damage() * 1.2))

    @classmethod
    def upgrade_monster_lst(cls):
        for mob in cls.monsters:
            mob.upgrade_monster()
        return cls.monsters


# Monster.read_csv_file()
# Monster.upgrade_monster_lst()
#
# for monster in Monster.monsters:
#     monster.unit_info()


class Boss(Unit, DropItem):
    def __init__(self, name, hp, min_damage, max_damage, skill, skill_damage):
        super().__init__(name, hp, min_damage, max_damage)
        self._skill = skill
        self._skill_damage = skill_damage

    def get_skill(self):
        return self._skill

    def get_skill_damage(self):
        return self._skill_damage

    def unit_info(self):
        Line.line_star()
        print(f"┌ 보스명 : {self.get_name()}")
        print(f"│ 체력 : {self.get_hp()} / {self.get_max_hp()}")
        print(f"│ 스킬 : {self._skill}")
        print(f"└ 공격력 :  : {self.get_min_damage()} ~ {self.get_max_damage()}")
        Line.line_star()

    @classmethod
    def read_csv_file(cls, csv_file):
        bosses = []

        try:
            with open(csv_file, mode='r', encoding='utf-8') as f:
                reader = csv.reader(f)
                next(reader)

                for row in reader:
                    name, hp, min_attack, max_attack, skill, skill_damage = row
                    boss = cls(name, int(hp), int(min_attack), int(max_attack), skill, int(skill_damage))
                    bosses.append(boss)

        except FileNotFoundError:
            print("파일을 찾을 수 없습니다.")
        except UnicodeDecodeError:
            print("파일 읽기 오류: 올바른 인코딩을 사용하세요.")

        return bosses

    @classmethod
    def planned_boss(cls):
        boss_lst = cls.read_csv_file("unit_boss.csv")
        return boss_lst

    def drop(self):
        if not self.is_alive():
            dropped_weapon = Weapon(
                f"{self.get_name()}의 무기",
                "강력한 보스의 무기입니다.",
                self.get_max_damage() - 10,
                self.get_min_damage() - 15,
            )
            return dropped_weapon

    def defeat_boss(self, character):
        boss_weapon = Boss.drop(self)
        print(f"보스가 {boss_weapon.get_name()}을(를) 드랍했습니다!")
        select = str(input("보스 무기를 장착하시겠습니까? y/n : "))
        if select == 'y':
            character.equip(boss_weapon)
        Line.line_one()

# bosses = Boss.read_csv_file("unit_boss.csv")
# for boss in bosses:
#     boss.unit_info()
