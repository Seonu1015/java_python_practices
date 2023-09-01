import random
from abc import ABC, abstractmethod
from Interface import *


class Item(ABC):
    def __init__(self, name, description):
        self._name = name
        self._description = description

    def get_name(self):
        return self._name

    def set_name(self, name):
        self._name = name

    def get_description(self):
        return self._description

    def set_description(self, description):
        self._description = description

    @abstractmethod
    def item_info(self):
        pass


class Potion(Item, ABC):
    def __init__(self, name, description, heal):
        super().__init__(name, description)
        self._heal = heal

    def get_heal(self):
        return self._heal

    @abstractmethod
    def get_quantity(self):
        pass

    @abstractmethod
    def increase_quantity(self, amount):
        pass

    @abstractmethod
    def decrease_quantity(self, amount):
        pass


class RegularPotion(Potion, ConsumableItem, DropItem):
    quantity = 3

    def __init__(self):
        super().__init__("일반 회복 포션", "체력을 30% 회복시킵니다.", 1)

    def get_quantity(self):
        return RegularPotion.quantity

    def increase_quantity(self, amount):
        RegularPotion.quantity += amount

    def decrease_quantity(self, amount):
        if RegularPotion.quantity >= amount:
            RegularPotion.quantity -= amount
        else:
            print("수량이 부족합니다.")

    def use(self, character):
        max_hp = character.get_max_hp()
        current_hp = character.get_hp()

        recovered_hp = int(max_hp * 0.3)
        remaining_hp_space = max_hp - current_hp

        if recovered_hp > remaining_hp_space:
            recovered_hp = remaining_hp_space

        character.set_hp(current_hp + recovered_hp)
        print(f"{character.get_name()}이(가) 일반 포션을 사용하여 {recovered_hp}만큼 체력을 회복했습니다.")
        self.decrease_quantity(1)

    def drop(self):
        amount = random.randint(1, 3)
        self.increase_quantity(amount)
        print(f"{self.get_name()}이(가) 일반 포션 {amount}개를 드랍했습니다.")

    def item_info(self):
        Line.line_star()
        print(self.get_name())
        print("┌ 설명 : " + self.get_description())
        print("└ 소지 : " + str(self.get_quantity()))
        Line.line_star()


class SpecialPotion(Potion, ConsumableItem, DropItem):
    quantity = 3

    def __init__(self):
        super().__init__("특별 회복 포션", "체력을 100% 회복시키지만 중독에 걸릴 수 있습니다.", 1)

    def get_quantity(self):
        return SpecialPotion.quantity

    def increase_quantity(self, amount):
        SpecialPotion.quantity += amount

    def decrease_quantity(self, amount):
        if SpecialPotion.quantity >= amount:
            SpecialPotion.quantity -= amount
        else:
            print("수량이 부족합니다.")

    def use(self, character):
        is_poisoned = random.random() <= 0.2
        if is_poisoned:
            print(f"{character.get_name()}이(가) 특별 포션을 사용하였으나 중독에 걸렸습니다!")
            for _ in range(5):
                character.take_damage(10)
                print(f"{character.get_name()}의 체력이 10만큼 줄었습니다. 현재 체력: {character.get_hp()}")
        else:
            print(f"{character.get_name()}이(가) 특별 포션을 사용하여 체력을 완전히 회복했습니다.")
            character.set_hp(character.get_max_hp())
        self.decrease_quantity(1)

    def drop(self):
        special_drop_rate = 0.3
        rand_special_drop_rate = random.random()

        if rand_special_drop_rate <= special_drop_rate:
            self.increase_quantity(1)
            print(f"{self.get_name()}이(가) 특별 포션 1개를 드랍했습니다.")

    def item_info(self):
        Line.line_star()
        print(self.get_name())
        print("┌ 설명 : " + self.get_description())
        print("└ 소지 : " + str(self.get_quantity()))
        Line.line_star()


# sp = SpecialPotion()
# sp.item_info()

class Weapon(Item):
    def __init__(self, name="누군가 쓰다버린 검", description="과거 잘나갔던 용병이 쓰던 검으로 시간이 지나 꽤 녹슬었다.", max_damage=3, min_damage=1):
        super().__init__(name, description)
        self._max_damage = max_damage
        self._min_damage = min_damage

    def get_max_damage(self):
        return self._max_damage

    def get_min_damage(self):
        return self._min_damage

    def item_info(self):
        Line.line_star()
        print(self.get_name())
        print("┌ 설명 :", self.get_description())
        print("└ 공격력 :", self._min_damage, "~", self._max_damage)
        Line.line_star()

# weapon = Weapon()
# weapon.item_info()
