package RegistrationClass;

import java.util.Scanner;

public class ManagementSystem implements InterfaceLogin, InterfaceEnroll, InterfaceTeach {
	
	public static Scanner sc = new Scanner(System.in);

	public static void Output() {
		int select = 0;
		line();
		System.out.println("1.회원가입 | 2.로그인 | 3.ID찾기 | 4.PW찾기 | 5.종료");
		line();
		select = sc.nextInt();
		selectNum(select);
	}
	
	private static void selectNum(int select) {
		
		switch (select) {
		case 1:
			join();
		case 2:
			
		case 3:
			
		case 4:
			
		case 5:
			
		}
	}
	
	static void join() {
		int selectMember = 0;
		line();
		System.out.println("1.교수 | 2.학생 | 0.돌아가기");
		line();
		selectMember = sc.nextInt();
		switch(selectMember) {
		case 1:
			System.out.println("교번을 입력하세요.");
			int profNumber = sc.nextInt(); //교수 리스트에 있다면 가입 진행, 없다면 잘못된 입력 이전 메뉴로 돌아가기
		case 2:
			System.out.println("학번을 입력하세요.");
			int studentNumber = sc.nextInt();
		default:
			Output();
		}
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
