package RegistrationClass;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		MemberStudent23 student1 = new MemberStudent23("김지은", "123-456-7890", "국문과");
        MemberStudent23 student2 = new MemberStudent23("이성민", "987-654-3210", "수학과");
        MemberStudent23 student3 = new MemberStudent23("박지현", "555-123-4567", "법학과");
        MemberStudent23 student4 = new MemberStudent23("정윤서", "111-222-3333", "통계학과");
        MemberStudent23 student5 = new MemberStudent23("최현우", "444-555-6666", "무용과");
        MemberStudent23 student6 = new MemberStudent23("송민지", "777-888-9999", "수학과");
        MemberStudent23 student7 = new MemberStudent23("강지원", "222-333-4444", "국문과");
        MemberStudent23 student8 = new MemberStudent23("황세영", "555-666-7777", "법학과");
        MemberStudent23 student9 = new MemberStudent23("권준호", "888-999-0000", "통계학과");
        MemberStudent23 student10 = new MemberStudent23("윤지수", "123-234-3456", "무용과");
        
        MemberStudent22 student11 = new MemberStudent22("이서영", "456-567-6789", "수학과");
        MemberStudent22 student12 = new MemberStudent22("임지환", "789-890-1234", "국문과");
        MemberStudent22 student13 = new MemberStudent22("김하늘", "111-222-3333", "법학과");
        MemberStudent22 student14 = new MemberStudent22("손태민", "222-333-4444", "통계학과");
        MemberStudent22 student15 = new MemberStudent22("박수진", "333-444-5555", "무용과");
        MemberStudent22 student16 = new MemberStudent22("조민서", "444-555-6666", "수학과");
        MemberStudent22 student17 = new MemberStudent22("한지호", "555-666-7777", "국문과");
        MemberStudent22 student18 = new MemberStudent22("문서연", "666-777-8888", "법학과");
        MemberStudent22 student19 = new MemberStudent22("정태영", "777-888-9999", "통계학과");
        MemberStudent22 student20 = new MemberStudent22("김유림", "888-999-0000", "무용과");

        MemberProfessor prof1 = new MemberProfessor("홍길동", "123-456-7890", "국문과");
        MemberProfessor prof2 = new MemberProfessor("김철수", "987-654-3210", "법학과");
        MemberProfessor prof3 = new MemberProfessor("이영희", "555-123-4567", "수학과");
        MemberProfessor prof4 = new MemberProfessor("박영수", "777-888-9999", "무용과");
        MemberProfessor prof5 = new MemberProfessor("최지영", "444-333-2222", "통계학과");
        MemberProfessor prof6 = new MemberProfessor("정민우", "111-222-3333", "국문과");
        MemberProfessor prof7 = new MemberProfessor("송지현", "999-888-7777", "법학과");
        MemberProfessor prof8 = new MemberProfessor("강민석", "666-555-4444", "수학과");
        MemberProfessor prof9 = new MemberProfessor("윤서영", "333-222-1111", "국문과");
        MemberProfessor prof10 = new MemberProfessor("장영미", "222-333-4444", "무용과");
                
        System.out.println("student1 : " + student1.getStudentNum() + " 비밀번호 : " + student1.getStudentPassword());
        System.out.println("student3 : " + student3.getStudentNum() + " 비밀번호 : " + student3.getStudentPassword());
        System.out.println("student8 : " + student8.getStudentNum() + " 비밀번호 : " + student8.getStudentPassword());
        System.out.println("student13 : " + student13.getStudentNum() + " 비밀번호 : " + student13.getStudentPassword());
        System.out.println("student18 : " + student18.getStudentNum() + " 비밀번호 : " + student18.getStudentPassword());
        System.out.println("prof1 : " + prof1.getProfNum() + " 비밀번호 : " + prof1.getProfPassword());
        System.out.println("prof7 : " + prof7.getProfNum() + " 비밀번호 : " + prof7.getProfPassword());
		Management.management();
        
	}
}
