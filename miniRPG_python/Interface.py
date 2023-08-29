from abc import ABC, abstractmethod


class Line:
    @staticmethod
    def line_one():
        print("-----------------------------------------")

    @staticmethod
    def line_two():
        print("=========================================")

    @staticmethod
    def line_star():
        print("*****************************************")


class DropItem(ABC):
    @abstractmethod
    def drop(self):
        pass


class EquipableItem(ABC):
    @abstractmethod
    def equip(self, item):
        pass

    @abstractmethod
    def unequip(self):
        pass


class ConsumableItem(ABC):
    @abstractmethod
    def use(self):
        pass
