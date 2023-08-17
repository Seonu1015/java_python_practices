package RegistrationClass;

public class MemberStudent23 extends MemberStudent implements InterfaceEnroll {

	private int studentNum;
	private static int studentNumInit = 23000000;
	
	MemberStudent23(String name, String phone, String major) {
        super(name, phone, major);
        this.studentNum = getStudentNum();
    }
	
	int getStudentNum() {
		return studentNum;
	}
	
	void setStudentNum() {
		this.studentNum = studentNumInit + super.getMajorNum(); // 전공의 학생수가 추가될 때마다 +1이 되도록 수정
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
	
}
