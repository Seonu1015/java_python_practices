package RegistrationClass;

import java.util.ArrayList;

public class MemberStudent22 extends MemberStudent implements InterfaceLogin {

	private int studentNum;
	private static int studentNumInit = 22;
	private String studentPassword;
	
	private static ArrayList<MemberStudent> studentList = new ArrayList<>();
	
	MemberStudent22(String name, String phone, String major) {
        super(name, phone, major);
        setStudentNum();
        setStudentPassword();
        addStudentList(this);
    }
	
	public int getStudentNum() {
		return studentNum;
	}
	
	private void setStudentNum() {
		this.studentNum = (studentNumInit*1000000) + (super.getMajorNum()*1000) + getMajorStudentCount(super.getMajor());
    }

    private int getMajorStudentCount(String major) {
        int count = 1;
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
    
    public static ArrayList<MemberStudent> getStudentList() {
    	return studentList;
    }
    
    public static void addStudentList(MemberStudent student) {
        studentList.add(student);
    }

	@Override
	public boolean login(int ID, String password) {
		if (ID == this.studentNum && password.equals(this.studentPassword)) {
            System.out.println("Login successful.");
            line();
            return true;
        } else {
            System.out.println("Login failed.");
            line();
            return false;
        }
	}
	
	public static void line() {
		System.out.println("============================================");
	}
}
