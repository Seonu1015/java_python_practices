import pandas as pd
from Book import Library
from Interface import Line
from Member import User, Admin


class Main:
    @staticmethod
    def entrance_system():
        choice = int(input("1. 회원 | 2. 관리자 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            UserMenu.start_user_system()
        elif choice == 2:
            # if "admin.csv" 파일이 없다면 최초 관리자 계정을 설정하게 하고 싶음!!
            # 관리자는 일단 한명인걸로 할까....
            admin = Admin()
            if admin.login():
                AdminMenu.manage_system()
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
                if user.login():
                    UserMenu.user_system()
            elif choice == 2:
                user = User()
                user.register()
                user.write_csv_file('user.csv')
            elif choice == 3:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def user_system():
        library = Library()
        choice = int(input("1. 회원 정보 | 2. 도서 검색 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            pass
        elif choice == 2:
            keyword = str(input("검색(제목or작가) : "))
            library.search_book(keyword)
        elif choice == 3:
            print("시스템을 종료합니다.")


class AdminMenu(Main):

    @staticmethod
    def manage_system():
        choice = int(input("1. 회원 관리 | 2. 도서 관리 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
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

    @staticmethod
    def display_user_info():
        user_data = User.read_csv_file('user.csv')
        if not user_data:
            print("사용자 데이터가 없습니다.")
        else:
            columns = ["이름", "생년월일", "휴대폰 번호", "이메일", "주소", "User ID", "Password"]
            df = pd.DataFrame(user_data, columns=columns)
            print(df)

    @staticmethod
    def manage_staff():
        pass

    @staticmethod
    def manage_books():
        choice = int(input("1. 도서 검색 | 2. 도서 추가 | 3. 도서 삭제 | 4. 대여 목록 | 5. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if choice == 1:
            Library.add_book()
        elif choice == 2:
            Library.remove_book()
        elif choice == 3:
            pass
        elif choice == 4:
            pass
        # 대여 목록에서 반납 처리 구현
        elif choice == 5:
            print("시스템을 종료합니다.")
