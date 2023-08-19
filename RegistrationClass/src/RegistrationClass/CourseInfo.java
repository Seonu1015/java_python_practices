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
            System.out.println("교수 " + professor.getName() + "님의 강의 목록:");
            for (CourseInfo c : courseList) {
                if (c.getProfessor().equals(professor)) {
                    System.out.println(c.getCourseName());
                }
            }
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
    
    @Override
    public void addCourse() {
    	System.out.print("강의명을 입력하세요: ");
        String courseName = sc.next();
        courseList.add(new CourseInfo(courseName, this.professor));
        System.out.println("강의가 등록되었습니다.");
    }
    
    @Override
    public void removeCourse() {
        System.out.print("삭제할 강의명을 입력하세요: ");
        String courseDelete = sc.next();
        Iterator<CourseInfo> iterator = courseList.iterator();
        while (iterator.hasNext()) {
            CourseInfo course = iterator.next();
            if (course.getCourseName().equals(courseDelete)) {
                iterator.remove();
                System.out.println("강의가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 강의를 찾을 수 없습니다.");
    }
    
    public static void line() {
        System.out.println("============================================");
    }
}