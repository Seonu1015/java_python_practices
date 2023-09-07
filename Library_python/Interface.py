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
    def write_csv_file(self, file_path):  # read 하고 추가해줘야 함!!
        with open(file_path, mode='w', encoding='utf-8') as f:
            writer = csv.writer(f)

    @abstractmethod
    def read_csv_file(self, file_path):
        data = []
        with open(file_path, mode='r', encoding='utf-8') as f:
            reader = csv.reader(f)
            for row in reader:
                name, birth, phone_number, email, address = row
                data.append((name, int(birth), phone_number, email, address))
        return data
