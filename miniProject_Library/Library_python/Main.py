import csv
import datetime
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
                member = Admin()
                if member.login('CSVFiles/admin.csv'):
                    AdminMenu.manage_system()
            else:
                print("관리자 정보가 등록되어 있지 않습니다. 최초 관리자 정보를 등록해주세요.")
                member = Admin()
                member.register()
                member.write_csv_file('CSVFiles/admin.csv', header=None)

        elif choice == 3:
            print("시스템을 종료합니다.")


class UserMenu(Main):
    current_user = None
    last_request_number = 0

    @staticmethod
    def start_user_system():
        while True:
            choice = int(input("1. 로그인 | 2. 회원 가입 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                user = User()
                if user.login('CSVFiles/user.csv'):
                    UserMenu.current_user = user
                    UserMenu.user_system()
            elif choice == 2:
                user = User()
                user.register()
                user.write_csv_file('CSVFiles/user.csv', header=None)
            elif choice == 3:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def user_system():
        library = Library()
        while True:
            choice = int(input("1. 도서 검색 | 2. 신청 내역 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                Library.search_book('CSVFiles/library_book.csv')
                lookingfor_book = str(input("찾으시는 책이 있으신가요? (y/n) : "))
                if lookingfor_book == "y":
                    isbn = input("해당 책의 ISBN을 입력하세요. : ")
                    found_book = Library.find_book_by_isbn(isbn)
                    if found_book:
                        action = input("이 책을 대여하시겠습니까? (y/n) : ")
                        if action == "y":
                            request_data = UserMenu.rent_request(isbn)
                            UserMenu.add_rent_request(request_data, 'CSVFiles/rental_requests.csv')
                            library.rent_book()
                        else:
                            print("도서 대여 신청이 취소되었습니다.")
                    else:
                        print("해당 ISBN을 가진 도서를 찾을 수 없습니다.")
                elif lookingfor_book == "n":
                    action = input("구매를 요청하시겠습니까? (y/n) : ")
                    if action == "y":
                        book_title = input("요청하려는 도서의 제목을 입력하세요: ")
                        request_data = UserMenu.purchase_request(book_title)
                        UserMenu.add_purchase_request(request_data, 'CSVFiles/purchase_requests.csv')
                        print("구매 요청이 완료되었습니다.")
                    else:
                        print("구매 요청이 취소되었습니다.")
                else:
                    print("(입력이 잘못되었습니다. y or n 으로 입력해주세요.)")
            elif choice == 2:
                pass
            elif choice == 3:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def rent_request(book_isbn):
        UserMenu.last_request_number += 1
        request_number = datetime.datetime.now().strftime('%m%d') + f"{UserMenu.last_request_number:03d}"
        request_data = {
            "NO": request_number,
            "요청자": UserMenu.current_user.get_id(),
            "ISBN": book_isbn,
            "요청 날짜": datetime.datetime.now().strftime("%Y-%m-%d"),
            "대여 확인": "대여 신청 중"
        }
        return request_data

    @staticmethod
    def add_rent_request(request_data, file_path):
        try:
            with open(file_path, mode='x', encoding='utf-8', newline='') as f:
                header = ["NO", "요청자", "ISBN", "요청 날짜", "대여 확인"]
                writer = csv.writer(f)
                writer.writerow(header)
        except FileExistsError:
            pass

        with open(file_path, mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            writer.writerow([request_data["NO"], request_data["요청자"], request_data["ISBN"], request_data["요청 날짜"],
                             request_data["대여 확인"]])

    @staticmethod
    def purchase_request(book_title):
        UserMenu.last_request_number += 1
        request_number = datetime.datetime.now().strftime('%m%d') + f"{UserMenu.last_request_number:03d}"
        request_data = {
            "NO": request_number,
            "요청자": UserMenu.current_user.get_id(),
            "도서 제목": book_title,
            "요청 날짜": datetime.datetime.now().strftime("%Y-%m-%d"),
            "승인 여부": "",
            "반려 사유": ""
        }
        return request_data

    @staticmethod
    def add_purchase_request(request_data, file_path):
        try:
            with open(file_path, mode='x', encoding='utf-8', newline='') as f:
                header = ["NO", "요청자", "도서 제목", "요청 날짜", "승인 여부", "반려 사유"]
                writer = csv.writer(f)
                writer.writerow(header)
        except FileExistsError:
            pass

        with open(file_path, mode='a', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            writer.writerow([request_data["NO"], request_data["요청자"], request_data["도서 제목"], request_data["요청 날짜"],
                             request_data["승인 여부"], request_data["반려 사유"]])


class AdminMenu(Main):
    @staticmethod
    def is_admin_info_exist():
        try:
            with open('CSVFiles/admin.csv', mode='r', encoding='utf-8') as f:
                reader = csv.reader(f)
                header = next(reader)
                if "ID" in header and "Password" in header:
                    for row in reader:
                        if row:
                            return True
        except FileNotFoundError:
            return False

    @staticmethod
    def manage_system():
        while True:
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
                break

    @staticmethod
    def manage_user():
        while True:
            choice = int(input("1. 회원 검색 | 2. ????(연체정보? 회원삭제?) | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                AdminMenu.search_member('CSVFiles/user.csv')
            elif choice == 2:
                pass
            elif choice == 3:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def manage_staff():
        admin = Admin()
        while True:
            choice = int(input("1. 직원 검색 | 2. 직원 등록 | 3. 직원 삭제 | 4. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                AdminMenu.search_member('CSVFiles/admin.csv')
            elif choice == 2:
                # admin = Admin()  # 아무래도 이 부분이 누적되지 않는 원인 같은데... 루프 밖으로 옮겨봤으나 차이는 없음
                admin.register()
                print(f"Main.py의 AdminMenu 내에서 추가 => ID: {admin.get_id()}")
                admin.write_csv_file('CSVFiles/admin.csv', header=None)
            elif choice == 3:
                AdminMenu.remove_member('CSVFiles/admin.csv')
            elif choice == 4:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def search_member(file_path):
        search_member = input("이름 or ID (전체 검색을 원하면 엔터) : ")
        member_data = Member.read_csv_file(file_path)
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
        while True:
            choice = int(input("1. 도서 검색 | 2. 도서 추가 | 3. 도서 삭제 | 4. 요청 목록 | 5. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                Library.search_book('CSVFiles/library_book.csv')
            elif choice == 2:
                library = Library()
                library.add_book()
                library.add_book_to_library('CSVFiles/library_book.csv', header=None)
            elif choice == 3:
                Library.remove_book('CSVFiles/library_book.csv')
            elif choice == 4:
                AdminMenu.request_list()
            elif choice == 5:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def request_list():
        while True:
            choice = int(input("1. 대여 요청 목록 | 2. 구매 요청 목록 | 3. 종료\n>> 진행하시려는 번호를 입력하세요 : "))
            Line.line_two()
            if choice == 1:
                AdminMenu.rental_requests_list('CSVFiles/rental_requests.csv')
                rent_request_no = str(input("승인할 NO을 입력해주세요. : "))
                AdminMenu.approve_rental_request(rent_request_no)
            elif choice == 2:
                AdminMenu.purchase_requests_list('CSVFiles/purchase_requests.csv')
                approval = str(input("구매 요청 승인여부 [ 1.승인 | 2.반려 ] : "))
                if approval == "1":
                    purchase_request_no = int(input("승인할 NO를 입력해주세요. : "))
                    AdminMenu.approve_purchase_request(purchase_request_no)
                else:
                    purchase_request_no = int(input("반려할 요청NO를 입력해주세요. : "))
                    AdminMenu.reject_purchase_request(purchase_request_no)
            elif choice == 3:
                print("시스템을 종료합니다.")
                break

    @staticmethod
    def rental_requests_list(file_path):
        purchase_requests = AdminMenu.read_csv_file(file_path)
        if purchase_requests:
            columns = ["NO", "요청자", "ISBN", "요청 날짜", "대여 확인"]
            df = pd.DataFrame(purchase_requests, columns=columns)
            print("대여 요청 목록:")
            print(df)
        else:
            print("대여 요청 목록이 비어 있습니다.")

    @staticmethod
    def approve_rental_request(request_no):
        requests_data = AdminMenu.read_csv_file('CSVFiles/rental_requests.csv')

        for request in requests_data:
            if request[0] == request_no:
                request[4] = "대여 완료"

        with open('CSVFiles/rental_requests.csv', mode="w", encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            header = ["NO", "요청자", "ISBN", "요청 날짜", "대여 확인"]
            writer.writerow(header)
            for request in requests_data:
                writer.writerow(request)

        book_data = Library.read_csv_file('CSVFiles/library_book.csv')
        for book in book_data:
            if book[0] == request_no:
                book[5] = "대여 중"
        Library.update_csv_file(book_data)

        print("대여 요청을 승인하였습니다.")

    @staticmethod
    def purchase_requests_list(file_path):
        purchase_requests = AdminMenu.read_csv_file(file_path)
        if purchase_requests:
            columns = ["NO", "요청자", "도서 제목", "요청 날짜", "승인 여부", "반려 사유"]
            df = pd.DataFrame(purchase_requests, columns=columns)
            print("구매 요청 목록:")
            print(df)
        else:
            print("구매 요청 목록이 비어 있습니다.")

    @staticmethod
    def approve_purchase_request(request_no):
        requests_data = AdminMenu.read_csv_file('CSVFiles/purchase_requests.csv')

        for request in requests_data:
            if request[0] == request_no:
                request[4] = "승인"

        with open('CSVFiles/purchase_requests.csv', mode="w", encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            header = ["NO", "요청자", "도서 제목", "요청 날짜", "승인 여부", "반려 사유"]
            writer.writerow(header)
            for request in requests_data:
                writer.writerow(request)

        library = Library()
        library.add_book()
        library.add_book_to_library('CSVFiles/library_book.csv', header=None)

        print("구매 요청을 승인하였습니다.")

    @staticmethod
    def reject_purchase_request(request_no):
        requests_data = AdminMenu.read_csv_file('CSVFiles/purchase_requests.csv')

        rejection_reason = str(input("반려 사유를 기입해주세요. : "))

        for request in requests_data:
            if request[0] == request_no:
                request[4] = "반려"
                request[5] = rejection_reason

        with open('CSVFiles/purchase_requests.csv', mode='w', encoding='utf-8', newline='') as f:
            writer = csv.writer(f)
            header = ["NO", "요청자", "도서 제목", "요청 날짜", "승인 여부", "반려 사유"]
            writer.writerow(header)
            for request in requests_data:
                writer.writerow(request)

        print("구매 요청을 반려하였습니다.")

    @staticmethod
    def read_csv_file(file_path):
        data = []
        with open(file_path, mode='r', encoding='utf-8') as f:
            reader = csv.reader(f)
            next(reader)
            for row in reader:
                if row:
                    data.append(row)
        return data
