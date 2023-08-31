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


class EquipableItem(ABC):
    @abstractmethod
    def equip(self, item):
        pass

    @abstractmethod
    def un_equip(self):
        pass


class ConsumableItem(ABC):
    @abstractmethod
    def use(self, unit):
        pass


class DropItem(ABC):
    @abstractmethod
    def drop(self):
        pass
