import csv
import datetime
from abc import ABC, abstractmethod


class Member(ABC):
    def __init__(self, name, birth, phone_number, email, address):
        self._name = name
        self._birth = birth
        self._phone_number = phone_number
        self._email = email
        self._address = address

    def get_name(self):
        return self._name

    def set_name(self):
        input_name = str(input("이름 : "))
        self._name = input_name

    def get_birth(self):
        return self._birth

    def set_birth(self):
        self._birth = int(input("생년월일 (YYMMDD) : "))

    def get_phone_number(self):
        return self._phone_number

    def set_phone_number(self):
        self._phone_number = str(input("휴대폰 : "))

    def get_email(self):
        return self._email

    def set_email(self):
        self._email = str(input("이메일 : "))

    def get_address(self):
        return self._address


class User(Member):
    def __init__(self):
        super().__init__("", 0, "", "", "")
        self._user_id = ""
        self._password = ""

    def get_user_id(self):
        return self._user_id

    def set_user_id(self):
        self._user_id = str(input("ID : "))

    def get_password(self):
        return self._password

    def set_password(self):
        self._password = str(input("Password : "))

    def rent_book(self):
        pass

    def return_book(self):
        pass

    def reserve_book(self):
        pass

    def request_purchase(self):
        pass


class Admin(Member):
    def __init__(self):
        super().__init__("", 0, "", "", "")
        self._staff_no = ""
        self._password = ""

    def get_staff_no(self):
        return self._staff_no

    def set_staff_no(self):
        staff_no_init = str(datetime.datetime.now().year)[-2:]
        s_no = 1
        four_s_no = f"{s_no:04d}"
        self._staff_no = staff_no_init + four_s_no

    def get_password(self):
        return self._password

    def set_password(self):
        self._password = self.get_staff_no() + "p" + self.get_phone_number()[-4:]
