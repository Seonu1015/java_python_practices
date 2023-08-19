package RegistrationClass;

import java.util.Scanner;

public class Enrollment implements InterfaceEnroll {

	private MemberStudent student;
    private CourseInfo course;
    
    static void managementEnroll() {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("1. 수강신청 | 2. 수강취소 | 3. 강의목록");
    	line();
		System.out.println(">> 진행하시려는 번호를 입력해주세요.");
    	int selectMenu = sc.nextInt();
    	
    	switch(selectMenu) {
    	case 1:
    		
    		break;
    	case 2:
    		
    		break;
    	case 3:
    		
    		break;
    	default:
    		System.err.println("잘못된 입력입니다. 다시 입력해주세요.");
    	}
    }
    
	@Override
	public boolean enrollCourse(CourseInfo course) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean dropCourse(CourseInfo course) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void line() {
		System.out.println("============================================");
	}
    
}
