package RegistrationClass;

import java.util.ArrayList;

public class Member {
	
    private String name;
    private String phone;
	private String major;
	private int majorNum;
	
	static ArrayList<String> majorList = new ArrayList<>();
        
    Member() {
    	
    }

    Member(String name, String phone) {
    	this.name = name;
    	this.phone = phone;
    }
    
	Member(String name, String phone, String major) {
		this.name = name;
    	this.phone = phone;
		this.major = major;
		this.setMajorNum(generateMajorNumber());
		setMajorList();
	}
	
	String getName() {
		return this.name;
	}
	
	String getPhone() {
		return this.phone;
	}

	String getMajor() {
		return this.major;
	}

	public int getMajorNum() {
		return majorNum;
	}

	public void setMajorNum(int majorNum) {
		this.majorNum = majorNum;
	}
	
	ArrayList<String> getMajorList() {
		return majorList;
	}

	void setMajorList() {
		if (!majorList.contains(major)) {
			majorList.add(major);
		}
	}

	private int generateMajorNumber() {
		int baseNumber = 111000 + (majorList.size() * 1000);
		return baseNumber;
	}
	
}
