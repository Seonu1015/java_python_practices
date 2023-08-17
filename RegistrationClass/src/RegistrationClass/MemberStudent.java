package RegistrationClass;

import java.util.ArrayList;

//study_0808 참고하기
public class MemberStudent extends Member implements InterfaceEnroll {

	private String major;
	private int majorNum;
	
	static ArrayList<MemberStudent> studentsList = new ArrayList<>();
	static ArrayList<String> majorList = new ArrayList<>();
	
	MemberStudent() {
		
	}
	
	MemberStudent(String name, String phone, )

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
