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
        try:  # 길이 제한에 관한 부분 필요할지도??
            self._birth = int(input("생년월일 (YYMMDD) : "))
        except TypeError:
            print("숫자만 입력해주세요.")

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
    def __init__(self, user_id, password):
        super().__init__("", 0, "", "", "")
        self._user_id = user_id
        self._password = password


class Admin(Member):
    pass
