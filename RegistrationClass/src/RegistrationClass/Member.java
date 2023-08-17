package RegistrationClass;

public abstract class Member {
	
    private String name;
    private String phone;
        
    Member() {
    	
    }

    Member(String name, String phone) {
    	this.name = name;
    	this.phone = phone;
    }
    
    public abstract boolean login(String ID, String password);
    public abstract void logout();
    
}
