import random
from abc import ABC, abstractmethod
from Interface import Line


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


class Potion(Item):
    def __init__(self, name, description, heal, quantity):
        super().__init__(name, description)
        self._heal = heal
        self._quantity = quantity

    def get_heal(self):
        return self._heal

    def get_quantity(self):
        return self._quantity

    def increase_quantity(self, amount):
        self._quantity += amount

    def decrease_quantity(self, amount):
        if self._quantity >= amount:
            self._quantity -= amount
        else:
            print("수량이 부족합니다.")


class RegularPotion(Potion):
    def __init__(self):
        super().__init__("일반 회복 포션", "체력을 30% 회복시킵니다.", 1, 3)

    def use(self, character):
        recovered_hp = int(character.get_max_hp() * 0.3)
        character.set_hp(character.get_hp() + recovered_hp)
        print(f"{character.get_name()}이(가) 일반 포션을 사용하여 {recovered_hp}만큼 체력을 회복했습니다.")
        self.decrease_quantity(1)

    def item_info(self):
        print("이 포션은 체력을 30% 회복시킵니다.")
        print(f"현재 소지량 : {self.get_quantity()}")


class SpecialPotion(Potion):
    def __init__(self):
        super().__init__("특별 회복 포션", "체력을 100% 회복시키지만 중독에 걸릴 수 있습니다.", 1, 1)

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
        self.decrease_special_quantity()

    def decrease_special_quantity(self):
        if self._quantity > 0:
            self._quantity -= 1
        else:
            print("포션이 모두 소진되었습니다.")

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
        print("🗡", self.get_name())
        print("┌ 설명 :", self.get_description())
        print("└ 공격력 :", self._min_damage, "~", self._max_damage)
        Line.line_star()

# weapon = Weapon()
# weapon.item_info()
