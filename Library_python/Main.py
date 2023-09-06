from Interface import *


class Main:
    @staticmethod
    def management_system():
        sel = int(input("1. 회원 | 2. 관리자 | 3. 종료"))
        Line.line_two()
        print(">> 진행하시려는 번호를 입력하세요.")
        Line.line_one()
        if sel == 1:
            pass
        elif sel == 2:
            pass
        elif sel == 3:
            print("시스템을 종료합니다.")


class UserMenu:
    pass


class AdminMenu:
    pass
