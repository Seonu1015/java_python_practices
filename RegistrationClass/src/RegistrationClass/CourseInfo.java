package RegistrationClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CourseInfo implements InterfaceTeach {

	Scanner sc = new Scanner(System.in);
	
    private String courseName;
    private MemberProfessor professor;
    
    private static ArrayList<CourseInfo> courseList = new ArrayList<>();
    
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
    
    void managementCourse(MemberProfessor professor) {
        
        System.out.println("1. 강의등록 | 2. 강의삭제 | 3. 강의정보 | 4. 로그아웃");
        line();
        System.out.println(">> 진행하시려는 번호를 입력해주세요.");
        int selectMenu = sc.nextInt();
        
        switch(selectMenu) {
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
            for (int i = 0; i < courseList.size(); i++) {
                CourseInfo course = courseList.get(i);
                System.out.println(i + 1 + ". " + course.getCourseName());
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
        System.out.print("삭제할 강의 번호를 입력하세요: ");
        int courseToDelete = sc.nextInt();

        if (courseToDelete >= 1 && courseToDelete <= getCourseList().size()) {
            String deletedCourseName = getCourseList().get(courseToDelete - 1).getCourseName();
            getCourseList().remove(courseToDelete - 1);
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