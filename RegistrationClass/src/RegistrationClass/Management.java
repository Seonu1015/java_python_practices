package RegistrationClass;

import java.util.ArrayList;
import java.util.Scanner;

public class Management {

	public static Scanner sc = new Scanner(System.in);

	public static void management() {
		System.out.println("1. 교수 | 2. 학생 | 3. 종료");
		line();
		System.out.println(">> 진행하시려는 번호를 입력해주세요.");
		int select = sc.nextInt();
		line();
		switch (select) {
		case 1:
			loginProf();
			break;
		case 2:
			loginStudent();
			break;
		case 3:
			System.out.println("종료합니다.");
			break;
		default:
			System.err.println("잘못된 입력입니다. 다시 입력해주세요.");
		}
	}

	static void loginProf() {
		System.out.print("교  번 : ");
		int profNum = sc.nextInt();
		System.out.print("비밀번호 : ");
		String profPW = sc.next();
		line();
		MemberProfessor professor = findProfNumber(profNum);
		if (professor != null) {
			boolean loginSuccess = professor.login(profNum, profPW);
			if (loginSuccess) {
				CourseInfo courseInfo = new CourseInfo(professor);
				courseInfo.managementCourse(professor);
			}
		}
	}

	static MemberProfessor findProfNumber(int profNum) {
		for (MemberProfessor professor : MemberProfessor.profList) {
			if (professor.getProfNum() == profNum) {
				return professor;
			}
		}
		System.out.println("교번을 찾을 수 없습니다.");
		return null;
	}

	static void loginStudent() {
		System.out.print("학  번 : ");
		int studentNum = sc.nextInt();
		System.out.print("비밀번호 : ");
		String studentPW = sc.next();
		line();

		MemberStudent student = findStudNumber(studentNum);
		if (student != null) {
			boolean loginSuccess = student.login(studentNum, studentPW);
			if (loginSuccess) {
				Enrollment enrollment = new Enrollment(student, CourseInfo.getCourseList());
				enrollment.managementEnroll();
			}
		}
	}

	static MemberStudent findStudNumber(int studentNum) {
		ArrayList<MemberStudent> studentList = MemberStudent.getStudentList();
		for (MemberStudent student : studentList) {
			if (student.getStudentNum() == studentNum && student instanceof InterfaceLogin) {
				return student;
			}
		}
		System.out.println("학번을 찾을 수 없습니다.");
		return null;
	}

	public static void line() {
		System.out.println("============================================");
	}

}
