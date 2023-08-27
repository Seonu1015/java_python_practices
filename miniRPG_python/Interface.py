from abc import ABC, abstractmethod


class Line:
    @staticmethod
    def line_one():
        print("-----------------------------------------")

    @staticmethod
    def line_two():
        print("=========================================")


class EquipableItem(ABC):
    @abstractmethod
    def equip(self):
        pass

    @abstractmethod
    def unequip(self):
        pass


class ConsumableItem(ABC):
    @abstractmethod
    def use(self):
        pass
