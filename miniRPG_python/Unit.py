import random
from abc import ABC, abstractmethod

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

    def set_min_damage(self,min_damage):
        self._min_damage = min_damage

    def get_max_damage(self):
        return self._max_damage

    def set_max_damage(self, max_damage):
        self._max_damage = max_damage

    @abstractmethod
    def unit_info(self):
        pass

class Character(Unit):
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
        default_weapon = ItemWeapon.get_instance()
        self.equip(default_weapon)

    def set_name(self):
        print("캐릭터명을 지어주세요. : ")
        input_name = input()  # Replace this with actual user input method
        self.set_name(input_name)

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

