import csv
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

    # 행 이름?? 어떻게 입력하는거지???
    # def write_cse_file(file_path):
    #     with open(file_path, mode='w', encoding='urf-8') as f:
    #         writer = csv.writer(f)
    #
    #         for i in data:

    @abstractmethod
    def read_csv_file(file_path):
        pass

    # def read_csv_file(file_path):
    #     data = []
    #     with open(file_path, mode='r', encoding='utf-8') as f:
    #         reader = csv.reader(f)
    #         next(reader)
    #         for row in reader:
    #             name, age, gender, address = row
    #             data.append((int(age), gender, address))
    #     return data