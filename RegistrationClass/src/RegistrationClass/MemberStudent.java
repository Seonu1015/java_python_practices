package RegistrationClass;

import java.util.ArrayList;

public abstract class MemberStudent extends Member {

	static ArrayList<MemberStudent> studentsList = new ArrayList<>();

    MemberStudent(String name, String phone, String major) {
        super(name, phone, major);
        studentsList.add(this);
    }

	ArrayList<MemberStudent> getStudentsList() {
		return MemberStudent.studentsList;
	}

	void setStudentsList(MemberStudent student) {
		studentsList.add(student);
	}

}
