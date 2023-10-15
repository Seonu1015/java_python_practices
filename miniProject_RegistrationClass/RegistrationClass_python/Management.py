from abc import ABC, abstractmethod


class Program:
    pass


class CourseRegistration(Program): # 교수가 로그인해 강의 등록
    pass


class Enrollment(Program): # 학생이 로그인해 수강 신청
    pass


class Management(Program): # 직원이 로그인해 학생 교수 직원 정보 관리
    pass


class ManageStrudent(Management):
    pass


class ManageProfessor(Management):
    pass


class ManageStaff(Management):
    pass
