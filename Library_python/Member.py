from abc import ABC, abstractmethod


class Member(ABC):
    pass


class User(Member):
    pass


class Auth:
    pass


class Admin(Member):
    pass


class Management(ABC):
    pass


class UserManagement(Management):
    pass


class BookManagement(Management):
    pass
