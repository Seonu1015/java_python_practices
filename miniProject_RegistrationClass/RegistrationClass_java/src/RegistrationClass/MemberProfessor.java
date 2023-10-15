package RegistrationClass;

import java.util.ArrayList;

public class MemberProfessor extends Member implements InterfaceLogin {

	private int professorNum;
	private static int profNumInit = 9;
	private String profPassword;

	static ArrayList<MemberProfessor> profList = new ArrayList<>();

	MemberProfessor(String name, String phone, String major) {
		super(name, phone, major);
		profList.add(this);
		setProfNum();
		setProfPassword();
	}

	public int getProfNum() {
		return professorNum;
	}

	private void setProfNum() {
		this.professorNum = (profNumInit * 1000000) + (super.getMajorNum() * 1000)
				+ getMajorProfCount(super.getMajor());
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
		String lastFourDigit = pNum.substring(pNum.length() - 4);
		this.profPassword = this.getProfNum() + "p" + lastFourDigit;
	}

	@Override
	public boolean login(int ID, String password) {
		if (ID == this.professorNum && password.equals(this.profPassword)) {
			System.out.println(this.getName() + " 교수님 환영합니다.");
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
