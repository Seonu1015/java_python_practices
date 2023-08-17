package RegistrationClass;

import java.util.Scanner;

public class ManagementSystem implements InterfaceLogin, InterfaceEnroll, InterfaceTeach {
	
	public static Scanner sc = new Scanner(System.in);

	//교수 학생 선택해서 각각 관리창에 입장
	//교번 또는 학번과 비밀번호를 입력
	
	
	public static void Output() {
		int select = 0;
		line();
		System.out.println("로그인 해주세요.");
		line();
		System.out.println("초기 비밀번호는 학번 + p + 폰 번호 마지막 4자리 입니다.");
		System.out.println("보안을 위해 첫 로그인 후 비밀번호를 변경해주세요.");
		line();
		
	}
	

	

	
	public static void line() {
		System.out.println("============================================");
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean enrollCourse(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dropCourse(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(String ID, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
}
