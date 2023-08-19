package RegistrationClass;

import java.util.ArrayList;
import java.util.Scanner;

public class Enrollment implements InterfaceEnroll {
	
	Scanner sc = new Scanner(System.in);

	private MemberStudent student;
	private ArrayList<CourseInfo> availableCourse;
	private ArrayList<CourseInfo> enrolledCourse;

    Enrollment(MemberStudent student, ArrayList<CourseInfo> availableCourse) {
        this.student = student;
        this.availableCourse = availableCourse;
        this.enrolledCourse = new ArrayList<>();
    }
    
    void managementEnroll() {
    	
    	System.out.println("1. 수강신청 | 2. 수강취소 | 3. 수강목록 확인 | 4. 로그아웃");
    	line();
		System.out.println(">> 진행하시려는 번호를 입력해주세요.");
    	int selectMenu = sc.nextInt();
    	line();
    	
    	switch(selectMenu) {
    	case 1:
    		enrollCourse();
    		managementEnroll();
    		break;
    	case 2:
    		dropCourse();
    		managementEnroll();
    		break;
    	case 3:
    		listEnrolledCourse();
    		line();
    		managementEnroll();
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
    
    private static void listAllCourse(ArrayList<CourseInfo> availableCourse) {
        System.out.println("강의 목록");
        for (int i = 0; i < availableCourse.size(); i++) {
            CourseInfo course = availableCourse.get(i);
            System.out.println(i + 1 + ". " + course.getCourseName() + " (교수: " + course.getProfessor().getName() + ")");
        }
    }
    
    private void listEnrolledCourse() {
        if (enrolledCourse.isEmpty()) {
            System.out.println("신청된 강의가 없습니다.");
        } else {
            System.out.println("신청된 강의 목록:");
            for (int i = 0; i < enrolledCourse.size(); i++) {
                CourseInfo course = enrolledCourse.get(i);
                System.out.println(i + 1 + ". " + course.getCourseName() + " (교수: " + course.getProfessor().getName() + ")");
            }
        }
    }
    
	@Override
	public void enrollCourse() {
		listAllCourse(availableCourse);
		line();
		System.out.println("신청하고자 하는 강의의 번호를 입력해주세요.");
		int selectCourseNum = sc.nextInt();
		enrolledCourse.add(availableCourse.get(selectCourseNum-1));
		System.out.println(availableCourse.get(selectCourseNum-1).getCourseName() + " 강의의 수강신청이 완료되었습니다.");
		line();
	}
	
	@Override
	public void dropCourse() {
		listEnrolledCourse();
		line();
		System.out.println("취소하고자 하는 강의의 번호를 입력해주세요.");
		int selectCourseNum = sc.nextInt();
		
		if (selectCourseNum < 1 || selectCourseNum > enrolledCourse.size()) {
	        System.out.println("유효하지 않은 번호입니다. 취소 작업을 종료합니다.");
	        return;
	    }
		
		enrolledCourse.remove(selectCourseNum-1);
		System.out.println(enrolledCourse.get(selectCourseNum-1).getCourseName() + " 강의의 수강신청이 취소되었습니다.");
		line();
	}
	
	public static void line() {
		System.out.println("============================================");
	}
    
}
