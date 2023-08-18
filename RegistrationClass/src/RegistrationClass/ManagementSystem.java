package RegistrationClass;

import java.util.Scanner;

public class ManagementSystem{
	
	public static Scanner sc = new Scanner(System.in);

	//교수 학생 선택해서 각각 관리창에 입장
	//교번 또는 학번과 비밀번호를 입력
	
	
	public static void management() {
		System.out.println("1. 교수 | 2. 학생 | 3. 종료");
		line();
		System.out.println(">> 진행하시려는 번호를 입력해주세요.");
		int select = sc.nextInt();
		line();
		switch(select) {
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
	

	

	
	public static void line() {
		System.out.println("============================================");
	}

}
