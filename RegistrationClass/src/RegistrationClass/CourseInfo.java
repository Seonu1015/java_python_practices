package RegistrationClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CourseInfo implements InterfaceTeach {

    private String courseName;
    private MemberProfessor professor;
    
    private static ArrayList<CourseInfo> courseList = new ArrayList<>();
    		
    CourseInfo(String courseName, MemberProfessor professor) {
        this.courseName = courseName;
        this.professor = professor;
        courseList.add(this);
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
    
    static void managementCourse(MemberProfessor professor) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1. 강의등록 | 2. 강의삭제 | 3. 강의정보");
        line();
        System.out.println(">> 진행하시려는 번호를 입력해주세요.");
        int selectMenu = sc.nextInt();
        
        switch(selectMenu) {
        case 1:
            System.out.print("강의명을 입력하세요: ");
            String courseName = sc.next();
            CourseInfo course = new CourseInfo(courseName, professor);
            course.addCourse(courseName);
            break;
        case 2:
            System.out.print("삭제할 강의명을 입력하세요: ");
            String courseToDelete = sc.next();
            CourseInfo courseInfo = new CourseInfo(courseToDelete, professor);
            courseInfo.removeCourse(courseToDelete);
            break;
        case 3:
            System.out.println("교수 " + professor.getName() + "의 강의 목록:");
            for (CourseInfo c : courseList) {
                if (c.getProfessor().equals(professor)) {
                    System.out.println(c.getCourseName());
                }
            }
            break;
        default:
            System.err.println("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }
    
    @Override
    public void addCourse(String courseName) {
        courseList.add(new CourseInfo(courseName, this.professor));
        System.out.println("강의가 등록되었습니다.");
    }
    
    @Override
    public void removeCourse(String courseName) {
    	Iterator<CourseInfo> iterator = courseList.iterator();
        while (iterator.hasNext()) {
            CourseInfo course = iterator.next();
            if (course.getCourseName().equals(courseName)) {
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