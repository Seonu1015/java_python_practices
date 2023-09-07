import csv
import datetime
from Interface import *
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

    def set_address(self):
        self._address = str(input("주소 : "))


class User(Member):
    def __init__(self):
        super().__init__("", 0, "", "", "")
        self._user_id = ""
        self._password = ""

    def get_user_id(self):
        return self._user_id

    def set_user_id(self):
        while True:
            input_id = str(input("ID : "))
            if self.is_user_id_available(input_id):
                self._user_id = input_id
                break
            else:
                print("사용할 수 없는 아이디입니다. 다른 아이디를 입력하세요.")

    def is_user_id_available(self, input_id):
        user_lst = self.read_csv_file('user.csv')
        for i in user_lst:
            if i[5] != input_id:
                return True
            else:
                return False

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

    def register(self):
        self.set_name()
        self.set_birth()
        self.set_phone_number()
        self.set_email()
        self.set_address()
        self.set_user_id()
        self.set_password()

    def write_csv_file(self, file_path):
        user_data = [
            self.get_name(),
            self.get_birth(),
            self.get_phone_number(),
            self.get_email(),
            self.get_address(),
            self.get_user_id(),
            self.get_password()
        ]
        with open(file_path, mode='a', encoding='utf-8') as f:
            writer = csv.writer(f)
            writer.writerow(user_data)

    @staticmethod
    def read_csv_file(file_path):
        data = []
        with open(file_path, mode='r', encoding='utf-8') as f:
            reader = csv.reader(f)
            for row in reader:
                if row:
                    data.append(row)
        return data

    def login(self):
        input_id = str(input("ID : "))
        input_password = str(input("Password : "))

        if self.is_valid_login(input_id, input_password):
            print("로그인 성공!")
            Line.line_two()
            return True
        else:
            print("로그인 실패. 아이디 또는 비밀번호를 확인하세요.")
            Line.line_two()
            return False

    def is_valid_login(self, input_id, input_password):
        user_list = self.read_csv_file('user.csv')
        for row in user_list:
            _, _, _, _, _, user_id, password = row
            if user_id == input_id and password == input_password:
                return True
        return False


# user = User()
# user_data = user.read_csv_file('user.csv')
# for i in user_data:
#     print(i)


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
