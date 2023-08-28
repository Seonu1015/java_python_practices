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
    def __init__(self, name, description, heal):
        super().__init__(name, description)
        self._heal = heal
        self._quantity = 3

    def get_heal(self):
        return self._heal

    def set_heal(self, heal):
        self._heal = heal

    def get_quantity(self):
        return self._quantity

    def set_quantity(self, quantity):
        self._quantity = quantity

    def increase_quantity(self, amount):
        self._quantity += amount

    def decrease_quantity(self, amount):
        if self._quantity >= amount:
            self._quantity -= amount
        else:
            print("수량이 부족합니다.")

    @staticmethod
    def get_instance():
        if Potion.instance is None:
            Potion.instance = Potion("회복 포션", "체력을 회복합니다.", 40)
        return Potion.instance

    @abstractmethod
    def item_info(self):
        Line.line_star()
        print("💊 " + self.get_name())
        print("┌ 설명 : " + self.get_description())
        print("│ 개수 : " + str(self.get_quantity()))
        print("└ 회복량 : " + str(self._heal))
        Line.line_star()


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