import csv
import random
from abc import ABC, abstractmethod
from Interface import *
from Item import *


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
        self._sc = input
        self._birth = ""
        self._level = 1
        self._exp = 0
        self._equipped_weapon = None
        self._base_attack = 0
        self.set_name()
        self.set_birth()
        default_weapon = Weapon()
        self.equip(default_weapon)
        self.unit_info()

    def set_name(self):
        Line.line_one()
        input_name = str(input("캐릭터명을 지어주세요. : "))
        double_check = str(input(f"{input_name}으로 진행하시겠습니까? (y/n) : "))
        if double_check == 'y':
            self._name = input_name
            Line.line_one()
        else:
            self.set_name()

    def get_birth(self):
        return self._birth

    def set_birth(self): # 예외처리 추가 예정
        print("태생을 선택하세요. 선택한 태생에 따라 기본 스탯이 달라집니다.")
        print("퇴역군인 | 도굴꾼 | 망국의왕족 | 역병의사 | 못가진자")
        select_birth = input()
        Line.line_one()
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

    def unit_info(self):
        Line.line_star()
        print("┌ 캐릭터명 : " + self.get_name())
        print("│ 체력 : " + str(self.get_hp()) + " / " + str(self.get_max_hp()))
        if self._equipped_weapon:
            print("│ 장착무기 : " + self._equipped_weapon.get_name())
            print("└ 공격력 : " + str(self.get_min_damage()) + " ~ " + str(self.get_max_damage()))
        else:
            print("└ 장착무기 : 없음")
        Line.line_star()

    def get_exp(self):
        return self._exp

    def set_exp(self):
        self._exp = round((random.random() * 90 + 20) * 100.00) / 100.00
        print(str(self._exp) + "의 경험치를 획득하였습니다.")

    def accumulate_exp(self):
        self._exp += self.set_exp()
        full_exp = 100 + ((self._level - 1) * 50)
        if self._exp >= full_exp:
            Line.line_one()
            print(f"★ {self.get_name()} LEVEL UP ★")
            print(f"{self.get_name()}의 공격력이 상승합니다. (+3)")
            print(f"{self.get_name()}의 최대 체력이 상승합니다. (+20)")
            Line.line_one()
            self._level += 1
            self._exp -= full_exp
            self.set_min_damage(self.get_min_damage() + 3)
            self.set_max_damage(self.get_max_damage() + 3)
            self.set_max_hp(self.get_max_hp() + 20)
            # self.new_random_damage()

    def equip(self, weapon):
        if not self._equipped_weapon == None:
            self.unequip()

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

    def unequip(self):
        if not self._equipped_weapon == None:
            print(f"{self._equipped_weapon.get_name()}을(를) 해제했습니다.")
            self._equipped_weapon = None
        else:
            print("장착한 무기가 없습니다.")

    def use(self):
        if self.get_hp() >= self.get_max_hp():
            print("이미 최대 체력입니다.")
            return
        if Potion.get_instance().get_quantity() > 0:
            heal_amount = Potion.get_instance().get_heal()
            self.set_hp(min(self.get_hp() + heal_amount, self.get_max_hp()))
            Potion.get_instance().decrease_quantity(1)
            print(self.get_name() + "이(가) 회복 포션을 사용하여 " + str(heal_amount) + "만큼 회복합니다.")
            print("남은 체력: " + str(self.get_hp()) + " / " + str(self.get_max_hp()))
        else:
            print("회복 포션이 없습니다.")


class Monster(Unit, DropItem):
    def __init__(self, name, hp, min_damage, max_damage):
        super().__init__(name, hp, min_damage, max_damage)

    def unit_info(self):
        Line.line_star()
        print(f"┌ 몬스터명 : {self.get_name()}")
        print(f"│ 체력 :  : {self.get_hp()}")
        print(f"└ 공격력 :  : {self.get_min_damage()} ~ {self.get_max_damage()}")
        Line.line_star()

    def drop(self):
        potion = Potion.get_instance()
        amount = random.randint(1,3)
        potion.increase_quantity(amount)
        print(f"{self.get_name()}이(가) 회복 포션 {amount}개를 드랍했습니다.")

    @classmethod
    def read_csv_file(cls, csv_file):
        monsters = []

        with open(csv_file, mode='r', encoding='utf-8') as f:
            reader = csv.reader(f)
            next(reader)

            for row in reader:
                name, hp, min_attack, max_attack = row
                monster = cls(name, int(hp), int(min_attack), int(max_attack))
                monsters.append(monster)

        return monsters

    @classmethod
    def random_monster(self):
        monster_pool = self.read_csv_file("unit_monster.csv")
        rand_monster = random.choice(monster_pool)
        return rand_monster

# monsters = Monster.read_csv_file("unit_monster.csv")
# for monster in monsters:
#     monster.unit_info()

# rand_monster = Monster.random_monster()
# rand_monster.unit_info()


class Boss(Unit):
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
        print(f"│ 체력 : {self.get_hp()}")
        print(f"│ 스킬 : {self._skill}")
        print(f"└ 공격력 :  : {self.get_min_damage()} ~ {self.get_max_damage()}")
        Line.line_star()

    @classmethod
    def read_csv_file(cls, csv_file):
        bosses = []

        with open(csv_file, mode='r', encoding='utf-8') as f:
            reader = csv.reader(f)
            next(reader)

            for row in reader:
                name, hp, min_attack, max_attack, skill, skill_damage = row
                boss = cls(name, int(hp), int(min_attack), int(max_attack), skill, int(skill_damage))
                bosses.append(boss)

        return bosses

# bosses = Boss.read_csv_file("unit_boss.csv")
# for boss in bosses:
#     boss.unit_info()