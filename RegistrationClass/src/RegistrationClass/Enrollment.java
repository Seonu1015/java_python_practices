package RegistrationClass;

import java.util.ArrayList;
import java.util.Scanner;

public class Enrollment implements InterfaceEnroll {

	private MemberStudent student;
	private ArrayList<CourseInfo> availableCourses;
	private ArrayList<CourseInfo> enrolledCourses;

    Enrollment(MemberStudent student, ArrayList<CourseInfo> availableCourses) {
        this.student = student;
        this.availableCourses = availableCourses;
        this.enrolledCourses = new ArrayList<>();
    }
    
    void managementEnroll() {
    	Scanner sc = new Scanner(System.in);
    	
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
    		System.out.println("취소하고자 하는 과목의 번호를 입력해주세요.");
    		managementEnroll();
    		break;
    	case 3:
    		System.out.println("현재 신청된 과목의 목록입니다.");
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
    
    private static void listAllCourses(ArrayList<CourseInfo> availableCourses) {
        System.out.println("강의 목록:");
        for (int i = 0; i < availableCourses.size(); i++) {
            CourseInfo course = availableCourses.get(i);
            System.out.println(i + 1 + ". " + course.getCourseName() + " (교수: " + course.getProfessor().getName() + ")");
        }
    }
    
	@Override
	public boolean enrollCourse() {
		listAllCourses(availableCourses);
		line();
		System.out.println("신청하고자 하는 과목의 번호를 입력해주세요.");
		return false;
	}
	
	@Override
	public boolean dropCourse() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void line() {
		System.out.println("============================================");
	}
    
}
