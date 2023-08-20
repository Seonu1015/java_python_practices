package RegistrationClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CourseInfo implements InterfaceTeach {

	Scanner sc = new Scanner(System.in);

	private String courseName;
	private MemberProfessor professor;

	private static ArrayList<CourseInfo> courseList = new ArrayList<>();
	private ArrayList<MemberStudent> enrolledStudents = new ArrayList<>();

	CourseInfo(MemberProfessor professor) {
		this.professor = professor;
	}

	CourseInfo(String courseName, MemberProfessor professor) {
		this.courseName = courseName;
		this.professor = professor;
	}

	String getCourseName() {
		return courseName;
	}

	static ArrayList<CourseInfo> getCourseList() {
		return courseList;
	}

	MemberProfessor getProfessor() {
		return professor;
	}
	
	public void enrollStudent(MemberStudent student) {
		if (!enrolledStudents.contains(student)) {
	        enrolledStudents.add(student);
	    }
	}
	
	public void dropStudent(MemberStudent student) {
		enrolledStudents.remove(student);
	}

	void managementCourse(MemberProfessor professor) {

		System.out.println("1. 강의등록 | 2. 강의삭제 | 3. 강의정보 | 4. 로그아웃");
		line();
		System.out.println(">> 진행하시려는 번호를 입력해주세요.");
		int selectMenu = sc.nextInt();

		switch (selectMenu) {
		case 1:
			addCourse();
			managementCourse(professor);
			break;
		case 2:
			this.removeCourse();
			managementCourse(professor);
			break;
		case 3:
			listAllCourse();
			line();
			managementCourse(professor);
			break;
		case 4:
			System.out.println("로그아웃되었습니다.");
			line();
			Management.management();
			break;
		default:
			System.err.println("잘못된 입력입니다. 다시 입력해주세요.");
		}
	}

	private void listAllCourse() {
		if (courseList.isEmpty()) {
			System.out.println("등록된 강의가 없습니다.");
		} else {
			System.out.println("교수 " + professor.getName() + "님의 강의 목록");
			int count = 0;
			for (int i = 0; i < courseList.size(); i++) {
				CourseInfo course = courseList.get(i);
				if (course.getProfessor().equals(professor)) {
					count++;
					System.out.println(count + ". " + course.getCourseName());
					
					System.out.println("    수강신청한 학생");
					for(MemberStudent student : course.enrolledStudents) {
						System.out.println("    - " + student.getName() + " (학번 : " + student.getStudentNum() + ")");
					}
					if (course.enrolledStudents.isEmpty()) {
						System.out.println("    - 수강신청한 학생이 없습니다.");
					}
				}
			}
			if (count == 0) {
				System.out.println("등록된 강의가 없습니다.");
			}
		}
	}

	@Override
	public void addCourse() {
		System.out.print("강의명을 입력하세요: ");
		String courseName = sc.next();
		courseList.add(new CourseInfo(courseName, this.professor));
		System.out.println("강의가 등록되었습니다.");
		line();
	}

	@Override
	public void removeCourse() {
		listAllCourse();
	    line();
	    System.out.print("삭제할 강의 번호를 입력하세요: ");
	    int courseDelete = sc.nextInt();
	    if (courseDelete >= 1 && courseDelete <= getCourseList().size()) {
	        String deletedCourseName = getCourseList().get(courseDelete - 1).getCourseName();	        
	        getCourseList().remove(courseDelete - 1);
	        System.out.println(deletedCourseName + " 강의 등록이 취소되었습니다.");
	    } else {
	        System.out.println("유효하지 않은 번호입니다. 취소 작업을 종료합니다.");
	    }
	    line();
	}

	public static void line() {
		System.out.println("============================================");
	}
}