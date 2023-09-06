from Member import User


class Book:
    def __init__(self, title, author, publisher, publication_year):
        self.title = title
        self.author = author
        self.publisher = publisher
        self.publication_year = publication_year

    def get_title(self):
        return self.title

    def get_author(self):
        return self.author

    def get_publisher(self):
        return self.publisher

    def get_publication_year(self):
        return self.publication_year

    def set_title(self, title):
        self.title = title

    def set_author(self, author):
        self.author = author

    def set_publisher(self, publisher):
        self.publisher = publisher

    def set_publication_year(self, publication_year):
        self.publication_year = publication_year


class Library(Book):
    def __init__(self):
        super().__init__("", "", "", 0)

    def rental_history(self):
        pass

    def reserve_list(self):
        pass

    def request_purchase_list(self):
        pass
