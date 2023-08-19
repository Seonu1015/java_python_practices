package RegistrationClass;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		MemberStudent23 student1 = new MemberStudent23("A", "123-456-7890", "국문과");
        MemberStudent23 student2 = new MemberStudent23("B", "987-654-3210", "수학과");
        MemberStudent23 student3 = new MemberStudent23("C", "555-123-4567", "법학과");
        MemberStudent23 student4 = new MemberStudent23("D", "111-222-3333", "통계학과");
        MemberStudent23 student5 = new MemberStudent23("E", "444-555-6666", "무용과");
        MemberStudent23 student6 = new MemberStudent23("F", "777-888-9999", "수학과");
        MemberStudent23 student7 = new MemberStudent23("G", "222-333-4444", "국문과");
        MemberStudent23 student8 = new MemberStudent23("H", "555-666-7777", "법학과");
        MemberStudent23 student9 = new MemberStudent23("I", "888-999-0000", "통계학과");
        MemberStudent23 student10 = new MemberStudent23("J", "123-234-3456", "무용과");
        MemberStudent23 student11 = new MemberStudent23("K", "456-567-6789", "수학과");
        MemberStudent23 student12 = new MemberStudent23("L", "789-890-1234", "국문과");
        MemberStudent23 student13 = new MemberStudent23("M", "111-222-3333", "법학과");
        MemberStudent23 student14 = new MemberStudent23("N", "222-333-4444", "통계학과");
        MemberStudent23 student15 = new MemberStudent23("O", "333-444-5555", "무용과");
        MemberStudent23 student16 = new MemberStudent23("P", "444-555-6666", "수학과");
        MemberStudent23 student17 = new MemberStudent23("Q", "555-666-7777", "국문과");
        MemberStudent23 student18 = new MemberStudent23("R", "666-777-8888", "법학과");
        MemberStudent23 student19 = new MemberStudent23("S", "777-888-9999", "통계학과");
        MemberStudent23 student20 = new MemberStudent23("T", "888-999-0000", "무용과");

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
        System.out.println("student2 : " + student2.getStudentNum() + " 비밀번호 : " + student2.getStudentPassword());
        System.out.println("student7 : " + student7.getStudentNum() + " 비밀번호 : " + student7.getStudentPassword());
        System.out.println("student18 : " + student18.getStudentNum() + " 비밀번호 : " + student18.getStudentPassword());
        System.out.println("prof1 : " + prof1.getProfNum() + " 비밀번호 : " + prof1.getProfPassword());
        System.out.println("prof7 : " + prof7.getProfNum() + " 비밀번호 : " + prof7.getProfPassword());
		ManagementSystem.management();
        
	}
}
