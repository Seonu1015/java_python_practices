from Interface import *


class Main(ABC):
    @staticmethod
    def entrance_system():
        sel = int(input("1. 회원 | 2. 관리자 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if sel == 1:
            pass
        elif sel == 2:
            pass
        elif sel == 3:
            print("시스템을 종료합니다.")

    @abstractmethod
    def login(self):
        pass


class UserMenu(Main):
    def login(self):
        pass

    @staticmethod
    def user_system():
        sel = int(input("1. 회원 정보 | 2. 도서 검색 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if sel == 1:
            pass
        elif sel == 2:
            pass
        elif sel == 3:
            print("시스템을 종료합니다.")


class AdminMenu(Main):
    def login(self):
        pass

    @staticmethod
    def manage_system():
        sel = int(input("1. 회원 관리 | 2. 도서 관리 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
        Line.line_two()
        if sel == 1:
            pass
        elif sel == 2:
            pass
        elif sel == 3:
            print("시스템을 종료합니다.")

