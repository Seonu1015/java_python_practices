from Member import User
import pandas as pd


class Book:
    def __init__(self):
        self._isbn = 0
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
        self._isbn = int(input("ISBN : "))

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
        super().__init__(0, "", "", "", 0)
        self._books = {}
        self._reservation_list = User.read_reservation_list()

    def add_book(self, book):
        self._books[book.get_isbn()] = book

    def remove_book(self, book_isbn):
        if book_isbn in self._books:
            del self._books[book_isbn]

    def search_book(self, keyword):
        matching_books = []
        for book in self._books:
            if keyword in book.get_title() or keyword in book.get_author():
                matching_books.append(book)

        if not matching_books:
            print("검색 결과가 없습니다.")
            return

        book_data = {
            "ISBN": [],
            "제목": [],
            "작가": [],
            "대여 여부": [],
            "예약자 수": []
        }

        for book in matching_books:
            book_data["ISBN"].append(book.get_isbn())
            book_data["제목"].append(book.get_title())
            book_data["작가"].append(book.get_author())

            if book.get_isbn() not in User.read_rental_list():
                availability = "대여 가능"
            else:
                availability = "대여 중"
            book_data["대여 여부"].append(availability)

            reservations = self._reservation_list.get(book.get_isbn(), [])
            book_data["예약자 수"].append(len(reservations))

        df = pd.DataFrame(book_data)

        print(df)

    def check_availability(self, book_isbn):
        rental_list = User.read_rental_list()
        if book_isbn in rental_list:
            return "대여 중"

        reservations = self._reservation_list(book_isbn)
        if len(reservations) > 0:
            return f"예약 중 ({len(reservations)} 명 예약 중)"

        return "대여 가능"
