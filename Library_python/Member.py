import csv
import datetime
from Interface import Line


class Member:
    def __init__(self):
        self._name = ""
        self._birth = 0
        self._phone_number = ""
        self._email = ""
        self._address = ""

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
        super().__init__()
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
            if i[5] == input_id:
                return False
        return True

    def get_password(self):
        return self._password

    def set_password(self):
        self._password = str(input("Password : "))

    def enter_personal_info(self):
        self.set_name()
        self.set_birth()
        self.set_phone_number()
        self.set_email()
        self.set_address()

    def enter_user_credentials(self):
        self.set_user_id()
        self.set_password()

    def register(self):
        self.enter_personal_info()
        self.enter_user_credentials()

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
        with open(file_path, mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(user_data)
        print(f"{self.get_name()}님 반갑습니다! 회원가입이 완료되었습니다.")
        Line.line_two()

    @staticmethod
    def read_csv_file(file_path):
        try:
            with open(file_path, mode='x', encoding='utf-8', newline='') as f:
                header = ["Name", "Birth", "Phone Number", "Email", "Address", "User ID", "Password"]
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

    def rent_book(self, isbn, title):
        current_date = datetime.datetime.now().date()
        formatted_date = current_date.strftime("%Y-%m-%d")
        rental_data = [self.get_user_id(), self.get_name(), isbn, title,
                       formatted_date]
        self.write_rental_list(rental_data)
        print(f"{title} 책을 대여했습니다.")

    def return_book(self, isbn):
        rental_data = self.read_rental_list()
        found = False
        for i, record in enumerate(rental_data):
            if record[0] == self.get_user_id() and record[2] == isbn:
                del rental_data[i]
                found = True
                break

        if found:
            self.write_rental_list(rental_data)
            print("책을 반납했습니다.")
        else:
            print("대여한 책 중 해당 ISBN을 가진 책을 찾을 수 없습니다.")

    @staticmethod
    def write_rental_list(data):
        with open('rental_list.csv', mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(data)

    @staticmethod
    def read_rental_list():
        data = []
        try:
            with open('rental_list.csv', mode='r', encoding='utf-8') as f:
                reader = csv.reader(f)
                for row in reader:
                    data.append(row)
        except FileNotFoundError:
            pass
        return data

    def reserve_book(self, isbn, title):
        # 대여 중인 책인지 확인
        if isbn in User.read_rental_list():
            print("해당 책은 이미 대여 중입니다. 예약할 수 없습니다.")
            return

        # 이미 예약한 책인지 확인
        if self.is_book_reserved(isbn):
            print("이미 예약한 책입니다.")
            return

        # 최대 3권까지 예약 가능한지 확인
        if self.get_reserved_books_count() >= 3:
            print("예약은 최대 3권까지만 가능합니다.")
            return

        # 예약 정보를 추가하고 CSV 파일 업데이트
        reservation_data = [self.get_user_id(), self.get_name(), isbn, title]
        self.write_reservation_list(reservation_data)
        print(f"{title} 책을 예약했습니다.")

    def is_book_reserved(self, isbn):
        reservation_list = self.read_reservation_list()
        for reservation in reservation_list:
            if reservation[2] == isbn:
                return True
        return False

    def get_reserved_books_count(self):
        reservation_list = self.read_reservation_list()
        return len(reservation_list)

    @staticmethod
    def write_reservation_list(data):
        with open('reservation_list.csv', mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(data)

    @staticmethod
    def read_reservation_list():
        data = []
        try:
            with open('reservation_list.csv', mode='r', encoding='utf-8') as f:
                reader = csv.reader(f)
                for row in reader:
                    data.append(row)
        except FileNotFoundError:
            pass
        return data

    # def request_purchase(self): <- 뺄까... 일단 보류
    #     # 도서 검색 결과에 원하는 게 없으면 도서구매신청
    #     pass


# user = User()
# user_data = user.read_csv_file('user.csv')
# for i in user_data:
#     print(i)


class Admin(Member):
    def __init__(self):
        super().__init__()
        self._admin_id = ""
        self._password = ""

    def get_admin_id(self):
        return self._admin_id

    def set_admin_id(self):
        staff_no_init = str(datetime.datetime.now().year)[-2:]
        s_no = 1
        four_s_no = f"{s_no:04d}"
        self._admin_id = staff_no_init + four_s_no

    def get_password(self):
        return self._password

    def set_password(self):  # 랜덤한 비밀번호?? 아니면 그냥 기본 설정만 해두고 수정하게끔?
        self._password = self.get_admin_id() + "p" + self.get_phone_number()[-4:]

    def register(self):
        self.set_name()
        self.set_birth()
        self.set_phone_number()
        self.set_email()
        self.set_address()
        self.set_admin_id()
        self.set_password()

    def write_csv_file(self, file_path):
        user_data = [
            self.get_name(),
            self.get_birth(),
            self.get_phone_number(),
            self.get_email(),
            self.get_address(),
            self.get_admin_id(),
            self.get_password()
        ]
        with open(file_path, mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(user_data)
        Line.line_two()

    @staticmethod
    def read_csv_file(file_path):
        try:
            with open(file_path, mode='x', encoding='utf-8', newline='') as f:
                header = ["Name", "Birth", "Phone Number", "Email", "Address", "Admin ID", "Password"]
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
        user_list = self.read_csv_file('admin.csv')
        for row in user_list:
            _, _, _, _, _, admin_id, password = row
            if admin_id == input_id and password == input_password:
                return True
        return False
