package RegistrationClass;

import java.util.ArrayList;

public class MemberProfessor extends Member implements InterfaceTeach {

	private int professorNum;
	private static int profNumInit = 9000000;
	private String profPassword;
	
	static ArrayList<MemberProfessor> profList = new ArrayList<>();
	
	MemberProfessor(String name, String phone, String major) {
        super(name, phone, major);
        profList.add(this);
        setProfNum();
    }
	
	public int getProfNum() {
		return professorNum;
	}
	
	private void setProfNum() {
		this.professorNum = profNumInit + super.getMajorNum() + getMajorProfCount(super.getMajor());
    }

    private int getMajorProfCount(String major) {
        int count = 0;
        for (MemberProfessor prof : profList) {
            if (prof.getMajor().equals(major)) {
                count++;
            }
        }
        return count;
    }
    
    String getProfPassword() {
    	return profPassword;
    }
    
    void setProfPassword() {
    	String pNum = super.getPhone();
    	String lastFourDigit = pNum.substring(pNum.length()-4);
    	this.profPassword = this.getProfNum() + "p" + lastFourDigit;
    }

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCourse(Course course) {
		// TODO Auto-generated method stub
		
	}
    
}
