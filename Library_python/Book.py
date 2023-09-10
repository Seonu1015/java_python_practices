import csv

import pandas as pd

from Interface import Line


class Book:
    def __init__(self):
        self._isbn = ""
        self._title = ""
        self._author = ""
        self._publisher = ""
        self._publication_year = 0

    def get_isbn(self):
        return self._isbn

    def get_title(self):
        return self._title

    def get_author(self):
        return self._author

    def get_publisher(self):
        return self._publisher

    def get_publication_year(self):
        return self._publication_year

    def set_isbn(self):
        self._isbn = str(input("ISBN : "))

    def set_title(self):
        self._title = str(input("제목 : "))

    def set_author(self):
        self._author = str(input("작가 : "))

    def set_publisher(self):
        self._publisher = str(input("출판사 : "))

    def set_publication_year(self):
        self._publication_year = int(input("발행년도 : "))


class Library(Book):
    def __init__(self):
        super().__init__()

    def add_book(self):
        self.set_isbn()
        self.set_title()
        self.set_author()
        self.set_publisher()
        self.set_publication_year()

    def add_book_to_library(self, file_path, header):
        book_data = [
            self.get_isbn(),
            self.get_title(),
            self.get_author(),
            self.get_publisher(),
            self.get_publication_year()
        ]

        self.read_csv_file(file_path)
        with open(file_path, mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            if header:
                writer.writerow(header)
            writer.writerow(book_data)

        print("도서가 도서관에 등록되었습니다.")
        Line.line_two()

    @staticmethod
    def read_csv_file(file_path):
        try:
            with open(file_path, mode='x', encoding='utf-8', newline='') as f:
                header = ["ISBN", "제목", "작가", "출판사", "발행년도"]
                writer = csv.writer(f)
                writer.writerow(header)
        except FileExistsError:
            pass
        data = []
        with open(file_path, mode='r', encoding='utf-8') as f:
            reader = csv.reader(f)
            next(reader)
            for row in reader:
                if row:
                    data.append(row)
        return data

    @staticmethod
    def remove_book(file_path):
        delete_book = input("도서 ISBN : ")
        book_data = Library.read_csv_file(file_path)
        found_book = None

        if delete_book == "":
            found_book = book_data
        else:
            for book in book_data:
                if delete_book in book[0]:
                    found_book = book
                    break

        if found_book:
            book_data.remove(found_book)
            with open(file_path, mode='w', encoding='utf-8', newline='') as f:
                writer = csv.writer(f)
                header = ["ISBN", "제목", "작가", "출판사", "발행년도"]
                writer.writerow(header)
                for book in book_data:
                    writer.writerow(book)
            print(f"도서 {found_book[1]}가 삭제되었습니다.")
        else:
            print("해당 정보를 찾을 수 없습니다.")

    @staticmethod
    def search_book(file_path):
        search_book = input("도서 ISBN (전체 검색을 원하면 엔터) : ")
        book_data = Library.read_csv_file(file_path)
        found_isbn = None

        if search_book == "":
            found_isbn = book_data
        else:
            for book in book_data:
                if search_book in book[0]:
                    found_isbn = book
                    break

        if found_isbn:
            columns = ["ISBN", "제목", "작가", "출판사", "발행년도"]
            df = pd.DataFrame(found_isbn, columns=columns)
            print("검색 결과:")
            print(df)
        else:
            print("검색 조건과 일치하는 도서가 없습니다.")
        Line.line_two()

    @staticmethod
    def check_availability():
        pass
