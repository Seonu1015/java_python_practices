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


class WriteFile(ABC):
    @abstractmethod
    def write_csv_file(file_path):
        pass

    @abstractmethod
    def read_csv_file(file_path):
        pass
