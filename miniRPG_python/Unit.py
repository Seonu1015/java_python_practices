import random
from abc import ABC, abstractmethod
from Interface import *
from Item import *


class Unit(ABC):
    def __init__(self, name, health, max_damage, min_damage):
        self._name = name
        self._health = health
        self._max_health = health
        self._attack = 0
        self._random_attack = 0
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

    def set_rand_attack(self, rand_attack):
        self._rand_attack = random.randint(self._min_damage, self._max_damage + 1)

    def get_min_damage(self):
        return self._min_damage

    def set_min_damage(self, min_damage):
        self._min_damage = min_damage

    def get_max_damage(self):
        return self._max_damage

    def set_max_damage(self, max_damage):
        self._max_damage = max_damage

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

    def set_name(self):
        input_name = str(input("캐릭터명을 지어주세요. : "))  # Replace this with actual user input method
        self._name = input_name

    def get_birth(self):
        return self._birth

    def set_birth(self):
        print("태생을 선택하세요. 선택한 태생에 따라 기본 스탯이 달라집니다.")
        print("퇴역군인 | 도굴꾼 | 망국의왕족 | 역병의사 | 못가진자")
        select_birth = input()
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
            print(f"{self.get_name()}의 최대 체력이 상승합니다. (+10)")
            Line.line_one()
            self._level += 1
            self._exp -= full_exp
            self.set_min_damage(self.get_min_damage() + 3)
            self.set_max_damage(self.get_max_damage() + 3)
            self.set_max_hp(self.get_max_hp() + 10)

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
        super().set_rand_attack(random.randint(self._min_damage, self._max_damage + 1))
        self.set_attack(super().get_rand_attack)

    def unequip(self):
        if not self._equipped_weapon == None:
            print(f"{self._equipped_weapon.get_name()}을(를) 해제했습니다.")
            self._equipped_weapon = None
        else:
            print("장착한 무기가 없습니다.")

    # def use(self):
    #     if self.get_hp() >= self.get_max_hp():
    #         print("이미 최대 체력입니다.")
    #         return
    #     if

char = Character()
char.unit_info()