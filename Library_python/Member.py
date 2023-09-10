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
        self._id = ""
        self._password = ""

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

    def get_id(self):
        return self._id

    def get_password(self):
        return self._password

    def enter_personal_info(self):
        self.set_name()
        self.set_birth()
        self.set_phone_number()
        self.set_email()
        self.set_address()

    def enter_member_credentials(self):
        pass

    def register(self):
        self.enter_personal_info()
        self.enter_member_credentials()

    def write_csv_file(self, file_path, header):
        member_data = [
            self.get_name(),
            self.get_birth(),
            self.get_phone_number(),
            self.get_email(),
            self.get_address(),
            self.get_id(),
            self.get_password()
        ]
        self.read_csv_file(file_path)
        with open(file_path, mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            if header:
                writer.writerow(header)
            writer.writerow(member_data)
        print(f"{self.get_name()}님 반갑습니다! 등록이 완료되었습니다.")
        Line.line_two()

    @staticmethod
    def read_csv_file(file_path):
        try:
            with open(file_path, mode='x', encoding='utf-8', newline='') as f:
                header = ["Name", "Birth", "Phone Number", "Email", "Address", "ID", "Password"]
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

    def login(self, file_path):
        input_id = input("ID : ")
        input_password = input("Password : ")

        if self.is_valid_login(input_id, input_password, file_path):
            print("로그인 성공!")
            Line.line_two()
            return True
        else:
            print("로그인 실패. 아이디 또는 비밀번호를 확인하세요.")
            Line.line_two()
            return False

    def is_valid_login(self, input_id, input_password, file_path):
        member_list = self.read_csv_file(file_path)
        for row in member_list:
            _, _, _, _, _, member_id, password = row
            if member_id == input_id and password == input_password:
                return True
        return False


class User(Member):
    def __init__(self):
        super().__init__()

    def get_id(self):
        return self._id

    def set_id(self):
        while True:
            input_id = str(input("ID : "))
            if self.is_id_available(input_id):
                self._id = input_id
                break
            else:
                print("사용할 수 없는 아이디입니다. 다른 아이디를 입력하세요.")

    def is_id_available(self, input_id):
        user_lst = self.read_csv_file('CSVFiles/user.csv')
        for row in user_lst:
            if row[5] == input_id:
                return False
        return True

    def get_password(self):
        return self._password

    def set_password(self):
        self._password = str(input("Password : "))

    def enter_member_credentials(self):
        self.set_id()
        self.set_password()

    def rent_book(self):
        pass

    def return_book(self):
        pass

    def reserve_book(self):
        pass

    # def request_purchase(self): <- 뺄까... 일단 보류
    #     # 도서 검색 결과에 원하는 게 없으면 도서구매신청
    #     pass


# user = User()
# user_data = user.read_csv_file('CSVFiles/user.csv')
# for i in user_data:
#     print(i)


class Admin(Member):
    s_no = 0

    def __init__(self):
        super().__init__()

    def get_id(self):
        return self._id

    def set_id(self):
        staff_no_init = str(datetime.datetime.now().year)[-2:]
        Admin.s_no += 1  # 왜 누적이 되지 않지???
        four_s_no = f"{Admin.s_no:04d}"
        self._id = staff_no_init + four_s_no

    def get_password(self):
        return self._password

    def set_password(self):
        self._password = self.get_id() + "p" + self.get_phone_number()[-4:]

    def enter_member_credentials(self):
        self.set_id()
        self.set_password()

# admin = Admin()
# admin_data = admin.read_csv_file('CSVFiles/admin.csv')
# for i in admin_data:
#     print(i)
