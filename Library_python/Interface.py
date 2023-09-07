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
    def write_csv_file(self, file_path): # read 하고 추가해줘야 함!!
        with open(file_path, mode='w', encoding='utf-8') as f:
            writer = csv.writer(f)
        pass

    # 행 이름?? 어떻게 입력하는거지??? <- 행 없이 한줄한줄 넣어서 진행해보자 안되면 그때 다시 얘기하기!
    # def write_cse_file(file_path):
    #     with open(file_path, mode='w', encoding='utf-8') as f:
    #         writer = csv.writer(f)
    #
    #         for i in data:

    @abstractmethod
    def read_csv_file(self, file_path):
        pass

    # def read_csv_file(file_path):
    #     data = []
    #     with open(file_path, mode='r', encoding='utf-8') as f:
    #         reader = csv.reader(f)
    #         next(reader) <- 행 없이 진행해봐도 될 듯
    #         for row in reader:
    #             name, age, gender, address = row
    #             data.append((int(age), gender, address))
    #     return data