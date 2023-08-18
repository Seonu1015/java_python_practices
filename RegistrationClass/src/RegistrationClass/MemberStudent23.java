package RegistrationClass;

public class MemberStudent23 extends MemberStudent implements InterfaceEnroll {

	private int studentNum;
	private static int studentNumInit = 23;
	private String studentPassword;
	
	MemberStudent23(String name, String phone, String major) {
        super(name, phone, major);
        setStudentNum();
    }
	
	public int getStudentNum() {
		return studentNum;
	}
	
	private void setStudentNum() {
		this.studentNum = (studentNumInit*1000000) + (super.getMajorNum()*1000) + getMajorStudentCount(super.getMajor());
    }

    private int getMajorStudentCount(String major) {
        int count = 0;
        for (MemberStudent student : studentList) {
            if (student.getMajor().equals(major)) {
                count++;
            }
        }
        return count;
    }
    
    String getStudentPassword() {
    	return studentPassword;
    }
    
    void setStudentPassword() {
    	String pNum = super.getPhone();
    	String lastFourDigit = pNum.substring(pNum.length()-4);
    	this.studentPassword = this.getStudentNum() + "p" + lastFourDigit;
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
