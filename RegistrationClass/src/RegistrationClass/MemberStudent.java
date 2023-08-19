package RegistrationClass;

import java.util.ArrayList;

public abstract class MemberStudent extends Member {

	//static ArrayList<MemberStudent> studentList = new ArrayList<>();

    MemberStudent(String name, String phone, String major) {
        super(name, phone, major);
        //studentList.add(this);
    }

//    static ArrayList<MemberStudent> getStudentList() {
//        return studentList;
//    }

}
