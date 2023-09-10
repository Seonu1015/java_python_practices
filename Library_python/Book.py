import pandas as pd
from Member import User
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
    books = {}

    def __init__(self):
        super().__init__()

    @staticmethod
    def add_book(book):
        Library.books[book.get_isbn()] = book

    @staticmethod
    def remove_book(book_isbn):
        pass

    def search_book(self):
        pass

    def check_availability(self, book_isbn):
        pass
