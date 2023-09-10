import csv

import pandas as pd
from Book import Library
from Interface import Line
from Member import Member, User, Admin


class Main:
    @staticmethod
    def entrance_system():
        choice = int(input("1. 회원 | 2. 관리자 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            UserMenu.start_user_system()
        elif choice == 2:
            if AdminMenu.is_admin_info_exist():
                admin = Admin()
                if admin.login('CSVFiles/admin.csv'):
                    AdminMenu.manage_system()
            else:
                print("관리자 정보가 등록되어 있지 않습니다. 최초 관리자 정보를 등록해주세요.")
                admin = Admin()
                admin.register()
                admin.write_csv_file('CSVFiles/admin.csv')

        elif choice == 3:
            print("시스템을 종료합니다.")


class UserMenu(Main):
    @staticmethod
    def start_user_system():
        while True:
            choice = int(input("1. 로그인 | 2. 회원 가입 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                user = User()
                if user.login('CSVFiles/user.csv'):
                    UserMenu.user_system()
            elif choice == 2:
                user = User()
                user.register()
                user.write_csv_file('CSVFiles/user.csv')
            elif choice == 3:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def user_system():
        library = Library()
        choice = int(input("1. 도서 검색 | 2. 대여 목록 | 3. 예약 목록 | 4. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            pass
        elif choice == 2:
            library.search_book()
        elif choice == 3:
            pass
        elif choice == 4:
            print("시스템을 종료합니다.")


class AdminMenu(Main):
    @staticmethod
    def is_admin_info_exist():
        try:
            with open('CSVFiles/admin.csv', mode='r', encoding='utf-8') as f:
                reader = csv.reader(f)
                header = next(reader)
                if "Admin ID" in header and "Password" in header:
                    for row in reader:
                        if row:
                            return True
        except FileNotFoundError:
            return False

    @staticmethod
    def manage_system():
        choice = int(input("1. 회원 관리 | 2. 직원 관리 | 3. 도서 관리 | 4. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            AdminMenu.manage_user()
        elif choice == 2:
            AdminMenu.manage_staff()
        elif choice == 3:
            AdminMenu.manage_books()
        elif choice == 4:
            print("시스템을 종료합니다.")

    @staticmethod
    def manage_user():
        choice = int(input("1. 회원 검색 | 2. 연체자 검색 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            AdminMenu.search_member('CSVFiles/user.csv')
        elif choice == 2:
            pass
        elif choice == 3:
            print("시스템을 종료합니다.")

    @staticmethod
    def manage_staff():
        while True:
            choice = int(input("1. 직원 검색 | 2. 직원 등록 | 3. 직원 삭제 | 4. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                AdminMenu.search_member('CSVFiles/admin.csv')
            elif choice == 2:
                admin = Admin()
                admin.register()
                admin.write_csv_file('CSVFiles/admin.csv')
            elif choice == 3:
                AdminMenu.remove_member('CSVFiles/admin.csv')
            elif choice == 4:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def search_member(file_path):
        search_member = input("이름 or ID (전체 검색을 원하면 엔터) : ")
        member_data = User.read_csv_file(file_path)
        found_member = None

        if search_member == "":
            found_member = member_data
        else:
            for member in member_data:
                if search_member.lower() in member[0].lower() or search_member.lower() in member[5].lower():
                    found_member = member
                    break

        if found_member:
            columns = ["이름", "생년월일", "휴대폰 번호", "이메일", "주소", "ID", "Password"]
            df = pd.DataFrame(found_member, columns=columns)
            print("검색 결과:")
            print(df)
        else:
            print("검색 조건과 일치하는 사람이 없습니다.")

    @staticmethod
    def remove_member(file_path):
        delete_member = input("이름 or ID : ")
        member_data = Member.read_csv_file(file_path)
        found_member = None

        if delete_member == "":
            found_member = member_data
        else:
            for member in member_data:
                if delete_member.lower() in member[0].lower() or delete_member.lower() in member[5].lower():
                    found_member = member
                    break

        if found_member:
            member_data.remove(found_member)
            with open(file_path, mode='w', encoding='utf-8', newline='') as f:
                writer = csv.writer(f)
                header = ["Name", "Birth", "Phone Number", "Email", "Address", "User ID", "Password"]
                writer.writerow(header)
                for member in member_data:
                    writer.writerow(member)
            print(f"{found_member[0]}님의 정보가 삭제되었습니다.")
        else:
            print("해당 정보를 찾을 수 없습니다.")

    @staticmethod
    def manage_books():
        library = Library()
        choice = int(input("1. 도서 검색 | 2. 도서 추가 | 3. 도서 삭제 | 4. 대여 목록 | 5. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            pass
        elif choice == 2:
            Admin.add_book_to_library()
        elif choice == 3:
            select_isbn = str(input("삭제하려는 도서의 ISBN을 입력하세요. : "))
            library.remove_book(select_isbn)
        elif choice == 4:
            pass
        # 대여 목록에서 대여신청/반납 승인 구현
        elif choice == 5:
            print("시스템을 종료합니다.")
